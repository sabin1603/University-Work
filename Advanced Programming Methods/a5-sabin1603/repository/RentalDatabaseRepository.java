package a5;

import domain.*;

import java.sql.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.DriverManager.getConnection;

public class RentalDatabaseRepository extends DatabaseRepository<String, Rental> {
    public RentalDatabaseRepository(String tableName) {
        super(tableName);
        getData();
    }

    @Override
    public Rental add(Rental rental) throws SQLException {
        openConnection();
        String sql = "INSERT INTO Rental (id, carId, customerId, startDate, endDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, rental.getId());
            statement.setString(2, rental.getCar().getId());
            if (rental.getCustomer() != null) {
                statement.setString(3, rental.getCustomer().getId());
            } else {
                // Handle the case where the customer is null (you may want to throw an exception or set to a default value)
                statement.setNull(3, Types.VARCHAR);
            }
            statement.setDate(4, new java.sql.Date(rental.getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(rental.getEndDate().getTime()));

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        getData();
        return rental;
    }

    public Rental getById(String id) throws SQLException {
        openConnection();
        String sql = "SELECT * FROM Rental WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractRentalFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

    public Rental update(String id, Rental entity) throws SQLException {
        openConnection();
        String sql = "UPDATE Rental SET carId=?, customerId=?, startDate=?, endDate=? WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, entity.getCar().getId());
            statement.setString(2, entity.getCustomer().getId());
            statement.setDate(3, new java.sql.Date(entity.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(entity.getEndDate().getTime()));
            statement.setString(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update rental with ID: " + id, e);
        }
        getData();
        return entity;
    }

    private Rental extractRentalFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String carId = resultSet.getString("carId");
        String customerId = resultSet.getString("customerId");
        Date startDate = resultSet.getDate("startDate");
        Date endDate = resultSet.getDate("endDate");

        CarDatabaseRepository carRepository = new CarDatabaseRepository("Car"); // Assume you have a CarDbRepository
        CustomerDatabaseRepository customerRepository = new CustomerDatabaseRepository("Customer"); // Assume you have a CustomerDbRepository

        Car car = carRepository.getById(carId);
        Customer customer = customerRepository.getById(customerId);

        Rental rental = new Rental(id, car, customer, startDate, endDate);
        return rental;
    }

    public void removeById(String id) throws SQLException {
        openConnection();
        String sql = "DELETE FROM Rental WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove rental with ID: " + id, e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void getData() {
        repository.clear();
        try {
            openConnection();
            String selectString = "SELECT * FROM " + tableName + ";";
            try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    CarDatabaseRepository cars = new CarDatabaseRepository("Car");
                    CustomerDatabaseRepository customers = new CustomerDatabaseRepository("Customer");
                    String id = resultSet.getString("id");
                    String carId = resultSet.getString("carId");
                    Car car = cars.getById(carId);
                    String customerId = resultSet.getString("customerId");
                    Customer customer = customers.getById(customerId);
                    long startDateMillis = resultSet.getLong("startDate");
                    long endDateMillis = resultSet.getLong("endDate");

                    System.out.println("Customer: " + customer);

                    // Create Date objects using milliseconds since the epoch
                    java.util.Date startDate = new java.util.Date(startDateMillis);
                    java.util.Date endDate = new java.util.Date(endDateMillis);

                    Rental rental = new Rental(id, car, customer, startDate, endDate);
                    repository.put(rental.getId(), rental);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package a5;

import domain.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.DriverManager.getConnection;

public class CarDatabaseRepository extends DatabaseRepository<String, Car> {

    public CarDatabaseRepository(String tableName) {
        super(tableName);
        getData();
    }

    @Override
    public Car add(Car car) throws SQLException {
        openConnection();
        String sql = "INSERT INTO Car (id, nameOfCar, fabricationYear, km) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, car.getId());
            statement.setString(2, car.getNameOfCar());
            statement.setInt(3, car.getFabricationYear());
            statement.setInt(4, car.getKm());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace(); // Print the exception details for debugging
            throw new RuntimeException("Failed to add car: " + e.getMessage(), e);
        }
        getData();
        return car;
    }

    @Override
    public Car update(String id, Car entity) throws SQLException {
        openConnection();
        String sql = "UPDATE Car SET nameOfCar=?, fabricationYear=?, km=? WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, entity.getNameOfCar());
            statement.setInt(2, entity.getFabricationYear());
            statement.setInt(3, entity.getKm());
            statement.setString(4, id);

            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        getData();
        return entity;
    }

    public Car getById(String id) throws SQLException {
        openConnection();
        String sql = "SELECT * FROM Car WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractCarFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return null;
    }

    private Car extractCarFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("nameOfCar");
        int fabricationYear = resultSet.getInt("fabricationYear");
        int km = resultSet.getInt("km");
        return new Car(id, name, fabricationYear, km);
    }

    public void removeById(String id) throws SQLException {
        openConnection();
        String sql = "DELETE FROM Car WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove customer with ID: " + id, e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void getData() {
        repository.clear();
        try
        {
            openConnection();
            String selectString = "SELECT * FROM " + tableName + ";";
            try (PreparedStatement ps = conn.prepareStatement(selectString))
            {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next())
                {
                    String id = resultSet.getString("id");
                    String nameOfCar = resultSet.getString("nameOfCar");
                    int fabricationYear = resultSet.getInt("fabricationYear");
                    int km = resultSet.getInt("km");
                    Car car = new Car(id, nameOfCar, fabricationYear, km);
                    repository.put(car.getId(), car);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

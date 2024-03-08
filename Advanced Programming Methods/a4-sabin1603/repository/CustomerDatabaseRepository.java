package repository;

import domain.*;
import utils.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.DriverManager.getConnection;

public class CustomerDatabaseRepository extends DatabaseRepository<String, Customer> {

    public CustomerDatabaseRepository(String tableName) {
        super(tableName);
        getData();
    }

    @Override
    public Customer add(Customer customer) throws SQLException {
        openConnection();
        String sql = "INSERT INTO Customer (id, name, age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getAge());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace(); // Print the exception details for debugging
            throw new RuntimeException("Failed to add customer: " + e.getMessage(), e);
        }
        getData();
        return customer;
    }

    @Override
    public Customer update(String id, Customer entity) throws SQLException {
        openConnection();
        String sql = "UPDATE Customer SET name=?, age=? WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAge());
            statement.setString(3, id);

            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        getData();
        return entity;
    }

    public Customer getById(String id) throws SQLException {
        openConnection();
        String sql = "SELECT * FROM Customer WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractCustomerFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return null;
    }

    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        return new Customer(id, name, age);
    }

    public void removeById(String id) throws SQLException {
        openConnection();
        String sql = "DELETE FROM Customer WHERE id=?";
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
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    Customer customer = new Customer(id, name, age);
                    repository.put(customer.getId(), customer);
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

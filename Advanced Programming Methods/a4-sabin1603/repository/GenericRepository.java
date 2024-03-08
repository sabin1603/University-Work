package repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import domain.Identifiable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GenericRepository<ID, T extends Identifiable<ID>> implements Repository<ID, T> {
    protected Map<ID, T> repository = new HashMap<>();

    private Connection connection;

    public GenericRepository() {}

    // Load database configuration from properties file
//    private void loadDatabaseConfig() {
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("settings.properties")) {
//            Properties prop = new Properties();
//            if (input == null) {
//                System.out.println("Sorry, unable to find settings.properties");
//                return;
//            }
//            prop.load(input);
//
//            // Retrieve database configurations from properties file
//            String repositoryType = prop.getProperty("Repository");
//            String location = prop.getProperty("Location");
//
//            // Establish a connection to the database
//            connection = DriverManager.getConnection("jdbc:" + repositoryType + ":" + location);
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    private void createTableIfNotExists() {
//        try (Statement statement = connection.createStatement()) {
//            // Create Car table if not exists
//            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Car (" +
//                    "id VARCHAR(36) PRIMARY KEY," +
//                    "nameOfCar VARCHAR(255)," +
//                    "fabricationYear INT," +
//                    "km INT)");
//
//            // Create Customer table if not exists
//            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Customer (" +
//                    "id VARCHAR(36) PRIMARY KEY," +
//                    "name VARCHAR(255)," +
//                    "age INT)");
//
//            // Create Rental table if not exists
//            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Rental (" +
//                    "id VARCHAR(36) PRIMARY KEY," +
//                    "carId VARCHAR(36) REFERENCES Car(id)," +
//                    "customerId VARCHAR(36) REFERENCES Customer(id)," +
//                    "startDate DATE," +
//                    "endDate DATE)");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public T add(T item) throws SQLException {

        return repository.put(item.getId(), item);
    }

    @Override
    public T update(ID id, T entity) throws SQLException {
        if (repository.containsKey(id)) {
            repository.put(id, entity);
        } else {
            throw new RuntimeException("Entity with ID " + id + " not found. Cannot update.");
        }
        return entity;
    }

    @Override
    public T delete(ID id) {
        return repository.remove(id);
    }

    @Override
    public T getById(ID id) throws SQLException {

        return repository.get(id);
    }

    @Override
    public boolean exists(ID id){
        if (repository.containsKey(id))
            return true;
        else return false;
    }

    @Override
    public Iterable<T> getAll() {

        return repository.values();
    }

    public Map<ID, T> getRepository() {
        return repository;
    }
}

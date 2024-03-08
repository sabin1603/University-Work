package main;
import domain.*;
import repository.*;
import service.*;
import ui.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
//        // Initialize your database repositories
//        CarDatabaseRepository carRepository = new CarDatabaseRepository("Car");
//        CustomerDatabaseRepository customerRepository = new CustomerDatabaseRepository("Customer");
//        RentalDatabaseRepository rentalRepository = new RentalDatabaseRepository("Rental");
//
//        // Create a Service instance with the repositories
//        Service service = new Service(carRepository, customerRepository, rentalRepository);
//
//        // Create a ConsoleUI instance with the Service
//        ConsoleUI consoleUI = new ConsoleUI(service);
//
//        // Run the console application
//        consoleUI.run();
        CarDatabaseRepository carRepository = null;
        CustomerDatabaseRepository customerRepository = null;
        RentalDatabaseRepository rentalRepository = null;

        try (FileReader fr = new FileReader("settings.properties")) {
            Properties props = new Properties();
            props.load(fr);

            String repositoryType = props.getProperty("Repository");
            String carsTableName = props.getProperty("Cars");
            String customersTableName = props.getProperty("Customers");
            String rentalsTableName = props.getProperty("Rentals");

            carRepository = new CarDatabaseRepository(carsTableName);
            customerRepository = new CustomerDatabaseRepository(customersTableName);
            rentalRepository = new RentalDatabaseRepository(rentalsTableName);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Service service = new Service(carRepository, customerRepository, rentalRepository);
        ConsoleUI consoleUI = new ConsoleUI(service);
        consoleUI.run();

    }
}

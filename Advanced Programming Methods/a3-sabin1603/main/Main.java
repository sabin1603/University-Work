package main;
import domain.Car;
import domain.Customer;
import domain.Rental;
import repository.RentalRepoBinary;
import repository.Repository;
import service.Service;
import repository.*;
import ui.ConsoleUI;
import utils.SettingsManager;

import java.io.FileInputStream;
import java.util.Date;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("settings.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String repositoryType = properties.getProperty("Repository");

        String carFilePath;
        String carRentalFilePath;
        String customerFilePath;
        if (repositoryType != null && repositoryType.equalsIgnoreCase("binary")) {
            carFilePath = "car.bin";
            carRentalFilePath="rentals.bin";
            customerFilePath = "customers.bin";
        } else {
            carFilePath = "car.txt";
            carRentalFilePath="rentals.txt";
            customerFilePath = "customers.txt";
        }



        Service service = new Service(carFilePath, carRentalFilePath, customerFilePath);
        Repository<String, Car> carRepo = new CarRepoBinary(carFilePath);
        Car car1 = new Car("car1", "Nissan X-Trail", 2016, 220000);
        Car car2 = new Car("car2", "Audi A4", 2013, 280000);
        Car car3 = new Car("car3", "Kia Sportage", 2016,150000);
        Car car4 = new Car("car4", "Volvo S60", 2014, 260000);
        Car car5 = new Car("car5", "Lamborghini Urus", 2023, 6000);

        carRepo.add(car1);
        carRepo.add(car2);
        carRepo.add(car3);
        carRepo.add(car4);
        carRepo.add(car5);

        Repository<String, Customer> customerRepo = new CustomerRepoBinary(customerFilePath);
        Customer customer1 = new Customer("customer1", "Sabin", 20);
        Customer customer2 = new Customer("customer2", "Calin" , 20);
        Customer customer3 = new Customer("customer3", "Darian", 19);
        Customer customer4 = new Customer("customer4","George", 21);
        Customer customer5 = new Customer("customer5","Badau", 18);

        customerRepo.add(customer1);
        customerRepo.add(customer2);
        customerRepo.add(customer3);
        customerRepo.add(customer4);
        customerRepo.add(customer5);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Repository<String, Rental> rentalRepo = new RentalRepoBinary(carRentalFilePath);

        Date startDate1 = dateFormat.parse("2023-03-16");
        Date endDate1 = dateFormat.parse("2023-03-20");

        Date startDate2 = dateFormat.parse("2023-08-25");
        Date endDate2 = dateFormat.parse("2023-08-30");

        Date startDate3 = dateFormat.parse("2023-11-01");
        Date endDate3 = dateFormat.parse("2023-11-05");

        Date startDate4 = dateFormat.parse("2023-10-30");
        Date endDate4 = dateFormat.parse("2023-11-01");

        Date startDate5 = dateFormat.parse("2023-01-26");
        Date endDate5 = dateFormat.parse("2023-01-30");

        Rental rental1 = new Rental("rental_id1", car1, customer1, startDate1, endDate1);
        Rental rental2 = new Rental("rental_id2", car2, customer2, startDate2, endDate2);
        Rental rental3 = new Rental("rental_id3", car3, customer3, startDate3, endDate3);
        Rental rental4 = new Rental("rental_id4", car4, customer4, startDate4, endDate4);
        Rental rental5 = new Rental("rental_id5", car5, customer5, startDate5, endDate5);

        rentalRepo.add(rental1);
        rentalRepo.add(rental2);
        rentalRepo.add(rental3);
        rentalRepo.add(rental4);
        rentalRepo.add(rental5);


        try {
            loadSettings(service);
            service.loadCarsFromFile();
            service.loadCustomersFromFile();
            service.loadRentalsFromFile();
            ConsoleUI consoleUI = new ConsoleUI(service);
            consoleUI.run();
        } catch (IOException e) {
            System.out.println("Error loading settings: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }

    private static void loadSettings(Service service) throws IOException {
        SettingsManager settingsManager = new SettingsManager();

        // Assuming you have a settings manager to get values
        String carsFileName = settingsManager.getCarRepositoryFile();
        String customersFileName = settingsManager.getCustomerRepositoryFile();
        String rentalsFileName = settingsManager.getRentalRepositoryFile();

        service.setCarsFileName(carsFileName);
        service.setCustomersFileName(customersFileName);
        service.setRentalsFileName(rentalsFileName);
    }

}

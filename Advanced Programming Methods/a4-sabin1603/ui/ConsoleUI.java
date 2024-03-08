package ui;

import domain.Car;
import domain.Customer;
import domain.Rental;
import repository.*;
import service.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {
    private Service service;
    private Scanner scanner;

    public ConsoleUI(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() throws SQLException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Car");
            System.out.println("2. Add Customer");
            System.out.println("3. Add Rental");
            System.out.println("4. Update Car");
            System.out.println("5. Update Customer");
            System.out.println("6. Update Rental");
            System.out.println("7. Remove Car");
            System.out.println("8. Remove Customer");
            System.out.println("9. Remove Rental");
            System.out.println("10. List All Cars");
            System.out.println("11. List All Customers");
            System.out.println("12. List All Rentals");
            System.out.println("13. List All Cars In Km Range");
            System.out.println("14. List All Cars In Year Range");
            System.out.println("15. List All Customers Older Than Age");
            System.out.println("16. List All Customers Younger Than Age");
            System.out.println("17. List All Cars By Name");
            System.out.println("18. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    addRental();
                    break;
                case 4:
                    updateCar();
                    break;
                case 5:
                    updateCustomer();
                    break;
                case 6:
                    updateRental();
                    break;
                case 7:
                    removeCar();
                    break;
                case 8:
                    removeCustomer();
                    break;
                case 9:
                    removeRental();
                    break;
                case 10:
                    listAllCars();
                    break;
                case 11:
                    listAllCustomers();
                    break;
                case 12:
                    listAllRentals();
                    break;
                case 13:
                    getAllCarsByKmRange();
                    break;
                case 14:
                    getAllCarsByYearRange();
                    break;
                case 15:
                    getAllCustomersOlderThanAge();
                    break;
                case 16:
                    getAllCustomerYoungerThanAge();
                    break;
                case 17:
                    getCarsByName();
                    break;
                case 18:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private void addCar() throws SQLException {
        System.out.println("Enter car details:");
        System.out.println("ID: ");
        String carId = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Fabrication Year: ");
        int fabricationYear = scanner.nextInt();
        System.out.print("Kilometers: ");
        int km = scanner.nextInt();
        scanner.nextLine();

        Car car = new Car(carId, name, fabricationYear, km);
        service.addCar(car);
        System.out.println("Car added successfully.");
    }

    private void addCustomer() throws SQLException {
        System.out.println("Enter customer details:");
        System.out.println("ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Customer customer = new Customer(customerId, name, age);
        service.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    private void addRental() {
        System.out.println("Enter rental details:");
        System.out.println("Rental ID: ");
        String rentalId = scanner.nextLine();
        System.out.print("Car details - ID: ");
        String carId = scanner.nextLine();
        System.out.print("Car details - Name: ");
        String carName = scanner.nextLine();
        System.out.print("Car details - Fabrication Year: ");
        int fabricationYear = scanner.nextInt();
        System.out.print("Car details - Kilometers: ");
        int km = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Customer details - ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Customer details - Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Customer details - Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Start Date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        System.out.print("End Date (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();

        try {
            Car car = new Car(carId, carName, fabricationYear, km);

            Customer customer = new Customer(customerId, customerName, age);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            service.addRental(rentalId, car, customer, startDate, endDate);

            System.out.println("Rental added successfully.");
        } catch (ParseException e) {
            System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateCar() throws SQLException {
        System.out.print("Enter Car ID to update: ");
        String carId = scanner.nextLine().trim();
        Car carToUpdate = service.getCarById(carId);

        if (carToUpdate != null) {
            System.out.println("Enter new details for the car:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Fabrication Year: ");
            int fabricationYear = scanner.nextInt();
            System.out.print("Kilometers: ");
            int km = scanner.nextInt();
            scanner.nextLine();

            carToUpdate.setNameOfCar(name);
            carToUpdate.setFabricationYear(fabricationYear);
            carToUpdate.setKm(km);

            service.updateCar(carId, carToUpdate);
            System.out.println("Car updated successfully.");
        } else {
            System.out.println("Car not found with the specified ID.");
        }
    }

    private void updateCustomer() throws SQLException {
        System.out.print("Enter Customer ID to update: ");
        String customerId = scanner.nextLine().trim();
        Customer customerToUpdate = service.getCustomerById(customerId);

        if (customerToUpdate != null) {
            System.out.println("Enter new details for the customer:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            customerToUpdate.setName(name);
            customerToUpdate.setAge(age);

            service.updateCustomer(customerId, customerToUpdate);
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer not found with the specified ID.");
        }
    }

    private void updateRental() throws SQLException {
        System.out.print("Enter Rental ID to update: ");
        String rentalId = scanner.nextLine().trim();
        Rental rentalToUpdate = service.getRentalById(rentalId);

        if (rentalToUpdate != null) {
            System.out.println("Enter new details for the rental:");
            System.out.print("Car details - ID: ");
            String newCarId = scanner.nextLine();
            System.out.print("Car details - Name: ");
            String carName = scanner.nextLine();
            System.out.print("Car details - Fabrication Year: ");
            int fabricationYear = scanner.nextInt();
            System.out.print("Car details - Kilometers: ");
            int km = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Customer details - ID: ");
            String newCustomerId = scanner.nextLine();
            System.out.print("Customer details - Name: ");
            String customerName = scanner.nextLine();
            System.out.print("Customer details - Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Start Date (yyyy-MM-dd): ");
            String startDateStr = scanner.nextLine();
            System.out.print("End Date (yyyy-MM-dd): ");
            String endDateStr = scanner.nextLine();

            try {
                Car car = new Car(newCarId, carName, fabricationYear, km);
                Customer customer = new Customer(newCustomerId,customerName, age);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = dateFormat.parse(startDateStr);
                Date endDate = dateFormat.parse(endDateStr);

                rentalToUpdate.setCar(car);
                rentalToUpdate.setCustomer(customer);
                rentalToUpdate.setStartDate(startDate);
                rentalToUpdate.setEndDate(endDate);

                service.updateRental(rentalId, rentalToUpdate);
                System.out.println("Rental updated successfully.");
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Rental not found with the specified ID.");
        }
    }

    private void removeCar() throws SQLException {
        System.out.print("Enter Car ID to remove: ");
        String carId = scanner.nextLine().trim();
        Car carToRemove = service.getCarById(carId);

        if (carToRemove != null) {
            service.removeCar(carId);
            System.out.println("Car removed successfully.");
        } else {
            System.out.println("Car not found with the specified ID.");
        }
        try {
            CarDatabaseRepository carRepository = new CarDatabaseRepository("Car");
            carRepository.removeById(carToRemove.getId());
        } catch (SQLException e) {
            // Handle database removal error (log, show error message, etc.)
            e.printStackTrace();
        }

    }

    private void removeCustomer() throws SQLException {
        System.out.print("Enter Customer ID to remove: ");
        String customerId = scanner.nextLine().trim();
        Customer customerToRemove = service.getCustomerById(customerId);

        if (customerToRemove != null) {
            service.removeCustomer(customerId);
            System.out.println("Customer removed successfully.");
        } else {
            System.out.println("Customer not found with the specified ID.");
        }

        try {
            CustomerDatabaseRepository customerRepository = new CustomerDatabaseRepository("Customer");
            customerRepository.removeById(customerToRemove.getId());
        } catch (SQLException e) {
            // Handle database removal error (log, show error message, etc.)
            e.printStackTrace();
        }
    }

    private void removeRental() throws SQLException {
        System.out.print("Enter Rental ID to remove: ");
        String rentalId = scanner.nextLine().trim();
        Rental rentalToRemove = service.getRentalById(rentalId);

        if (rentalToRemove != null) {
            service.removeRental(rentalId);
            System.out.println("Rental removed successfully.");
        } else {
            System.out.println("Rental not found with the specified ID.");
        }

        try {
            RentalDatabaseRepository rentalRepository = new RentalDatabaseRepository("Rental");
            rentalRepository.removeById(rentalToRemove.getId());
        } catch (SQLException e) {
            // Handle database removal error (log, show error message, etc.)
            e.printStackTrace();
        }
    }

    private void listAllCars() {
        Iterable<Car> cars = service.getAllCars();
        System.out.println("List of Cars:");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void listAllCustomers() {
        Iterable<Customer> customers = service.getAllCustomers();
        System.out.println("List of Customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void listAllRentals() {
        Iterable<Rental> rentals = service.getAllRentals();
        System.out.println("List of Rentals:");
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }

    private void getAllCarsByKmRange(){
        System.out.println("Enter the minimum number of km: ");
        float minKm = scanner.nextFloat();
        System.out.println("Enter the maximum number of km: ");
        float maxKm = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("The cars in the given km range are: ");
        String s = service.getAllCarsByKmRange(minKm,maxKm).toString();
        System.out.println(s);
    }

    private void getAllCarsByYearRange(){
        System.out.println("Enter the minimum year: ");
        int minYear = scanner.nextInt();
        System.out.println("Enter the maximum year: ");
        int maxYear = scanner.nextInt();
        scanner.nextLine();

        System.out.println("The cars in the given year range are: ");
        String s = service.getAllCarsByYearRange(minYear, maxYear).toString();

        System.out.println(s);
    }

    private void getAllCustomersOlderThanAge(){
        System.out.println("Enter the age to filter by: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("The customers older than the given age are: ");
        String s = service.getAllCustomersOlderThanAge(age).toString();
        System.out.println(s);
    }

    private void getAllCustomerYoungerThanAge(){
        System.out.println("Enter the age to filter by: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("The customers younger than the given age are: ");
        String s = service.getAllCustomerYoungerThanAge(age).toString();
        System.out.println(s);
    }

    private void getCarsByName(){
        System.out.println("Enter the car name to filter by: ");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.println("The cars that have this name are: ");
        String s = service.getCarsByName(name).toString();
        System.out.println(s);
    }
}

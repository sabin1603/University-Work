package ui;

import service.Service;
import domain.Car;
import domain.Customer;
import domain.Rental;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class ConsoleUI {
    private Service service;
    private Scanner scanner;

    public ConsoleUI(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Car");
            System.out.println("2. Add Rental");
            System.out.println("3. Update Car");
            System.out.println("4. Update Rental");
            System.out.println("5. Remove Car");
            System.out.println("6. Remove Rental");
            System.out.println("7. List All Cars");
            System.out.println("8. List All Rentals");
            System.out.println("9. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    addRental();
                    break;
                case 3:
                    updateCar();
                    break;
                case 4:
                    updateRental();
                    break;
                case 5:
                    removeCar();
                    break;
                case 6:
                    removeRental();
                    break;
                case 7:
                    listAllCars();
                    break;
                case 8:
                    listAllRentals();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private void addCar() {
        System.out.println("Enter car details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Fabrication Year: ");
        int fabricationYear = scanner.nextInt();
        System.out.print("Kilometers: ");
        int km = scanner.nextInt();
        scanner.nextLine();


        String carId = UUID.randomUUID().toString();

        Car car = new Car(carId, name, fabricationYear, km);
        service.addCar(car);
        System.out.println("Car added successfully.");
    }


    private void addRental() {
        System.out.println("Enter rental details:");
        System.out.print("Car details - Name: ");
        String carName = scanner.nextLine();
        System.out.print("Car details - Fabrication Year: ");
        int fabricationYear = scanner.nextInt();
        System.out.print("Car details - Kilometers: ");
        int km = scanner.nextInt();
        scanner.nextLine();
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
            String carId = UUID.randomUUID().toString();
            Car car = new Car(carId, carName, fabricationYear, km);
            String customerId = UUID.randomUUID().toString();
            Customer customer = new Customer(customerId, customerName, age);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            service.addRental(car, customer, startDate, endDate);

            System.out.println("Rental added successfully.");
        } catch (ParseException e) {
            System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateCar() {
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

            service.updateCar(carToUpdate);
            System.out.println("Car updated successfully.");
        } else {
            System.out.println("Car not found with the specified ID.");
        }
    }

    private void updateRental() {
        System.out.print("Enter Rental ID to update: ");
        String rentalId = scanner.nextLine().trim();
        Rental rentalToUpdate = service.getRentalById(rentalId);

        if (rentalToUpdate != null) {
            System.out.println("Enter new details for the rental:");
            System.out.print("Car details - Name: ");
            String carName = scanner.nextLine();
            System.out.print("Car details - Fabrication Year: ");
            int fabricationYear = scanner.nextInt();
            System.out.print("Car details - Kilometers: ");
            int km = scanner.nextInt();
            scanner.nextLine();
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
                rentalToUpdate.getCar().setNameOfCar(carName);
                rentalToUpdate.getCar().setFabricationYear(fabricationYear);
                rentalToUpdate.getCar().setKm(km);

                rentalToUpdate.getCustomer().setName(customerName);
                rentalToUpdate.getCustomer().setAge(age);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = dateFormat.parse(startDateStr);
                Date endDate = dateFormat.parse(endDateStr);

                rentalToUpdate.setStartDate(startDate);
                rentalToUpdate.setEndDate(endDate);

                service.updateRental(rentalToUpdate);

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


    private void removeCar() {
        System.out.print("Enter Car ID to remove: ");
        String carId = scanner.nextLine().trim();
        try {
            service.removeCar(carId);
            System.out.println("Car removed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void removeRental() {
        System.out.print("Enter Rental ID to remove: ");
        String rentalId = scanner.nextLine();
        try {
            service.removeRental(rentalId);
            System.out.println("Rental removed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listAllCars() {
        System.out.println("List of Cars:");
        for (Car car : service.getAllCars()) {
            System.out.println("Car ID: " + car.getId() + " - " + car);
        }
    }

    private void listAllRentals() {
        System.out.println("List of Rentals:");
        for (Rental rental : service.getAllRentals()) {
            System.out.println("Rental ID: " + rental.getId() + " - " + rental);
        }
    }
}


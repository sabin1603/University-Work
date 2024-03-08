package UI;

import domain.Car;
import domain.Customer;
import repository.Repository;
import service.Service;

import java.util.Scanner;

public class UI {
    private Service service;
    private Scanner scanner;

    public UI(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Car Rental Program");
            System.out.println("1. Add a car");
            System.out.println("2. Remove a car by name");
            System.out.println("3. Update car's name");
            System.out.println("4. Update car's kilometers");
            System.out.println("5. Update car's rented days");
            System.out.println("6. List all cars");
            System.out.println("0. Exit");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    removeCarByName();
                    break;
                case 3:
                    updateCarName();
                    break;
                case 4:
                    updateCarKilometers();
                    break;
                case 5:
                    updateCarRentedDays();
                    break;
                case 6:
                    listAllCars();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addCar() {
        String name = getStringInput("Enter the name of the car: ");
        int fabricationYear = getIntInput("Enter the fabrication year: ");
        int km = getIntInput("Enter the kilometers: ");
        int nrDaysRented = getIntInput("Enter the number of days rented: ");
        String customerName = getStringInput("Enter the customer's name: ");
        int customerAge = getIntInput("Enter the customer's age: ");
        Customer customer = new Customer(customerName, customerAge);
        Car car = new Car(name, customer, fabricationYear, km, nrDaysRented);
        service.add(car);
        System.out.println("Car added successfully.");
    }

    private void removeCarByName() {
        String name = getStringInput("Enter the name of the car to remove: ");
        service.removeByName(name);
        System.out.println("Car removed successfully.");
    }

    private void updateCarName() {
        String oldName = getStringInput("Enter the current name of the car: ");
        String newName = getStringInput("Enter the new name: ");
        service.updateName(oldName, newName);
        System.out.println("Car name updated successfully.");
    }

    private void updateCarKilometers() {
        int km = getIntInput("Enter the current kilometers of the car: ");
        int newKm = getIntInput("Enter the new kilometers: ");
        service.updateKm(km, newKm);
        System.out.println("Car kilometers updated successfully.");
    }

    private void updateCarRentedDays() {
        int nrDaysRented = getIntInput("Enter the current number of days rented: ");
        int newNrDaysRented = getIntInput("Enter the new number of days rented: ");
        service.updateNrDaysRented(nrDaysRented, newNrDaysRented);
        System.out.println("Car rented days updated successfully.");
    }

    private void listAllCars() {
        Car[] cars = service.getRepo().getRepo();
        if (cars == null || cars.length == 0) {
            System.out.println("No cars in the repository.");
        } else {
            for (Car car : cars) {
                System.out.println(car.toString());
            }
        }
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void add(Car car){
        service.add(car);
    }

    public static void main(String[] args) {
        Repository repository = new Repository();
        Service service = new Service(repository);
        Customer c1 = new Customer("Sabin", 20);
        Car car1 = new Car("Nissan X-Trail", c1, 2016, 224000, 30);
        Customer c2 = new Customer("Calin", 21);
        Car car2 = new Car("Audi A4", c2, 2013, 264000, 60);
        Customer c3 = new Customer("Darian", 19);
        Car car3 = new Car("Kia Sportage", c3, 2016, 180000, 69);
        Customer c4 = new Customer("Badau", 18);
        Car car4 = new Car("Lamborghini Hurracan", c4, 2023, 100, 2);
        Customer c5 = new Customer("Geo", 33);
        Car car5 = new Car("Volvo S60", c5, 2012, 300000, 10);

        UI ui = new UI(service);
        ui.add(car1);
        ui.add(car2);
        ui.add(car3);
        ui.add(car4);
        ui.add(car5);
        ui.start();
    }
}

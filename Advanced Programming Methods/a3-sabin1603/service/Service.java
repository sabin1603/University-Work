package service;

import domain.Car;
import domain.Customer;
import domain.Rental;
import repository.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Service {
    private FileRepository<String, Car> carRepository;
    private FileRepository<String, Rental> rentalRepository;
    private FileRepository<String, Customer> customerRepository;
    private RentalRepoBinary rentalRepositoryBinary;
    private CarRepoBinary carRepositoryBinary;
    private CarRepoText carRepositoryText;
    private CustomerRepoText customerRepoText;
    private CustomerRepoBinary customerRepoBinary;
    private RentalRepoText rentalRepositoryText;
    private String carsFileName;
    private String rentalsFileName;
    private String customersFileName;
    public Service(String filenameCarRepo, String filenameCustomersRepo, String filenameRentalRepo) throws IOException {
        if (filenameCarRepo.contains(".txt")) {
            carRepository = new CarRepoText(filenameCarRepo);
            carRepositoryText = (CarRepoText) carRepository;
        } else if (filenameCarRepo.contains(".bin")) {
            carRepository = new CarRepoBinary(filenameCarRepo);
            carRepositoryBinary = (CarRepoBinary) carRepository;
        } else {
            throw new FileNotFoundException("File must be either .txt or .bin!");
        }

        if (filenameCustomersRepo.contains(".txt")) {
            customerRepository = new CustomerRepoText(filenameCustomersRepo);
            customerRepoText = (CustomerRepoText) customerRepository;
        } else if (filenameCustomersRepo.contains(".bin")) {
            customerRepository = new CustomerRepoBinary(filenameCustomersRepo);
            customerRepoBinary = (CustomerRepoBinary) customerRepository;
        } else {
            throw new FileNotFoundException("File must be either .txt or .bin!");
        }
        if (filenameRentalRepo.contains(".txt")) {
            rentalRepository = new RentalRepoText(filenameRentalRepo);
            rentalRepositoryText = (RentalRepoText) rentalRepository;
        } else if (filenameRentalRepo.contains(".bin")) {
            rentalRepository = new RentalRepoBinary(filenameRentalRepo);
            rentalRepositoryBinary = (RentalRepoBinary) rentalRepository;
        } else {
            throw new FileNotFoundException("File must be either .txt or .bin!");
        }
    }


    public void setRepositoryTypes(String carRepositoryType, String customersRepositoryType, String rentalRepositoryType) {
        switch (carRepositoryType) {
            case "text":
                carRepository = carRepositoryText;
                break;
            case "binary":
                carRepository = carRepositoryBinary;
                break;
            default:
                throw new IllegalArgumentException("Invalid car repository type");
        }

        switch (customersRepositoryType) {
            case "text":
                customerRepository = customerRepoText;
                break;
            case "binary":
                customerRepository = customerRepoBinary;
                break;
            default:
                throw new IllegalArgumentException("Invalid customers repository type");
        }

        switch (rentalRepositoryType) {
            case "text":
                rentalRepository = rentalRepositoryText;
                break;
            case "binary":
                rentalRepository = rentalRepositoryBinary;
                break;
            default:
                throw new IllegalArgumentException("Invalid rental repository type");
        }
    }


    public void addCar(Car car) {
        carRepository.add(car);
    }

    public Car getCarById(String id) {
        return carRepository.getById(id);
    }

    public void updateCar(String id, Car car) {
        carRepository.update(id, car);
    }

    public void removeCar(String id) {
        carRepository.remove(id);
    }

    public Iterable<Car> getAllCars() {
        return carRepository.getAll();
    }


    public void addRental(String rentalId, Car car, Customer customer, Date startDate, Date endDate) {
        Rental rental = new Rental(rentalId, car, customer, startDate, endDate);
        rentalRepository.add(rental);
        rentalRepositoryBinary.writeToFile();
    }

    public Customer getCustomerById(String customerId) {
        for (Customer customer : customerRepository.getAll()) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }


    public Rental getRentalById(String id) {
        return rentalRepository.getById(id);
    }

    public void updateRental(String id, Rental rental) {
        rentalRepository.update(id, rental);
    }

    public void removeRental(String id) {
        rentalRepository.remove(id);
    }

    public Iterable<Rental> getAllRentals() {
        return rentalRepository.getAll();
    }

    public void writeRentalDataToFile() throws IOException {
        rentalRepositoryBinary.writeToFile();
    }

    public void setCarsFileName(String carsFileName) {
        this.carsFileName = carsFileName;
    }

    public void setCustomersFileName(String customersFileName){
        this.customersFileName = customersFileName;
    }

    public void setRentalsFileName(String rentalsFileName) {
        this.rentalsFileName = rentalsFileName;
    }

    public void loadCarsFromFile() throws IOException, ParseException {
        // Create a new repository instance based on the file type
        if (carsFileName.endsWith(".txt")) {
            carRepository = new CarRepoText(carsFileName);
            carRepositoryText = (CarRepoText) carRepository;
        } else if (carsFileName.endsWith(".bin")) {
            carRepository = new CarRepoBinary(carsFileName);
            carRepositoryBinary = (CarRepoBinary) carRepository;
        } else {
            throw new FileNotFoundException("File must be either .txt or .bin!");
        }

        // Read data from file
        try (BufferedReader reader = new BufferedReader(new FileReader(carsFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String carId = parts[0].trim();
                    String name = parts[1].trim();
                    int fabricationYear = Integer.parseInt(parts[2].trim());
                    int km = Integer.parseInt(parts[3].trim());

                    Car car = new Car(carId, name, fabricationYear, km);
                    carRepository.add(car);
                } else {
                    System.out.println("Invalid line in the car file: " + line);
                }
            }
        }
    }


    public void loadRentalsFromFile() throws IOException, ParseException {
        // Create a new repository instance based on the file type
        if (rentalsFileName.endsWith(".txt")) {
            rentalRepository = new RentalRepoText(rentalsFileName);
            rentalRepositoryText = (RentalRepoText) rentalRepository;
        } else if (rentalsFileName.endsWith(".bin")) {
            rentalRepository = new RentalRepoBinary(rentalsFileName);
            rentalRepositoryBinary = (RentalRepoBinary) rentalRepository;
        } else {
            throw new FileNotFoundException("File must be either .txt or .bin!");
        }

        // Read data from file
        try (BufferedReader reader = new BufferedReader(new FileReader(rentalsFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    String rentalId = parts[0].trim();
                    String carId = parts[1].trim();
                    String customerId = parts[2].trim();
                    String startDateStr = parts[3].trim();
                    String endDateStr = parts[4].trim();

                    Car car = carRepository.getById(carId);
                    Customer customer = getCustomerById(customerId);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date startDate = dateFormat.parse(startDateStr);
                    Date endDate = dateFormat.parse(endDateStr);

                    Rental rental = new Rental(rentalId, car, customer, startDate, endDate);

                    // Use the appropriate repository based on the file type
                    if (rentalsFileName.endsWith(".txt")) {
                        rentalRepository.add(rental);
                    } else {
                        rentalRepositoryBinary.add(rental);
                    }
                } else {
                    System.out.println("Invalid line in the rental file: " + line);
                }
            }
        }

        // Write rentals to binary file
        rentalRepositoryBinary.writeToFile();

        // If carRepository is null, initialize it based on the file type
        if (carRepository == null) {
            if (carsFileName.endsWith(".txt")) {
                carRepository = new CarRepoText(carsFileName);
                carRepositoryText = (CarRepoText) carRepository;
            } else if (carsFileName.endsWith(".bin")) {
                carRepository = new CarRepoBinary(carsFileName);
                carRepositoryBinary = (CarRepoBinary) carRepository;
            } else {
                throw new FileNotFoundException("File must be either .txt or .bin!");
            }
        }
    }

    public void loadCustomersFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(customersFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    Customer customer = new Customer(id, name, age);
                    customerRepository.add(customer);
                }
            }
        }
    }
}


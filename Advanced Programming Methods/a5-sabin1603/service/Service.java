package a5;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import domain.Car;
import domain.Customer;
import domain.Rental;
import repository.CarDatabaseRepository;
import repository.CustomerDatabaseRepository;
import repository.RentalDatabaseRepository;

import java.sql.SQLException;
import java.util.Date;
public class Service {
    private CarDatabaseRepository carRepository;
    private CustomerDatabaseRepository customerRepository;
    private RentalDatabaseRepository rentalRepository;

    public Service(
            CarDatabaseRepository carRepository,
            CustomerDatabaseRepository customerRepository,
            RentalDatabaseRepository rentalRepository
    ) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }

    public void addCar(Car car) throws SQLException {
        carRepository.add(car);
    }

    public Car getCarById(String id) throws SQLException {
        return carRepository.getById(id);
    }

    public void updateCar(String id, Car car) throws SQLException {
        carRepository.update(id, car);
    }

    public void removeCar(String id) {
        carRepository.delete(id);
    }

    public Iterable<Car> getAllCars() {
        return carRepository.getAll();
    }

    public void addRental(String rentalId, Car car, Customer customer, Date startDate, Date endDate) throws SQLException {
        Rental rental = new Rental(rentalId, car, customer, startDate, endDate);
        rentalRepository.add(rental);
    }

    public Rental getRentalById(String id) throws SQLException {
        return rentalRepository.getById(id);
    }

    public void updateRental(String id, Rental rental) throws SQLException {
        rentalRepository.update(id, rental);
    }

    public void removeRental(String id) {
        rentalRepository.delete(id);
    }

    public Iterable<Rental> getAllRentals() {
        return rentalRepository.getAll();
    }

    public void addCustomer(Customer customer) throws SQLException {
        customerRepository.add(customer);
    }

    public Customer getCustomerById(String id) throws SQLException {
        return customerRepository.getById(id);
    }

    public void updateCustomer(String id, Customer customer) throws SQLException {
        customerRepository.update(id, customer);
    }

    public void removeCustomer(String id) {
        customerRepository.delete(id);
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

}

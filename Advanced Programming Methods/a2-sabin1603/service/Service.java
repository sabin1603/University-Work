package service;

import domain.Car;
import domain.Customer;
import domain.Rental;
import repository.GenericRepository;

import java.util.Date;

public class Service {
    private GenericRepository<Car> carRepository;
    private GenericRepository<Rental> rentalRepository;

    public Service() {
        carRepository = new GenericRepository<>();
        rentalRepository = new GenericRepository<>();
    }

    public void addCar(Car car) {
        carRepository.add(car);
    }

    public Car getCarById(String id) {
        return carRepository.getById(id);
    }

    public void updateCar(Car car) {
        carRepository.update(car);
    }

    public void removeCar(String id) {
        carRepository.remove(id);
    }

    public Iterable<Car> getAllCars() {
        return carRepository.getAll();
    }


    public void addRental(Car car, Customer customer, Date startDate, Date endDate) {
        Rental rental = new Rental(car, customer, startDate, endDate);
        rentalRepository.add(rental);
    }


    public Rental getRentalById(String id) {
        return rentalRepository.getById(id);
    }

    public void updateRental(Rental rental) {
        rentalRepository.update(rental);
    }

    public void removeRental(String id) {
        rentalRepository.remove(id);
    }

    public Iterable<Rental> getAllRentals() {
        return rentalRepository.getAll();
    }

}

package service;

import domain.Car;
import repository.Repository;
public class Service {
    private Repository repo;

    public Service(Repository repository) {
        this.repo = repository;
    }
    public Repository getRepo(){
        return repo;
    }

    public Car[] add(Car car){
        return repo.addCar(car);
    }

    public Car[] removeByName(String name){
        return repo.removeByName(name);
    }

    public Car[] updateName(String name, String newName){
        return repo.updateName(name, newName);
    }

    public Car[] updateKm(int km, int newKm){
        return repo.updateKm(km, newKm);
    }

    public Car[] updateNrDaysRented(int nrDaysRented, int newNrDaysRented){
        return repo.updateNrDaysRented(nrDaysRented, newNrDaysRented);
    }
}


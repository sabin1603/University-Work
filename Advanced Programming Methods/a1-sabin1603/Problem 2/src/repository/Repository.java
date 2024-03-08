package repository;

import domain.Car;

public class Repository {

    private Car[] repo;

    public Repository() {
        this.repo = new Car[0];
    }

    public Car[] getRepo() {
        for (int i = 0; i < repo.length; i++) {
            System.out.println(repo[i].toString() + "\n");
        }
        return new Car[0];
    }

    public Car[] addToArray(Car car) {
        Car[] newRepo = new Car[repo.length + 1];
        for (int i = 0; i < repo.length; i++) {
            newRepo[i] = repo[i];
        }
        newRepo[repo.length] = car;
        repo = newRepo;
        return repo;
    }


    public Car[] addCar(Car car) {
        repo = addToArray(car);
        return repo;
    }

    public int searchByName(String name) {
        for (int i = 0; i < repo.length; i++) {
            if (repo[i].getNameOfCar().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public int searchByKm(int km) {
        for (int i = 0; i < repo.length; i++) {
            if (repo[i].getKm() == km) {
                return i;
            }
        }
        return -1;
    }

    public int searchByNrDaysRented(int nrDaysRented) {
        for (int i = 0; i < repo.length; i++) {
            if (repo[i].getNrDaysRented() == nrDaysRented) {
                return i;
            }
        }
        return -1;
    }

    public Car[] removeByName(String name) {
        int k = searchByName(name);
        Car[] new_arr = new Car[repo.length - 1];
        for (int i = 0; i < repo.length; i++) {
            int j = 0;
            if (i != j) {
                new_arr[j] = repo[i];
                j++;
            }
        }
        return new_arr;
    }


    public Car[] updateName(String name, String newName) {
        int i = searchByName(name);
        if (i != -1) {
            repo[i].setNameOfCar(newName);
        }
        return repo;
    }


    public Car[] updateKm(int km, int newKm) {
        int i = searchByKm(km);
        if (i != -1) {
            repo[i].setKm(newKm);
        }
        return repo;
    }


    public Car[] updateNrDaysRented(int nrDaysRented, int newNrDaysRented) {
        int i = searchByNrDaysRented(nrDaysRented);
        if (i != -1) {
            repo[i].setNrDaysRented(newNrDaysRented);
        }
        return repo;
    }
}


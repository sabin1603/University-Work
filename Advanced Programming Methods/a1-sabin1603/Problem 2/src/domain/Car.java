package domain;

public class Car {
    private String nameOfCar;
    
    private Customer customer;
    
    private int fabricationYear;

    private int nrDaysRented;

    private int km;
    
    public String getNameOfCar(){
        return nameOfCar;
    }
    
    public int getFabricationYear(){
        return fabricationYear;
    }

    public int getKm(){
        return km;
    }

    public int getNrDaysRented(){
        return nrDaysRented;
    }
    
    public Car(String nameOfCar, Customer customer, int fabricationYear, int km, int nrDaysRented){
        this.nameOfCar = nameOfCar;
        this.customer = customer;
        this.fabricationYear = fabricationYear;
        this.km = km;
        this.nrDaysRented = nrDaysRented;
    }
    
    public void setNameOfCar(String newNameOfCar){
        this.nameOfCar = newNameOfCar;
    }
    
    public void setFabricationYear(int newFabricationYear){
        this.fabricationYear = newFabricationYear;
    }

    public void setKm(int newKm){
        this.km = newKm;
    }

    public void setNrDaysRented(int newNrDaysRented){
        this.nrDaysRented = newNrDaysRented;
    }
    
    public String toString(){
        return "Car: " + nameOfCar + " - " + fabricationYear + " rented by " + customer.getName() + ", " + customer.getAge() + " for " + nrDaysRented + " days" + ", car has " + km + " km so far.";
    }
}

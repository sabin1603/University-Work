package a2;

public class Car implements Identifiable<String> {
    private String id;
    private String nameOfCar;
    private int fabricationYear;
    private int km;

    public Car(String id, String nameOfCar, int fabricationYear, int km) {
        this.id = id;
        this.nameOfCar = nameOfCar;
        this.fabricationYear = fabricationYear;
        this.km = km;
    }

    public String getNameOfCar() {
        return nameOfCar;
    }

    public int getFabricationYear() {
        return fabricationYear;
    }

    public int getKm() {
        return km;
    }

    public void setNameOfCar(String newNameOfCar) {
        this.nameOfCar = newNameOfCar;
    }

    public void setFabricationYear(int newFabricationYear) {
        this.fabricationYear = newFabricationYear;
    }

    public void setKm(int newKm) {
        this.km = newKm;
    }


    public String toString() {
        return "Car: " + "ID: " + id + "-" + nameOfCar + " - " + fabricationYear + ", car has " + km + " km so far.";
    }

    @Override
    public String getId() {
        return id;
    }

}

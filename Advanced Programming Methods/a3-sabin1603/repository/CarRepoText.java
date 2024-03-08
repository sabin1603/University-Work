package repository;

import domain.Car;

import java.io.*;

public class CarRepoText extends FileRepository<String, Car> {

    public CarRepoText(String filename) throws IOException {
        super(filename);
    }

    @Override
    protected void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stringArray = line.split(",");
                if (stringArray.length != 5) {
                    continue;
                } else {
                    String carId = stringArray[0].trim();
                    Car car = new Car(carId, stringArray[1].trim(), Integer.parseInt(stringArray[3].trim()), Integer.parseInt(stringArray[4].trim()));
                    repository.put(carId, car); // Using carId as the key in the HashMap
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void writeToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Car c: getAll())
            {
                writer.write(c.getId() + "," +
                        c.getNameOfCar() + "," +
                        c.getFabricationYear() + "," +
                        c.getKm() + "\n ") ;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

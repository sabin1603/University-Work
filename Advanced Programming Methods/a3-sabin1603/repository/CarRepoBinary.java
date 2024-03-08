package repository;

import domain.Car;
import domain.Rental;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CarRepoBinary extends FileRepository<String, Car> {

    public CarRepoBinary(String filename) throws IOException {
        super(filename);
    }

    @Override
    public void readFromFile() {
        //if the file does not exist
        File file = new File(filename);
        if (! file.exists())
        {
            try {
                HashMap<String, Car> emptyMap = new HashMap<>();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
                oos.writeObject(emptyMap);
            } catch (IOException e) {
                throw new Error("Could not create new empty binary file!");
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            repository = (HashMap<String, Car>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Error("Could not read binary file!");
        }
    }

    @Override
    public void writeToFile()
    {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(repository);
            oos.close();
        } catch (IOException e) {
            throw new Error("Could not write to binary file!");
        }
    }
}


package repository;

import domain.Car;
import domain.Rental;
import repository.GenericRepository;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RentalRepoBinary extends FileRepository<String, Rental> {

    public RentalRepoBinary(String filename) throws IOException {
        super(filename);
    }

    @Override
    public void readFromFile() {
        //if the file does not exist
        File file = new File(filename);
        if (! file.exists())
        {
            try {
                HashMap<String, Rental> emptyMap = new HashMap<>();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
                oos.writeObject(emptyMap);
            } catch (IOException e) {
                throw new Error("Could not create new empty binary file!");
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            repository = (HashMap<String, Rental>) ois.readObject();
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

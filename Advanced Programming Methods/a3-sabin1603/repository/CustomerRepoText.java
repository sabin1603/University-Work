package repository;

import domain.Car;
import domain.Customer;

import java.io.*;

public class CustomerRepoText extends FileRepository<String, Customer> {

    public CustomerRepoText(String filename) throws IOException {
        super(filename);
    }

    @Override
    protected void readFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stringArray = line.split(",");
                if (stringArray.length != 3) {
                    continue;
                } else {
                    String customerId = stringArray[0].trim();
                    String customerName = stringArray[1].trim();
                    int customerAge = Integer.parseInt(stringArray[2].trim());
                    Customer customer = new Customer(customerId, customerName, customerAge);
                    repository.put(customerId, customer); // Using carId as the key in the HashMap
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Customer c: getAll())
            {
                writer.write(c.getId() + "," +
                        c.getName() + "," +
                        c.getAge() + "\n") ;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

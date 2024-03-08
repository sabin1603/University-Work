package repository;

import domain.Rental;
import domain.Car;
import domain.Customer;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentalRepoText extends FileRepository<String, Rental> {
    private CarRepoText carRepository = new CarRepoText("cars.txt");
    private CustomerRepoText customerRepository = new CustomerRepoText("customers.txt");

    public RentalRepoText(String filename) throws IOException {
        super(filename);
    }

    @Override
    protected void readFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String rentalId = parts[0].trim();
                    String carId = parts[1].trim();
                    String customerId = parts[2].trim();
                    String startDateStr = parts[3].trim();
                    String endDateStr = parts[4].trim();

                    Customer customer = customerRepository.getById(customerId);
                    Car car = carRepository.getById(carId);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date startDate = dateFormat.parse(startDateStr);
                    Date endDate = dateFormat.parse(endDateStr);

                    Rental rental = new Rental(rentalId, car, customer, startDate, endDate);
                    repository.put(rentalId, rental);
                } else {
                    System.out.println("Invalid line in the rental file: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception, if needed
            throw e;
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }

    @Override
    public void writeToFile() {
        try (FileWriter writer = new FileWriter(filename)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Rental rental : getAll()) {
                writer.write(
                        rental.getId() + "," +
                                rental.getCar().getId() + "," +
                                rental.getCustomer().getId() + "," +
                                dateFormat.format(rental.getStartDate()) + "," +
                                dateFormat.format(rental.getEndDate()) + "\n"
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

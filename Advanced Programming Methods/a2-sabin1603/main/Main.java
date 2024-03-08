package a2;

import domain.Car;
import domain.Customer;
import service.Service;
import ui.ConsoleUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        String carId1 = UUID.randomUUID().toString();
        Car car1 = new Car(carId1, "Nissan X-Trail", 2016, 220000);

        String carId2 = UUID.randomUUID().toString();
        Car car2 = new Car(carId2, "Audi A4", 2013, 280000);

        String carId3 = UUID.randomUUID().toString();
        Car car3 = new Car(carId3, "Kia Sportage", 2016, 150000);

        String carId4 = UUID.randomUUID().toString();
        Car car4 = new Car(carId4, "Volvo s60", 2012, 260000);

        String carId5 = UUID.randomUUID().toString();
        Car car5 = new Car(carId5, "Lamborghini Urus", 2023, 8000);
        service.addCar(car1);
        service.addCar(car2);
        service.addCar(car3);
        service.addCar(car4);
        service.addCar(car5);

        Customer customer1 = new Customer("Sabin", 20);
        Customer customer2 = new Customer("Calin", 19);
        Customer customer3 = new Customer("Darian", 19);
        Customer customer4 = new Customer("George", 21);
        Customer customer5 = new Customer("Badau", 18);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate1 = dateFormat.parse("2023-03-16");
            Date endDate1 = dateFormat.parse("2023-03-20");

            Date startDate2 = dateFormat.parse("2023-08-25");
            Date endDate2 = dateFormat.parse("2023-08-30");

            Date startDate3 = dateFormat.parse("2023-11-01");
            Date endDate3 = dateFormat.parse("2023-11-05");

            Date startDate4 = dateFormat.parse("2023-10-30");
            Date endDate4 = dateFormat.parse("2023-11-01");

            Date startDate5 = dateFormat.parse("2023-01-26");
            Date endDate5 = dateFormat.parse("2023-01-30");


            service.addRental(car1, customer1, startDate1, endDate1);
            service.addRental(car2, customer2, startDate2, endDate2);
            service.addRental(car3, customer3, startDate3, endDate3);
            service.addRental(car4, customer4, startDate4, endDate4);
            service.addRental(car5, customer5, startDate5, endDate5);


            ConsoleUI consoleUI = new ConsoleUI(service);
            consoleUI.run();
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }
}

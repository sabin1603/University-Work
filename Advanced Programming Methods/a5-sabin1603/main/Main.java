package a5;

import domain.Car;
import domain.Customer;
import domain.Rental;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.CarDatabaseRepository;
import repository.CustomerDatabaseRepository;
import repository.RentalDatabaseRepository;
import service.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main extends Application {
    private Service service;

    public static void main(String[] args) {
        launch(args);
    }

    CarDatabaseRepository carDatabaseRepository = new CarDatabaseRepository("Car");
    CustomerDatabaseRepository customerDatabaseRepository = new CustomerDatabaseRepository("Customer");
    RentalDatabaseRepository rentalDatabaseRepository = new RentalDatabaseRepository("Rental");

    @Override
    public void start(Stage primaryStage) {
        service = new Service(carDatabaseRepository, customerDatabaseRepository, rentalDatabaseRepository);

        primaryStage.setTitle("Car Rental System");

        Button addCarButton = new Button("Add Car");
        Button addCustomerButton = new Button("Add Customer");
        Button addRentalButton = new Button("Add Rental");

        Button updateCarButton = new Button("Update Car");
        Button updateCustomerButton = new Button("Update Customer");
        Button updateRentalButton = new Button("Update Rental");

        Button removeCarButton = new Button("Remove Car");
        Button removeCustomerButton = new Button("Remove Customer");
        Button removeRentalButton = new Button("Remove Rental");

        Button listAllCarsButton = new Button("List All Cars");
        Button listAllCustomersButton = new Button("List All Customers");
        Button listAllRentalsButton = new Button("List All Rentals");

        VBox layout = new VBox(10);
        HBox columnLayout = new HBox(10);

        columnLayout.getChildren().addAll(
                createColumn("Add", addCarButton, addCustomerButton, addRentalButton),
                createColumn("Update", updateCarButton, updateCustomerButton, updateRentalButton),
                createColumn("Remove", removeCarButton, removeCustomerButton, removeRentalButton),
                createColumn("List All", listAllCarsButton, listAllCustomersButton, listAllRentalsButton)
        );

        layout.getChildren().add(columnLayout);

        Scene scene = new Scene(layout, 500, 250);
        primaryStage.setScene(scene);

        addCarButton.setOnAction(e -> showAddCarDialog());
        addCustomerButton.setOnAction(e -> showAddCustomerDialog());
        addRentalButton.setOnAction(e -> showAddRentalDialog());

        updateCarButton.setOnAction(e -> showUpdateCarDialog());
        updateCustomerButton.setOnAction(e -> showUpdateCustomerDialog());
        updateRentalButton.setOnAction(e -> showUpdateRentalDialog());

        removeCarButton.setOnAction(e -> showRemoveCarDialog());
        removeCustomerButton.setOnAction(e -> showRemoveCustomerDialog());
        removeRentalButton.setOnAction(e -> showRemoveRentalDialog());

        listAllCarsButton.setOnAction(e -> showCarsList());
        listAllCustomersButton.setOnAction(e -> showCustomersList());
        listAllRentalsButton.setOnAction(e -> showRentalsList());

        primaryStage.show();
    }

    private VBox createColumn(String columnName, Button... buttons) {
        VBox column = new VBox(10);
        column.getChildren().add(new Label(columnName));
        column.getChildren().addAll(buttons);
        return column;
    }


    private void showAddCarDialog() {
        Dialog<Car> dialog = new Dialog<>();
        dialog.setTitle("Add Car");
        dialog.setHeaderText("Enter car details:");

        // Create UI components
        TextField carIdField = new TextField();
        TextField nameField = new TextField();
        TextField fabricationYearField = new TextField();
        TextField kmField = new TextField();

        // Set up layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("ID:"), carIdField,
                new Label("Name:"), nameField,
                new Label("Fabrication Year:"), fabricationYearField,
                new Label("Kilometers:"), kmField
        );

        dialog.getDialogPane().setContent(layout);

        // Set up buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Set up result converter
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    String carId = carIdField.getText();
                    String name = nameField.getText();
                    int fabricationYear = Integer.parseInt(fabricationYearField.getText());
                    int km = Integer.parseInt(kmField.getText());
                    Car car = new Car(carId, name, fabricationYear, km);
                    service.addCar(car);
                    return car;
                } catch (SQLException ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to add car: " + ex.getMessage());
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showAddCustomerDialog() {
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Add Customer");
        dialog.setHeaderText("Enter customer details:");

        // Create UI components
        TextField customerIdField = new TextField();
        TextField nameField = new TextField();
        TextField ageField = new TextField();

        // Set up layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("ID:"), customerIdField,
                new Label("Name:"), nameField,
                new Label("Age:"), ageField
        );

        dialog.getDialogPane().setContent(layout);

        // Set up buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Set up result converter
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    String customerId = customerIdField.getText();
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    Customer customer = new Customer(customerId, name, age);
                    service.addCustomer(customer);
                    return customer;
                } catch (SQLException ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to add customer: " + ex.getMessage());
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showAddRentalDialog() {
        Dialog<Rental> dialog = new Dialog<>();
        dialog.setTitle("Add Rental");
        dialog.setHeaderText("Enter rental details:");

        // Create UI components
        TextField rentalIdField = new TextField();
        TextField carIdField = new TextField();
        TextField customerIdField = new TextField();
        TextField startDateField = new TextField();
        TextField endDateField = new TextField();

        // Set up layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Rental ID:"), rentalIdField,
                new Label("Car ID:"), carIdField,
                new Label("Customer ID:"), customerIdField,
                new Label("Start Date (yyyy-MM-dd):"), startDateField,
                new Label("End Date (yyyy-MM-dd):"), endDateField
        );

        dialog.getDialogPane().setContent(layout);

        // Set up buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Set up result converter
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    String rentalId = rentalIdField.getText();
                    String carId = carIdField.getText();
                    String customerId = customerIdField.getText();
                    String startDateStr = startDateField.getText();
                    String endDateStr = endDateField.getText();

                    Car car = service.getCarById(carId); // Assuming you have a method to get a car by ID
                    Customer customer = service.getCustomerById(customerId); // Assuming you have a method to get a customer by name

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date startDate = dateFormat.parse(startDateStr);
                    Date endDate = dateFormat.parse(endDateStr);

                    Rental rental = new Rental(rentalId, car, customer, startDate, endDate);
                    service.addRental(rentalId, car, customer, startDate, endDate);
                    return rental;
                } catch (SQLException | ParseException ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to add rental: " + ex.getMessage());
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showUpdateCarDialog() {
        Dialog<Car> dialog = new Dialog<>();
        dialog.setTitle("Update Car");
        dialog.setHeaderText("Enter new details for the car:");

        // Retrieve the selected car from the list
        Car selectedCar = showCarSelectionDialog("Select Car to Update");

        if (selectedCar == null) {
            return;
        }

        // Create UI components
        TextField nameField = new TextField(selectedCar.getNameOfCar());
        TextField fabricationYearField = new TextField(String.valueOf(selectedCar.getFabricationYear()));
        TextField kmField = new TextField(String.valueOf(selectedCar.getKm()));

        // Set up layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Fabrication Year:"), fabricationYearField,
                new Label("Kilometers:"), kmField
        );

        dialog.getDialogPane().setContent(layout);

        // Set up buttons
        ButtonType updateButton = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButton, ButtonType.CANCEL);

        // Set up result converter
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButton) {
                try {
                    String name = nameField.getText();
                    int fabricationYear = Integer.parseInt(fabricationYearField.getText());
                    int km = Integer.parseInt(kmField.getText());

                    selectedCar.setNameOfCar(name);
                    selectedCar.setFabricationYear(fabricationYear);
                    selectedCar.setKm(km);

                    service.updateCar(selectedCar.getId(), selectedCar);
                    return selectedCar;
                } catch (SQLException ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update car: " + ex.getMessage());
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showUpdateCustomerDialog() {
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Update Customer");
        dialog.setHeaderText("Enter new details for the customer:");

        Customer selectedCustomer = showCustomerSelectionDialog("Select Customer to Update");

        if (selectedCustomer == null) {
            return;
        }

        TextField nameField = new TextField(selectedCustomer.getName());
        TextField ageField = new TextField(String.valueOf(selectedCustomer.getAge()));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Age:"), ageField
        );

        dialog.getDialogPane().setContent(layout);

        ButtonType updateButton = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButton, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButton) {
                try {
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());

                    selectedCustomer.setName(name);
                    selectedCustomer.setAge(age);

                    service.updateCustomer(selectedCustomer.getId(), selectedCustomer);
                    return selectedCustomer;
                } catch (SQLException ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update customer: " + ex.getMessage());
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showUpdateRentalDialog() {
        Dialog<Rental> dialog = new Dialog<>();
        dialog.setTitle("Update Rental");
        dialog.setHeaderText("Enter new details for the rental:");

        Rental selectedRental = showRentalSelectionDialog("Select Rental to Update");

        if (selectedRental == null) {
            return;
        }

        TextField carIdField = new TextField(selectedRental.getCar().getId());
        TextField customerIdField = new TextField(selectedRental.getCustomer().getId());

        DatePicker startDatePicker = new DatePicker(selectedRental.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        DatePicker endDatePicker = new DatePicker(selectedRental.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Car ID:"), carIdField,
                new Label("Customer ID:"), customerIdField,
                new Label("Start Date:"), startDatePicker,
                new Label("End Date:"), endDatePicker
        );

        dialog.getDialogPane().setContent(layout);

        ButtonType updateButton = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButton, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButton) {
                try {
                    String carId = carIdField.getText();
                    String customerId = customerIdField.getText();

                    LocalDate startDate = startDatePicker.getValue();
                    LocalDate endDate = endDatePicker.getValue();

                    Car car = service.getCarById(carId);
                    Customer customer = service.getCustomerById(customerId);

                    if (car != null && customer != null) {
                        Rental updatedRental = new Rental(
                                selectedRental.getId(),
                                car,
                                customer,
                                java.sql.Date.valueOf(startDate),
                                java.sql.Date.valueOf(endDate)
                        );
                        service.updateRental(selectedRental.getId(), updatedRental);
                        return updatedRental;
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Error", "Invalid car or customer ID.");
                    }
                } catch (SQLException ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update rental: " + ex.getMessage());
                }
            }
            return null;
        });

        Optional<Rental> result = dialog.showAndWait();

        // Check if the dialog was canceled or closed
        result.ifPresent(updatedRental -> showAlert(Alert.AlertType.INFORMATION, "Success", "Rental updated successfully."));
    }



    private void showRemoveCarDialog() {
        Car selectedCar = showCarSelectionDialog("Select Car to Remove");

        if (selectedCar == null) {
            return;
        }

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirm Removal");
        confirmationDialog.setContentText("Are you sure you want to remove the selected car?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            service.removeCar(selectedCar.getId());
            showAlert(Alert.AlertType.INFORMATION, "Success", "Car removed successfully.");
        }

        try {
            CarDatabaseRepository carRepository = new CarDatabaseRepository("Car");
            carRepository.removeById(selectedCar.getId());
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer removed successfully.");
        } catch (SQLException e) {
            // Handle database removal error (log, show error message, etc.)
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to remove customer from the database.");
        }

    }

    private void showRemoveCustomerDialog() {
        Customer selectedCustomer = showCustomerSelectionDialog("Select Customer to Remove");

        if (selectedCustomer == null) {
            return;
        }

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirm Removal");
        confirmationDialog.setContentText("Are you sure you want to remove the selected customer?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            service.removeCustomer(selectedCustomer.getId());
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer removed successfully.");
        }

        try {
            CustomerDatabaseRepository customerRepository = new CustomerDatabaseRepository("Customer");
            customerRepository.removeById(selectedCustomer.getId());
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer removed successfully.");
        } catch (SQLException e) {
            // Handle database removal error (log, show error message, etc.)
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to remove customer from the database.");
        }
    }

    private void showRemoveRentalDialog() {
        Rental selectedRental = showRentalSelectionDialog("Select Rental to Remove");

        if (selectedRental == null) {
            return;
        }

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirm Removal");
        confirmationDialog.setContentText("Are you sure you want to remove the selected rental?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            service.removeRental(selectedRental.getId());
            showAlert(Alert.AlertType.INFORMATION, "Success", "Rental removed successfully.");
        }

        try {
            RentalDatabaseRepository rentalRepository = new RentalDatabaseRepository("Rental");
            rentalRepository.removeById(selectedRental.getId());
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer removed successfully.");
        } catch (SQLException e) {
            // Handle database removal error (log, show error message, etc.)
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to remove customer from the database.");
        }

    }

    private void showCarsList() {
        Iterable<Car> cars = service.getAllCars();
        showListDialog("List of Cars", cars);
    }

    private void showCustomersList() {
        Iterable<Customer> customers = service.getAllCustomers();
        showListDialog("List of Customers", customers);
    }

    private void showRentalsList() {
        Iterable<Rental> rentals = service.getAllRentals();
        showListDialog("List of Rentals", rentals);
    }

    private <T> void showListDialog(String title, Iterable<T> items) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(title);

        // Create UI components
        ListView<T> listView = new ListView<>();

        // Convert iterable to a list and add to ListView
        List<T> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        listView.getItems().addAll(itemList);

        listView.setPrefWidth(400);

        // Set up layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(listView);

        layout.setPrefWidth(450);

        dialog.getDialogPane().setContent(layout);

        // Set up buttons
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton);

        dialog.showAndWait();
    }

    private Car showCarSelectionDialog(String title) {
        return showSelectionDialog(title, carDatabaseRepository.getAll());
    }

    private Customer showCustomerSelectionDialog(String title) {
        return showSelectionDialog(title, customerDatabaseRepository.getAll());
    }

    private Rental showRentalSelectionDialog(String title) {
        return showSelectionDialog(title, rentalDatabaseRepository.getAll());
    }

    private <T> T showSelectionDialog(String title, Iterable<T> items) {
        Dialog<T> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText("Select an item:");

        ListView<T> listView = new ListView<>();
        List<T> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        listView.getItems().addAll(itemList);

        listView.setPrefWidth(400);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(listView);

        layout.setPrefWidth(400);

        dialog.getDialogPane().setContent(layout);

        ButtonType selectButton = new ButtonType("Select", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(selectButton, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == selectButton) {
                return listView.getSelectionModel().getSelectedItem();
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

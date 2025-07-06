package managers;

import models.Car;
import services.CarRentalService;
import services.FileService;

import java.util.List;
import java.util.Scanner;

public class RentalManager {
    private CarRentalService service;
    private FileService fileService;

    /**
     * Create a rental manager with the given service and file handler
     */
    public RentalManager(CarRentalService service, FileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    /**
     * Start the main user interaction loop for the car rental system
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Welcome to the Car Rental System");
        printCommands();

        while (isRunning) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Exit")) {
                fileService.writeCars(service.getAllCars());
                System.out.println("Saved. Goodbye!");
                isRunning = false;
                scanner.close();
            } else if (input.startsWith("Add")) {
                System.out.println("Enter: id,make,model,year,type,status");
                String[] parts = scanner.nextLine().split(",");
                for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();
                if (parts.length == 6) {
                    try {
                        service.addCar(new Car(
                            Integer.parseInt(parts[0]), parts[1], parts[2],
                            Integer.parseInt(parts[3]), parts[4], parts[5], ""
                        ));
                        System.out.println("Car added.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Id and year must be numbers.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter all 6 fields separated by commas.");
                }
            } else if (input.startsWith("Rent")) {
                String[] parts = input.split(" ");
                int id = Integer.parseInt(parts[1]);
                Car car = service.searchById(id);
                if (car == null) {
                    System.out.println("Car not found.");
                } else if (car.getStatus().equalsIgnoreCase("Removed")) {
                    System.out.println("This car has been removed and cannot be rented.");
                } else if (!car.getStatus().equalsIgnoreCase("Available")) {
                    System.out.println("This car is not available for rent.");
                } else {
                    System.out.print("Customer name: ");
                    String name = scanner.nextLine();
                    String startDate = "";
                    String returnDate = "";
                    while (true) {
                        System.out.print("Rental start date (DD-MM-YYYY): ");
                        startDate = scanner.nextLine();
                        if (startDate.matches("\\d{2}-\\d{2}-\\d{4}")) break;
                        System.out.println("Invalid date format. Please use DD-MM-YYYY.");
                    }
                    while (true) {
                        System.out.print("Estimated return date (DD-MM-YYYY): ");
                        returnDate = scanner.nextLine();
                        if (returnDate.matches("\\d{2}-\\d{2}-\\d{4}")) break;
                        System.out.println("Invalid date format. Please use DD-MM-YYYY.");
                    }
                    service.rent(id, name, startDate, returnDate);
                }
            } else if (input.startsWith("Return")) {
                int id = Integer.parseInt(input.split(" ")[1]);
                service.returnCar(id);
            } else if (input.startsWith("Edit")) {
                int id = Integer.parseInt(input.split(" ")[1]);
                Car car = service.searchById(id);
                if (car != null) {
                    System.out.println("Enter new details as: id,make,model,year,type");
                    String[] parts = scanner.nextLine().split(",");
                    for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();
                    if (parts.length == 5) {
                        try {
                            Car newData = new Car(
                                Integer.parseInt(parts[0]), parts[1], parts[2],
                                Integer.parseInt(parts[3]), parts[4], car.getStatus(), car.getCurrentRenter(), car.getRentalStartDate(), car.getEstimatedReturnDate()
                            );
                            service.editCar(id, newData);
                            System.out.println("Car details updated.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Id and year must be numbers.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter all 5 fields separated by commas.");
                    }
                } else {
                    System.out.println("Car not found.");
                }
            } else if (input.startsWith("Remove")) {
                int id = Integer.parseInt(input.split(" ")[1]);
                service.removeCar(id);
            } else if (input.equalsIgnoreCase("List all")) {
                for (Car car : service.getAllCars()) {
                    System.out.println(car);
                }
            } else if (input.startsWith("List")) {
                for (Car car : service.listAvailableCars()) {
                    System.out.println(car);
                }
            } else if (input.startsWith("Search")) {
                System.out.print("Enter search term: ");
                String term = scanner.nextLine().trim().toLowerCase();
                List<Car> results = new java.util.ArrayList<>();
                for (Car car : service.getAllCars()) {
                    if (String.valueOf(car.getId()).equalsIgnoreCase(term)
                        || car.getMake().toLowerCase().contains(term)
                        || car.getModel().toLowerCase().contains(term)
                        || String.valueOf(car.getYear()).equalsIgnoreCase(term)
                        || car.getType().toLowerCase().contains(term)
                        || car.getStatus().toLowerCase().contains(term)) {
                        results.add(car);
                    }
                }
                if (results.isEmpty()) {
                    System.out.println("No cars found matching the search term.");
                } else {
                    results.forEach(System.out::println);
                }
            } else {
                System.out.println("Unknown command.");
                printCommands();
            }
        }
    }

    /**
     * Print the list of available commands for the user
     */
    private void printCommands() {
        System.out.println("Commands: Add, Rent <id>, Return <id>, Edit <id>, Remove <id>, List, List all, Search, Exit");
    }
}
package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public void display() {

        init();

        int command = 0;

        do {

            displayMenu();

            //  SAFE INPUT HANDLING
            if (scanner.hasNextInt()) {
                command = scanner.nextInt();
                scanner.nextLine(); // clear buffer
            } else {
                System.out.println("Invalid input. Please enter a correct option  number.");
                scanner.nextLine(); // clear bad input like "1rt"
                continue;
            }

            switch (command) {

                case 1:
                    processAllVehiclesRequest();
                    break;

                case 2:
                    processGetByPriceRequest();
                    break;

                case 3:
                    processGetByMakeModelRequest();
                    break;

                case 4:
                    processGetByYearRequest();
                    break;

                case 5:
                    processGetByColorRequest();
                    break;

                case 6:
                    processGetByMileageRequest();
                    break;

                case 7:
                    processGetByTypeRequest();
                    break;

                case 8:
                    processAddVehicleRequest();
                    break;

                case 9:
                    processRemoveVehicleRequest();
                    break;

                case 99:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (command != 99);
    }

    //  INIT

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
    }

    // MENU

    private void displayMenu() {

        System.out.println("\n=====================================");
        System.out.println("        CAR DEALERSHIP SYSTEM");
        System.out.println("===================================== \n");

        System.out.println("  1  - List ALL vehicles");
        System.out.println("  2  - Find vehicles within a price range");
        System.out.println("  3  - Find vehicles by make / model");
        System.out.println("  4  - Find vehicles by year range");
        System.out.println("  5  - Find vehicles by color");
        System.out.println("  6  - Find vehicles by mileage range");
        System.out.println("  7  - Find vehicles by type");
        System.out.println("  8  - Add a vehicle");
        System.out.println("  9  - Remove a vehicle");
        System.out.println("  99 - Quit");

        System.out.println("\n-------------------------------------");
        System.out.print("Enter command: ");
    }

    // LIST ALL

    private void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    // PRICE

    private void processGetByPriceRequest() {

        System.out.print("Minimum price: ");
        double min = scanner.nextDouble();

        System.out.print("Maximum price: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    //  MAKE / MODEL

    private void processGetByMakeModelRequest() {

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    //  YEAR

    private void processGetByYearRequest() {

        System.out.print("Minimum year: ");
        int min = scanner.nextInt();

        System.out.print("Maximum year: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    //  COLOR

    private void processGetByColorRequest() {

        System.out.print("Color: ");
        String color = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color));
    }

    // MILEAGE

    private void processGetByMileageRequest() {

        System.out.print("Minimum mileage: ");
        int min = scanner.nextInt();

        System.out.print("Maximum mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    //  TYPE

    private void processGetByTypeRequest() {

        System.out.print("Vehicle type: ");
        String type = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByType(type));
    }

    //  ADD VEHICLE

    private void processAddVehicleRequest() {

        System.out.print("VIN: ");
        String vin = scanner.nextLine();

        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Mileage: ");
        int mileage = scanner.nextInt();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);

        dealership.addVehicle(vehicle);
        new DealershipFileManager().saveDealership(dealership);

        System.out.println("Vehicle added and saved.");
    }

    // REMOVE VEHICLE

    private void processRemoveVehicleRequest() {

        System.out.print("Enter VIN to remove: ");
        String vin = scanner.nextLine();

        Vehicle vehicleToRemove = null;

        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin().equalsIgnoreCase(vin)) {
                vehicleToRemove = v;
                break;
            }
        }

        if (vehicleToRemove != null) {

            dealership.removeVehicle(vehicleToRemove);
            new DealershipFileManager().saveDealership(dealership);

            System.out.println("Vehicle removed and saved.");

        } else {
            System.out.println("Vehicle not found.");
        }
    }

    // DISPLAY

    private void displayVehicles(ArrayList<Vehicle> vehicles) {

        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
}
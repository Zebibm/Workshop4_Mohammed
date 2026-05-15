package org.example;

import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);
    private DealershipFileManager fileManager = new DealershipFileManager();
    private ContractFileManager contractFileManager = new ContractFileManager();

    public void display() {

        init();

        int command;

        do {
            displayMenu();

            command = readInt("Enter command: ");

            switch (command) {

                case 1 -> processAllVehiclesRequest();
                case 2 -> processGetByPriceRequest();
                case 3 -> processGetByMakeModelRequest();
                case 4 -> processGetByYearRequest();
                case 5 -> processGetByColorRequest();
                case 6 -> processGetByMileageRequest();
                case 7 -> processGetByTypeRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 10 -> processSellOrLeaseRequest();
                case 99 -> System.out.println("Goodbye!");

                default -> System.out.println("Invalid option.");
            }

        } while (command != 99);
    }

    // ================= INIT =================
    private void init() {
        dealership = fileManager.getDealership();

        if (dealership == null) {
            System.out.println("Error loading dealership data.");
            System.exit(0);
        }
    }

    // ================= MENU =================
    private void displayMenu() {

        System.out.println("\n=====================================");
        System.out.println("        CAR DEALERSHIP SYSTEM");
        System.out.println("=====================================");

        System.out.println("1 - List ALL vehicles");
        System.out.println("2 - Price range");
        System.out.println("3 - Make / Model");
        System.out.println("4 - Year range");
        System.out.println("5 - Color");
        System.out.println("6 - Mileage range");
        System.out.println("7 - Type");
        System.out.println("8 - Add vehicle");
        System.out.println("9 - Remove vehicle");
        System.out.println("10 - Sell / Lease vehicle");
        System.out.println("99 - Quit");
    }

    // ================= INPUT HELPERS =================

    private int readInt(String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine();
            }
        }
    }

    private double readDouble(String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine();
            }
        }
    }

    private String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    // ================= DISPLAY TABLE =================

    private void displayVehicles(ArrayList<Vehicle> vehicles) {

        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        AsciiTable at = new AsciiTable();

        at.addRule();
        at.addRow("VIN", "Year", "Make", "Model", "Type", "Color", "Miles", "Price");
        at.addRule();

        for (Vehicle v : vehicles) {
            at.addRow(
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getType(),
                    v.getColor(),
                    v.getMileage(),
                    String.format("%.2f", v.getPrice())
            );
            at.addRule();
        }

        System.out.println(at.render());
    }

    // ================= FEATURES =================

    private void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void processGetByPriceRequest() {
        double min = readDouble("Min price: ");
        double max = readDouble("Max price: ");
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    private void processGetByMakeModelRequest() {
        String make = readString("Make: ");
        String model = readString("Model: ");
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void processGetByYearRequest() {
        int min = readInt("Min year: ");
        int max = readInt("Max year: ");
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    private void processGetByColorRequest() {
        String color = readString("Color: ");
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processGetByMileageRequest() {
        int min = readInt("Min mileage: ");
        int max = readInt("Max mileage: ");
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    private void processGetByTypeRequest() {
        String type = readString("Type: ");
        displayVehicles(dealership.getVehiclesByType(type));
    }

    // ================= ADD VEHICLE =================

    private void processAddVehicleRequest() {

        String vin = readString("Enter VIN: ").trim();
        int year = readInt("Year: ");
        String make = readString("Make: ");
        String model = readString("Model: ");
        String type = readString("Type: ");
        String color = readString("Color: ");
        int mileage = readInt("Mileage: ");
        double price = readDouble("Price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);

        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle added successfully.");
    }

    // ================= REMOVE VEHICLE =================

    private void processRemoveVehicleRequest() {

        String vin = readString("Enter VIN: ").trim();

        Vehicle found = null;

        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin().equalsIgnoreCase(vin)) {
                found = v;
                break;
            }
        }

        if (found != null) {
            dealership.removeVehicle(found);
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    // ================= SELL / LEASE =================

    private void processSellOrLeaseRequest() {

        String vin = readString("Enter VIN: ");

        Vehicle selectedVehicle = null;

        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin().trim().equalsIgnoreCase(vin)) {
                selectedVehicle = v;
                break;
            }
        }

        if (selectedVehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        String date = readString("Enter date (YYYYMMDD): ");
        String name = readString("Customer name: ");
        String email = readString("Customer email: ");

        String type = readString("Sale or Lease? ").toLowerCase();

        Contract contract;

        if (type.equals("sale")) {

            String financeInput = readString("Finance? (yes/no): ");
            boolean finance = financeInput.equalsIgnoreCase("yes");

            contract = new SalesContract(date, name, email, selectedVehicle, finance);

        } else if (type.equals("lease")) {

            contract = new LeaseContract(date, name, email, selectedVehicle);

        } else {
            System.out.println("Invalid option.");
            return;
        }

        contractFileManager.saveContract(contract);

        dealership.removeVehicle(selectedVehicle);
        fileManager.saveDealership(dealership);

        System.out.println("Contract completed successfully!");
    }
}
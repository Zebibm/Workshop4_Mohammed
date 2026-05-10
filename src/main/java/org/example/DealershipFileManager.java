package org.example;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DealershipFileManager {

    // ================= LOAD FILE =================
    public Dealership getDealership() {

        Dealership dealership = null;

        try {

            // Load from resources folder
            InputStream inputStream =
                    new FileInputStream("src/main/resources/inventory.csv");

            Scanner scanner = new Scanner(inputStream);

            // First line = dealership info
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");

            String name = parts[0];
            String address = parts[1];
            String phone = parts[2];

            dealership = new Dealership(name, address, phone);

            // Remaining lines = vehicles
            while (scanner.hasNextLine()) {

                line = scanner.nextLine();
                parts = line.split("\\|");

                Vehicle vehicle = new Vehicle(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        parts[2],
                        parts[3],
                        parts[4],
                        parts[5],
                        Integer.parseInt(parts[6]),
                        Double.parseDouble(parts[7])
                );

                dealership.addVehicle(vehicle);
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }

        return dealership;
    }

    // ================= SAVE FILE =================
    public void saveDealership(Dealership dealership) {

        try {

            // Save back into SAME file
            FileWriter fileWriter =
                    new FileWriter("src/main/resources/inventory.csv");

            PrintWriter writer = new PrintWriter(fileWriter);

            // Write dealership info
            writer.println(
                    dealership.getName() + "|" +
                            dealership.getAddress() + "|" +
                            dealership.getPhone()
            );

            // Write vehicles
            ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

            for (Vehicle v : vehicles) {

                writer.println(
                        v.getVin() + "|" +
                                v.getYear() + "|" +
                                v.getMake() + "|" +
                                v.getModel() + "|" +
                                v.getType() + "|" +
                                v.getColor() + "|" +
                                v.getMileage() + "|" +
                                v.getPrice()
                );
            }


            writer.close();

            System.out.println("Inventory saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
}
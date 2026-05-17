package org.example;

import java.io.FileWriter;
import java.io.PrintWriter;

// Handles saving contracts to a file
public class ContractFileManager {

    private static final String FILE = "src/main/resources/contracts.csv";

    // Saves either SALE or LEASE contract
    public void saveContract(Contract c) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE, true))) {

            // Check if contract is a sale
            if (c instanceof SalesContract sc) {

                writer.println(
                        "SALE|" + sc.getDate() + "|" +
                                sc.getCustomerName() + "|" +
                                sc.getCustomerEmail() + "|" +
                                sc.getVehicleSold().getVin() + "|" +
                                sc.getVehicleSold().getYear() + "|" +
                                sc.getVehicleSold().getMake() + "|" +
                                sc.getVehicleSold().getModel() + "|" +
                                sc.getVehicleSold().getType() + "|" +
                                sc.getVehicleSold().getColor() + "|" +
                                sc.getVehicleSold().getMileage() + "|" +
                                sc.getVehicleSold().getPrice() + "|" +
                                String.format("%.2f", sc.getTotalPrice()) + "|" +
                                (sc.isFinance() ? "YES" : "NO") + "|" +
                                String.format("%.2f", sc.getMonthlyPayment())
                );

            }
            // Check if contract is a lease
            else if (c instanceof LeaseContract lc) {

                writer.println(
                        "LEASE|" + lc.getDate() + "|" +
                                lc.getCustomerName() + "|" +
                                lc.getCustomerEmail() + "|" +
                                lc.getVehicleSold().getVin() + "|" +
                                lc.getVehicleSold().getYear() + "|" +
                                lc.getVehicleSold().getMake() + "|" +
                                lc.getVehicleSold().getModel() + "|" +
                                lc.getVehicleSold().getType() + "|" +
                                lc.getVehicleSold().getColor() + "|" +
                                lc.getVehicleSold().getMileage() + "|" +
                                lc.getVehicleSold().getPrice() + "|" +
                                String.format("%.2f", lc.getTotalPrice()) + "|" +
                                String.format("%.2f", lc.getMonthlyPayment())
                );
            }

        } catch (Exception e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}
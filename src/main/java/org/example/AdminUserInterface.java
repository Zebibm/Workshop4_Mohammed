package org.example;

import de.vandermeer.asciitable.AsciiTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


 // ADMIN USER INTERFACE
 // Reads and displays saved contracts from file

public class AdminUserInterface {

    private static final String PASSWORD = "admin123";
    private static final String FILE_PATH = "contracts.csv";

    public void display() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===== ADMIN LOGIN =====");
        System.out.print("Enter password: ");

        String input = scanner.nextLine();

        if (!input.equals(PASSWORD)) {
            System.out.println("Wrong password. Access denied.");
            return;
        }

        System.out.println("\n===== CONTRACTS =====");

        List<String[]> contracts = loadContracts();

        if (contracts.isEmpty()) {
            System.out.println("No contracts found.");
            return;
        }

        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("TYPE", "DATE", "NAME", "EMAIL", "VEHICLE", "TOTAL");
        table.addRule();

        for (String[] c : contracts) {

            table.addRow(
                    c[0], // TYPE
                    c[1], // DATE
                    c[2], // NAME
                    c[3], // EMAIL
                    c[4], // VIN or vehicle field
                    c[c.length - 2] // TOTAL PRICE
            );

            table.addRule();
        }

        System.out.println(table.render());
    }

    // Load contracts from file
    private List<String[]> loadContracts() {

        List<String[]> list = new ArrayList<>();

        try {

            Scanner fileScanner = new Scanner(new File(FILE_PATH));

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                list.add(parts);
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("Error reading contracts file.");
        }

        return list;
    }
}
package org.example;

import java.io.File;
import java.util.Scanner;

// admin tool to view saved contracts
public class AdminUserInterface {

    public void display() {

        try {

            // Reads contracts file line by line
            Scanner fileScanner =
                    new Scanner(new File("contracts.csv"));

            System.out.println("\n===== CONTRACTS =====");

            // Print each contract line
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }

            fileScanner.close();

        } catch (Exception e) {

            System.out.println("Error reading contracts.");
        }
    }
}
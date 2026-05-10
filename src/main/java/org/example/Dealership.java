package org.example;

import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> vehicles;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        this.vehicles = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    // Vehicle methods
    public ArrayList<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    // Search by price
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {

        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getPrice() >= min &&
                    vehicle.getPrice() <= max) {

                matches.add(vehicle);
            }
        }

        return matches;
    }

    // Search by make/model
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {

        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getMake().equalsIgnoreCase(make) &&
                    vehicle.getModel().equalsIgnoreCase(model)) {

                matches.add(vehicle);
            }
        }

        return matches;
    }

    // Search by year
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {

        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getYear() >= min &&
                    vehicle.getYear() <= max) {

                matches.add(vehicle);
            }
        }

        return matches;
    }

    // Search by color
    public ArrayList<Vehicle> getVehiclesByColor(String color) {

        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getColor().equalsIgnoreCase(color)) {

                matches.add(vehicle);
            }
        }

        return matches;
    }

    // Search by mileage
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {

        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getMileage() >= min &&
                    vehicle.getMileage() <= max) {

                matches.add(vehicle);
            }
        }

        return matches;
    }

    // Search by type
    public ArrayList<Vehicle> getVehiclesByType(String type) {

        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getType().equalsIgnoreCase(type)) {

                matches.add(vehicle);
            }
        }

        return matches;
    }
}
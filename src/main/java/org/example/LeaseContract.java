package org.example;

// Handles leasing transactions
public class LeaseContract extends Contract {

    // Lease-specific values
    private double expectedEndingValue;
    private double leaseFee;

    // Constructor calculates lease values
    public LeaseContract(String date, String name, String email, Vehicle vehicle) {
        super(date, name, email, vehicle);

        double price = vehicle.getPrice();

        // Lease rules
        this.expectedEndingValue = price * 0.5;
        this.leaseFee = price * 0.07;
    }

    // Total lease cost calculation
    @Override
    public double getTotalPrice() {

        double price = getVehicleSold().getPrice();

        double total = (price - expectedEndingValue) + leaseFee;

        // Round for clean output
        return Math.round(total * 100.0) / 100.0;
    }

    // Monthly payment for lease (36 months, 4% interest)
    @Override
    public double getMonthlyPayment() {

        double price = getTotalPrice();

        double rate = 4.0;
        int months = 36;

        double monthlyRate = (rate / 100) / 12;

        double payment = (price * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -months));

        return Math.round(payment * 100.0) / 100.0;
    }
}
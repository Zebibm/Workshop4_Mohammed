package org.example;

// Handles vehicle sale transactions
public class SalesContract extends Contract {

    // Sales-specific fields
    private double salesTax;
    private double recordingFee = 100;
    private double processingFee;
    private boolean finance;

    // Constructor calculates fees based on vehicle price
    public SalesContract(String date, String name, String email,
                         Vehicle vehicle, boolean finance) {

        super(date, name, email, vehicle);

        this.finance = finance;

        double price = vehicle.getPrice();

        // 5% sales tax
        this.salesTax = price * 0.05;

        // Processing fee depends on price
        this.processingFee = price < 10000 ? 295 : 495;
    }

    // Total price = vehicle + tax + fees
    @Override
    public double getTotalPrice() {
        double total = getVehicleSold().getPrice()
                + salesTax + recordingFee + processingFee;

        // Round to 2 decimals for clean money format
        return Math.round(total * 100.0) / 100.0;
    }

    // Monthly payment calculation if financing is selected
    @Override
    public double getMonthlyPayment() {

        // If no financing, payment is 0
        if (!finance) return 0;

        double price = getTotalPrice();

        // Interest rate and loan duration rules
        double rate = price >= 10000 ? 4.25 : 5.25;
        int months = price >= 10000 ? 48 : 24;

        double monthlyRate = (rate / 100) / 12;

        double payment = (price * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -months));

        // Round result for clean output
        return Math.round(payment * 100.0) / 100.0;
    }

    // Used for file output
    public boolean isFinance() {
        return finance;
    }
}
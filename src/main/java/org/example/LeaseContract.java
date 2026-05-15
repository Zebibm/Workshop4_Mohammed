package org.example;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date,
                         String customerName,
                         String customerEmail,
                         Vehicle vehicleSold) {

        super(date, customerName, customerEmail, vehicleSold);

        double price = vehicleSold.getPrice();

        this.expectedEndingValue = price * 0.5;
        this.leaseFee = price * 0.07;
    }

    //  GETTERS

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    //  TOTAL PRICE

    @Override
    public double getTotalPrice() {

        return getVehicleSold().getPrice() + leaseFee;
    }

    //  MONTHLY PAYMENT

    @Override
    public double getMonthlyPayment() {

        double price = getTotalPrice();

        double annualInterestRate = 4.0;
        int months = 36;

        double monthlyRate = (annualInterestRate / 100) / 12;

        return (price * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -months));
    }
}
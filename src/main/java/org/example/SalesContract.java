package org.example;

public class SalesContract extends Contract {

    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    public SalesContract(String date,
                         String customerName,
                         String customerEmail,
                         Vehicle vehicleSold,
                         boolean finance) {

        super(date, customerName, customerEmail, vehicleSold);

        this.finance = finance;

        double vehiclePrice = vehicleSold.getPrice();

        this.salesTaxAmount = vehiclePrice * 0.05;
        this.recordingFee = 100;

        if (vehiclePrice < 10000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }
    }

    // GETTERS

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    //  TOTAL PRICE

    @Override
    public double getTotalPrice() {

        return getVehicleSold().getPrice()
                + salesTaxAmount
                + recordingFee
                + processingFee;
    }

    //  MONTHLY PAYMENT

    @Override
    public double getMonthlyPayment() {

        if (!finance) {
            return 0;
        }

        double totalPrice = getTotalPrice();

        double annualInterestRate;
        int months;

        if (totalPrice >= 10000) {
            annualInterestRate = 4.25;
            months = 48;
        } else {
            annualInterestRate = 5.25;
            months = 24;
        }

        double monthlyRate = (annualInterestRate / 100) / 12;

        return (totalPrice * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -months));
    }
}
package org.example;

// Handles vehicle SALE contracts (cash or finance)
public class SalesContract extends Contract {

    // ================= FIELDS =================
    private double salesTaxAmount;
    private double recordingFee = 100;
    private double processingFee;
    private boolean finance;

    // ================= CONSTRUCTOR =================
    public SalesContract(String date,
                         String customerName,
                         String customerEmail,
                         Vehicle vehicleSold,
                         boolean finance) {

        super(date, customerName, customerEmail, vehicleSold);

        this.finance = finance;

        double price = vehicleSold.getPrice();

        // 5% sales tax
        this.salesTaxAmount = price * 0.05;

        // Processing fee rules
        if (price < 10000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }
    }

    // ================= TOTAL PRICE =================
    @Override
    public double getTotalPrice() {

        // IMPORTANT: no rounding (required for unit tests)
        return getVehicleSold().getPrice()
                + salesTaxAmount
                + recordingFee
                + processingFee;
    }

    // ================= MONTHLY PAYMENT =================
    @Override
    public double getMonthlyPayment() {

        // If not financing → no monthly payment
        if (!finance) {
            return 0;
        }

        double price = getTotalPrice();

        // Standard workshop rule (typical requirement)
        double annualInterestRate = 0.0425; // 4.25%
        int months = 48;

        double monthlyRate = annualInterestRate / 12;

        double payment = (price * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -months));

        return payment; // no rounding for unit tests
    }

    // ================= GETTER =================
    public boolean isFinance() {
        return finance;
    }
}
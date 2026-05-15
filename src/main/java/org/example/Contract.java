package org.example;

// Abstract class because we never create a generic contract
// We only create SalesContract or LeaseContract
public abstract class Contract {

    // Common contract information
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    // Constructor for shared fields
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // Getters for shared data
    public String getDate() { return date; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public Vehicle getVehicleSold() { return vehicleSold; }

    // Abstract methods (must be implemented in child classes)
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}
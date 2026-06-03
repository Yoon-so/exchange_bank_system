package model;

public class Transaction {
    private String type;
    private double amount;
    private String currency;
    private String date;

    public Transaction(String type, double amount, String currency, String date) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }
    @Override
    public String toString() {
        return type + ": " + amount + " " + currency + " (" + date + ")";
    }
    public String toFileString() {
        return type + " | " + amount + " | " + currency + " | " + date;
    }
}

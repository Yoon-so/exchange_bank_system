package model;

public class Account {
    private String owner;

    private double krwBalance;
    private double usdBalance;
    private double eurBalance;
    private double jpyBalance;

    public Account(String owner, double krwBalance) {
        this.owner = owner;

        this.krwBalance = krwBalance;
        this.usdBalance = 0.0;
        this.eurBalance = 0.0;
        this.jpyBalance = 0.0;
    }

    public synchronized void depositKRW(double amount) { //внесение?
        if (amount <= 0) {
            return;
        }
        krwBalance += amount;
    }

    public synchronized boolean withdrawKRW(double amount) { //снятие?
        if (amount <= 0 || amount > krwBalance) {
            return false;
        }

        krwBalance -= amount;
        return true;
    }

    //Add functions for other currencies
    public synchronized void addCurrency(String currency, double amount) {
        if (amount <= 0) {
            return;
        }

        switch (currency.toUpperCase()) {
            case "USD":
                usdBalance += amount;
                break;
            case "EUR":
                eurBalance += amount;
                break;
            case "JPY":
                jpyBalance += amount;
                break;
            default:
                throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
    }

    //Get functions
    public String getOwner() {
        return owner;
    }

    public double getKRWBalance() {
        return krwBalance;
    }

    public double getUSDBalance() {
        return usdBalance;
    }

    public double getEURBalance() {
        return eurBalance;
    }

    public double getJPYBalance() {
        return jpyBalance;
    }
}

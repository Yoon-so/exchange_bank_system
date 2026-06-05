package model;

public class Transaction {
    private String type;
    private double amount;
    private String currency;
    private String date;
    private String balanceInfo;

    public Transaction(String type, double amount, String currency, String date, String balanceInfo) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.balanceInfo = balanceInfo;
    }
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
    
    public String getCurrency() {
        return currency;
    }

    public String getDate() {
        return date;
    }

    public String getBalanceInfo() {
        return balanceInfo;
    }

    @Override
    public String toString() {
        return type + ": " + amount + " " + currency + " (" + date + ")" + " | " + balanceInfo;
    }
    public String toFileString() {
        return type + " | " + amount + " | " + currency + " | " + date + " | " + balanceInfo;
    }

}

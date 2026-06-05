package model;

public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount, String currency, String date, String balanceInfo) {
        super("DEPOSIT", amount, currency, date, balanceInfo);
    }

    @Override
    public String getDescription() {
        return "Deposit: " + formatAmount(getAmount()) + " " + getCurrency();
    }
}

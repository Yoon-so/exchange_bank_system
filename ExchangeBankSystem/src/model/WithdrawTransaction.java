package model;

public class WithdrawTransaction extends Transaction {

    public WithdrawTransaction(double amount, String currency, String date, String balanceInfo) {
        super("WITHDRAW", amount, currency, date, balanceInfo);
    }

    @Override
    public String getDescription() {
        return "Withdraw: " + formatAmount(getAmount()) + " " + getCurrency();
    }
}

package model;

public class ExchangeTransaction extends Transaction {

    public ExchangeTransaction(double amount, String currency, String date, String balanceInfo) {
        super("EXCHANGE", amount, currency, date, balanceInfo);
    }

    @Override
    public String getDescription() {
        return "Exchange: " + formatAmount(getAmount()) + " KRW -> " + getCurrency();
    }
}

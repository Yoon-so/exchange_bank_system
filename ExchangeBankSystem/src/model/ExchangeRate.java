package model;

public class ExchangeRate {
    private double krwRate;
    private double usdRate;
    private double jpyRate;
    private double eurRate;

    public ExchangeRate() {
        this.krwRate = 1.0; 

        // 1 KRW = ... 
        this.usdRate = 0.00085;
        this.jpyRate = 0.11; 
        this.eurRate = 0.00075;
    }

    public double getRate(String currency) {
        switch (currency.toUpperCase()) {
            case "KRW":
                return krwRate;
            case "USD":
                return usdRate;
            case "JPY":
                return jpyRate;
            case "EUR":
                return eurRate;
            default:
                throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
    }

    public double convert(double amount, String currency) {
        double result = amount * getRate(currency);
        return Math.round(result * 100.0) / 100.0;
    }

    public double usdToKrw(double amount) {
        return amount / usdRate;
    }
}

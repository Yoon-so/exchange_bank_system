package controller;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainController {

    private Account account;
    private ExchangeRate exchangeRate;
    private ArrayList<Transaction> transactionHistory;
    private FileManager fileManager;

    public MainController() {
        account = new Account(
                "John Doe",     
                100000
        );

        exchangeRate = new ExchangeRate();
        transactionHistory = new ArrayList<>();
        fileManager = new FileManager();
    }

    //Deposit
    public void deposit(double amount) {
        account.depositKRW(amount);

        transactionHistory.add(
                new Transaction(
                    "DEPOSIT",
                    amount,
                    "KRW",
                    java.time.LocalDate.now().toString()
            )
        );
    }
     
    //Withdraw
    public boolean withdraw(double amount) {
        boolean success = account.withdrawKRW(amount);

        if (success) {
            transactionHistory.add(
                    new Transaction(
                        "WITHDRAW",
                        amount,
                        "KRW",
                        java.time.LocalDate.now().toString()
                )
            );
        }
        return success;
    }

    //Exchange
    public double exchange(double amount, String currency) {

        if (!account.withdrawKRW(amount)) {
            return -1;
        }

        double convertedAmount = exchangeRate.convert(amount, currency);

        account.addCurrency(currency, convertedAmount);
        
        transactionHistory.add(
                new Transaction(
                        "EXCHANGE",
                        amount,
                        currency,
                        java.time.LocalDate.now().toString()
                )
        );
        return convertedAmount;
    }

    //Account Information
    public String getOwner() {
        return account.getOwner();
    }

    public double getKRWBalance() {
        return account.getKRWBalance();
    }

    public double getUSDBalance() {
        return account.getUSDBalance();
    }

    public double getEURBalance() {
        return account.getEURBalance();
    }

    public double getJPYBalance() {
        return account.getJPYBalance();
    }

    //Transaction History
    public ArrayList<Transaction> geTransactions() {
        return transactionHistory;
    }

    //Save Data
    public void saveData() {
        fileManager.saveTransaction(transactionHistory);
    }
 }

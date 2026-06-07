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
        exchangeRate = new ExchangeRate();
        
        fileManager = new FileManager();
        account = fileManager.loadAccount();
        transactionHistory = fileManager.loadTransactions();
    }

    //Deposit
    public void deposit(double amount) {
        if (amount <= 0) {
            return;
        }

        account.depositKRW(amount);

        transactionHistory.add(
                new DepositTransaction(
                    amount,
                    "KRW",
                    LocalDate.now().toString(),
                    account.getBalanceInfo("KRW")
            )
        );
    }
     
    //Withdraw
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        
        boolean success = account.withdrawKRW(amount);

        if (success) {
            transactionHistory.add(
                    new WithdrawTransaction(
                        amount,
                        "KRW",
                        java.time.LocalDate.now().toString(),
                        account.getBalanceInfo("KRW")
                )
            );
        }
        return success;
    }

    //Exchange
    public double exchange(double amount, String currency) {
        if (amount <= 0 || !account.withdrawKRW(amount) ) {
            return -1;
        }

        double convertedAmount = exchangeRate.convert(amount, currency);

        account.addCurrency(currency, convertedAmount);
        
        transactionHistory.add(
                new ExchangeTransaction(
                        amount,
                        currency,
                        java.time.LocalDate.now().toString(),
                        account.getBalanceInfo(currency)
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

    public double getRate(String currency) {
        return exchangeRate.getRate(currency);
    }

    //Transaction History
    public ArrayList<Transaction> getTransactions() {
        return transactionHistory;
    }

    //Save Data
    public void saveData() {
        fileManager.saveTransaction(transactionHistory);
        fileManager.saveAccount(account);
    }

    public void runThreadDemo() {

        Account testAccount = new Account("Test User", 100000);

        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                testAccount.depositKRW(100);
            }
        });

        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                testAccount.withdrawKRW(100);
            }
        });

        depositThread.start();
        withdrawThread.start();

        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Balance: " + testAccount.getKRWBalance());
    }
 }

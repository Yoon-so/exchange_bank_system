package model;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class FileManager {

    private static final String FILE_NAME = "transaction_history.txt";
    private static final String ACCOUNT_FILE = "account_data.txt";

    //Save transaction
    public void saveTransaction(ArrayList<Transaction> transactions) {

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Transaction t : transactions) {
               writer.write(t.toFileString());
               writer.write(System.lineSeparator());
            }
            System.out.println("Transaction history saved to " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Error saving transaction history: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Load transactions history
    public ArrayList<Transaction> loadTransactions() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                if (data.length == 5) {
                    Transaction transaction = new Transaction(data[0].trim(), Double.parseDouble(data[1].trim()), data[2].trim(), data[3].trim(), data[4].trim());

                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("No saved transaction history found.");
        }
        return transactions;
    }

    //Save account data
    public void saveAccount(Account account) {

        try (FileWriter writer = new FileWriter(ACCOUNT_FILE)) {
            
            writer.write(account.getOwner());
            writer.write(System.lineSeparator());

            writer.write(String.valueOf(account.getKRWBalance()));
            writer.write(System.lineSeparator());

            writer.write(String.valueOf(account.getUSDBalance()));
            writer.write(System.lineSeparator());

            writer.write(String.valueOf(account.getEURBalance()));
            writer.write(System.lineSeparator());

            writer.write(String.valueOf(account.getJPYBalance()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Load account data
    public Account loadAccount() {
        
        try(BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String owner = reader.readLine();

            double krw = Double.parseDouble(reader.readLine());
            double usd = Double.parseDouble(reader.readLine());
            double eur = Double.parseDouble(reader.readLine());
            double jpy = Double.parseDouble(reader.readLine());

            return new Account(owner, krw, usd, eur, jpy);
        } catch (IOException e) {
            return new Account("John Doe", 100000, 0, 0, 0);
        }
    }
}

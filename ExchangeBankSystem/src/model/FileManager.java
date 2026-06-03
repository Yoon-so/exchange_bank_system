package model;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static final String FILE_NAME = "transaction_history.txt";

    public void saveTransaction(ArrayList<Transaction> transactions) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Transaction t : transactions) {
               writer.write(t.toString());
               writer.write(System.lineSeparator());
            }
            System.out.println("Transaction history saved to " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Error saving transaction history: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

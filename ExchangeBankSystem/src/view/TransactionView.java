package view;

import controller.MainController;
import model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransactionView extends JFrame {
    private MainController controller;

    public TransactionView(MainController controller) {

        this.controller = controller;
        
        initializeFrame();
        initializeComponents();
        setVisible(true);
    } 

    public void initializeFrame() {
        setTitle("Transaction History");
        
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void initializeComponents() {
        String[] columns = {
            "Type",
            "Amount",
            "Currency",
            "Date",
            "Balance"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Transaction t : controller.getTransactions()) {
            model.addRow(
                new Object[] {
                    t.getDescription(),
                    t.getAmount(),
                    t.getCurrency(),
                    t.getDate(),
                    t.getBalanceInfo()
                }
            );
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}

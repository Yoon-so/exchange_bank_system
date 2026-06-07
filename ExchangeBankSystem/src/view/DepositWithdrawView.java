package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;

public class DepositWithdrawView extends JFrame {

    private MainController controller;
    private String mode;
    private JTextField amountField;
    private JButton submitButton;

    public DepositWithdrawView(MainController controller, String mode) {
        this.controller = controller;
        this.mode = mode;

        initializeFrame();
        initializeComponents();
        registerListeners();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle(mode);

        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        //Title
        JLabel label = new JLabel("Enter Amount", SwingConstants.CENTER);
        c.add(label, BorderLayout.NORTH);

        //Text field
        amountField = new JTextField();
        c.add(amountField, BorderLayout.CENTER);
        
        submitButton = new JButton(mode);
        c.add(submitButton, BorderLayout.SOUTH);
    }

    private void registerListeners() {
        
        //Submit Action
        submitButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be greater than 0!");
                    return;
                }  
                
                if (mode.equals("DEPOSIT")) {
                    controller.deposit(amount);
                    JOptionPane.showMessageDialog(this, "Deposit successful!");
                } else {
                    boolean success = controller.withdraw(amount);

                    if (success) {
                        JOptionPane.showMessageDialog(this, "Withdraw successful!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient balance!");
                    }
                }
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            }
        });
    }  
}

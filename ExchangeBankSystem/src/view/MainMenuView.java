package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame{
    private MainController controller;

    private JButton depositButton;
    private JButton withdrawButton;
    private JButton exchangeButton;
    private JButton balanceButton;
    private JButton historyButton;
    private JButton saveButton;
    private JButton exitButton;

    public MainMenuView(MainController controller) {

        this.controller = controller;

        initializeFrame();
        initializeComponents();
        registerListeners();

        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Exchange Bank System");

        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        //Title
        JLabel titleLabel = new JLabel("Exchange Bank System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        c.add(titleLabel, BorderLayout.NORTH);

        //Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1, 10,10));

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        exchangeButton = new JButton("Exchange");
        balanceButton = new JButton("Balance");
        historyButton = new JButton("History");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(exchangeButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);

        c.add(buttonPanel, BorderLayout.CENTER);
    }

    private void registerListeners() {

        balanceButton.addActionListener(e -> {
            String message = "Owner: " + controller.getOwner()
                            + "\n\nKRW: " +  controller.getKRWBalance()
                            + "\nUSD: " + controller.getUSDBalance()
                            + "\nEUR: " + controller.getEURBalance()
                            + "\nJPY: " + controller.getJPYBalance();

            JOptionPane.showMessageDialog(this, message, "Account Balance", JOptionPane.INFORMATION_MESSAGE);
        });

        depositButton.addActionListener(e -> {
            new DepositWithdrawView(controller, "DEPOSIT");
        });

        withdrawButton.addActionListener(e -> {
            new DepositWithdrawView(controller, "WITHDRAW");
        });

        exchangeButton.addActionListener(e -> {
            new ExchangeView(controller);
        });

        historyButton.addActionListener(e -> {
            new TransactionView(controller);
        });
    }
}

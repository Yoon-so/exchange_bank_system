package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenuView extends JFrame{
    private MainController controller;

    private JButton depositButton;
    private JButton withdrawButton;
    private JButton exchangeButton;
    private JButton balanceButton;
    private JButton historyButton;
    private JButton saveExitButton;

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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.saveData();
                System.exit(0);
            }
        });
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
        buttonPanel.setLayout(new GridLayout(6, 1, 10,10));

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        exchangeButton = new JButton("Exchange");
        balanceButton = new JButton("Balance");
        historyButton = new JButton("History");
        saveExitButton = new JButton("Save & Exit");

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(exchangeButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(saveExitButton);

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

        saveExitButton.addActionListener(e -> {
            controller.saveData();
            JOptionPane.showMessageDialog(this, "Data saved succesfully!");
            System.exit(0);
        });
    }
}

package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;

public class ExchangeView extends JFrame {

    private MainController controller;
    private JTextField amountField;
    private JComboBox<String> currencyBox;
    private JButton exchangeButton;
    private RateView rateView;

    public ExchangeView(MainController controller) {
        this.controller = controller;

        initializeFrame();
        initializeComponents();
        registerListeners();

        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Currency Exchange");
        
        setSize(400,350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        rateView = new RateView(controller);
        c.add(rateView, BorderLayout.NORTH);

        JPanel exchangePanel = new JPanel(new GridLayout(5, 1, 10, 10));

        exchangePanel.add(new JLabel("Amount (KRW)", SwingConstants.CENTER));
        amountField = new JTextField();
        exchangePanel.add(amountField);

        exchangePanel.add(new JLabel("Select Currency", SwingConstants.CENTER));

        currencyBox = new JComboBox<>();
        currencyBox.addItem("USD");
        currencyBox.addItem("EUR");
        currencyBox.addItem("JPY");
        exchangePanel.add(currencyBox);

        exchangeButton = new JButton("Exchange");
        exchangePanel.add(exchangeButton);

        c.add(exchangePanel, BorderLayout.CENTER);
    }

    private void registerListeners() {
        exchangeButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String currency = (String) currencyBox.getSelectedItem();

                double result = controller.exchange(amount, currency);

                if (result < 0) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!");
                    return;
                }

                JOptionPane.showMessageDialog(this, "Received " + result + " " + currency);
                dispose();
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            }
        });
    }
}

package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;

public class ExchangeView extends JFrame {

    private MainController controller;
    private JTextField amountField;
    private JComboBox<String> currencyBox;
    private JButton exchangeButton;
    private JLabel rateLabel;

    public ExchangeView(MainController controller) {
        this.controller = controller;

        initializeFrame();
        initializeComponents();
        updateRateLabel();
        registerListeners();

        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Currency Exchange");
        
        setSize(350,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        Container c = getContentPane();
        c.setLayout(new GridLayout(6, 1, 10, 10));

        rateLabel = new JLabel("", SwingConstants.CENTER);
        c.add(rateLabel);

        c.add(new JLabel("Amount (KRW)", SwingConstants.CENTER));
        amountField = new JTextField();
        c.add(amountField);

        c.add(new JLabel("Select Currency", SwingConstants.CENTER));

        currencyBox = new JComboBox<>();
        currencyBox.addItem("USD");
        currencyBox.addItem("EUR");
        currencyBox.addItem("JPY");
        c.add(currencyBox);

        exchangeButton = new JButton("Exchange");
        c.add(exchangeButton);
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

    private void updateRateLabel() {
        String text = "<html>"
                    + "Current Exchange Rate (for 1000krw)" + "<br>" + "<hr>"
                    + "USD: " + controller.getRate("USD") * 1000 + "<br>"
                    + "EUR: " + controller.getRate("EUR") * 1000 + "<br>"
                    + "\nJPY: " + controller.getRate("JPY") * 1000
                    + "</html>";
        rateLabel.setText(text);
    }
}

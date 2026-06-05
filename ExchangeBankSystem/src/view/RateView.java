package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;

public class RateView extends JPanel {
    private MainController controller;
    private JLabel rateLabel;

    public RateView(MainController controller) {
        this.controller = controller;

        setLayout(new BorderLayout());

        rateLabel = new JLabel("", SwingConstants.CENTER);
        rateLabel.setBorder(BorderFactory.createTitledBorder("Current Exchange Rates"));

        add(rateLabel, BorderLayout.CENTER);
        updateRates();
    }

    public void updateRates() {
        rateLabel.setText(
                    "<html><center>"
                    + "Exchange Rates (per 1,000 KRW)" + "<br>" + "<hr>"
                    + "USD: " + controller.getRate("USD") * 1000 + "<br>"
                    + "EUR: " + controller.getRate("EUR") * 1000 + "<br>"
                    + "\nJPY: " + controller.getRate("JPY") * 1000
                    + "</center></html>"
        );
    }
}

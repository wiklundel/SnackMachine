package view;

import javax.swing.*;
import java.awt.*;

import java.util.HashMap;
import java.util.Map;

public class SpaceMachineView extends JFrame {
    private JPanel productPanel;
    private JLabel messageLabel;

    private JPanel moneyPanel;
    private JLabel balanceLabel;

    private final Map<String, JButton> productButtons = new HashMap<>();
    private final Map<String, StockAnimationView> stockViews = new HashMap<>();
    private final int MAX_BAR_HEIGHT = 66;

    public SpaceMachineView() {
        setTitle("My Space Machine");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "MY SPACE MACHINE",
                SwingConstants.CENTER
        );
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(title, BorderLayout.NORTH);

        balanceLabel = new JLabel(
                "Balance: 0 kr",
                SwingConstants.CENTER
        );

        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(balanceLabel, BorderLayout.CENTER);

        moneyPanel = new JPanel(new FlowLayout());
        topPanel.add(moneyPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        productPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        add(productPanel, BorderLayout.CENTER);

        messageLabel = new JLabel(
                "Select a product",
                SwingConstants.CENTER
        );
        add(messageLabel, BorderLayout.SOUTH);
    }

    public void addMoneyButton(int moneyValue, Runnable onClick) {
        JButton moneyButton = new JButton();

        moneyButton.setText("<html><center>" + moneyValue + " kr</html>");

        moneyButton.addActionListener(e -> onClick.run());

        moneyPanel.add(moneyButton);
    }

    public void addProductButton(String name, int price, int stock, int maxStock, String imagePath, Runnable onBuy) {
        java.net.URL imageUrl = getClass().getResource(imagePath);

        JButton productButton = new JButton();
        productButton.setLayout(new BorderLayout());

        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            productButton.setIcon(new ImageIcon(scaledImage));
        }

        productButton.setText(
                "<html><center>" + name + ": " + stock + "<br>" + price + " kr</html>"
        );

        StockAnimationView stockView = new StockAnimationView();
        productButton.add(stockView);
        stockViews.put(name, stockView);

        productButton.setHorizontalTextPosition(SwingConstants.CENTER);
        productButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        productButton.addActionListener(e -> onBuy.run());

        productButtons.put(name, productButton);
        productPanel.add(productButton, BorderLayout.CENTER);

        updateStockBar(name, stock, maxStock);
    }

    public void showMessage(String message) {
        messageLabel.setText((message));
    }

    public void updateBalance(int balance) {
        balanceLabel.setText("Balance: " + balance + " kr");
    }

    public void updateStock(String name, int price, int stock) {
        JButton button = productButtons.get(name);

        if(button != null) {
            button.setText(
                    "<html><center>" + name + ": " + stock +
                    "<br>" + price + " kr<center></html>"
            );
        }
    }

    public void updateStockBar(String name, int currentStock, int maxStock) {
        StockAnimationView view = stockViews.get(name);

        if (view == null || maxStock <= 0) return;

        double percentage = (double) currentStock/maxStock;
        int newHeight = (int) (percentage * MAX_BAR_HEIGHT);

        view.setStockHeight(newHeight);
    }
}

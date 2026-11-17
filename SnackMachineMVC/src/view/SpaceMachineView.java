package view;

import javax.swing.*;
import java.awt.*;

import java.util.HashMap;
import java.util.Map;

public class SpaceMachineView extends JFrame {

    // GUI components for displaying the balance and money buttons.
    private final JLabel balanceLabel;
    private final JPanel moneyPanel;

    // Panel containing the product buttons.
    private final JPanel productPanel;

    // Used to display purchase receipts.
    private final JTextArea receiptLog;

    // Stores product buttons and stock animations by product name.
    // final prevents the maps from being reassigned.
    private final Map<String, JButton> productButtons = new HashMap<>();
    private final Map<String, StockAnimationView> stockViews = new HashMap<>();

    /**
     * Creates the main window and initializes its GUI components.
     */
    public SpaceMachineView() {
        // Configure the main application window.
        setTitle("My Space Machine");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Create and style the application title.
        JLabel title = new JLabel(
                "MY SPACE MACHINE",
                SwingConstants.CENTER
        );
        title.setFont(new Font("Arial", Font.BOLD, 24));

        // Create the top panel containing the title,
        // current balance, and money buttons.
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        topPanel.add(title, BorderLayout.NORTH);

        // Create the label displaying the current balance.
        balanceLabel = new JLabel(
                "Balance: 0 kr",
                SwingConstants.CENTER
        );
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(balanceLabel, BorderLayout.CENTER);

        // Create the panel for money and summary buttons.
        moneyPanel = new JPanel(new FlowLayout());
        topPanel.add(moneyPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Create a 2x2 grid for the product buttons.
        productPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        productPanel.setBorder(BorderFactory.createEmptyBorder(10, 4, 10, 4));
        add(productPanel, BorderLayout.CENTER);

        // Create the bottom panel for the receipt log.
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        add(bottomPanel, BorderLayout.SOUTH);

        // Create the receipt section.
        JPanel receiptPanel = new JPanel(new BorderLayout());
        bottomPanel.add(receiptPanel, BorderLayout.WEST);

        // Create the receipt log heading.
        JLabel receiptLabel = new JLabel(
                "Receipt log",
                SwingConstants.CENTER
        );
        receiptPanel.add(receiptLabel, BorderLayout.NORTH);

        // Create a text area for displaying purchases attempts.
        // Enable automatic wrapping
        receiptLog = new JTextArea();
        receiptLog.setLineWrap(true);
        receiptLog.setWrapStyleWord(true);
        receiptLog.setEditable(false);

        // Add scrolling to the receipt log.
        JScrollPane scrollPane = new JScrollPane(receiptLog);
        scrollPane.createVerticalScrollBar();
        scrollPane.setPreferredSize(new Dimension(getWidth() / 2, 100));
        receiptPanel.add(scrollPane, BorderLayout.CENTER);

    }

    /**
     * Adds a button for inserting money into the machine.
     *
     * @param moneyValue the amount displayed on the button
     * @param onClick action performed when the button is clicked
     */
    public void addMoneyButton(int moneyValue, Runnable onClick) {
        JButton moneyButton = new JButton();

        // Display the amount and connect the button to its action.
        moneyButton.setText("<html><center>" + moneyValue + " kr</html>");
        moneyButton.addActionListener(e -> onClick.run());

        moneyPanel.add(moneyButton);
    }

    /**
     * Creates a product button with an image, price, stock quantity,
     * and animated stock indicator.
     *
     * @param name the product name
     * @param price the product price
     * @param stock the current stock quantity
     * @param maxStock the maximum stock capacity
     * @param imagePath the path to the product image
     * @param onBuy action performed when the product is selected
     */
    public void addProductButton(String name, int price, int stock, int maxStock, String imagePath, Runnable onBuy) {

        // Load the product image from the application's resources.
        java.net.URL imageUrl = getClass().getResource(imagePath);

        JButton productButton = new JButton();
        productButton.setLayout(new BorderLayout());

        // Scale the image and display it on the button
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            productButton.setIcon(new ImageIcon(scaledImage));
        }

        // display the product name, stock, and price.
        productButton.setText(
                "<html><center>" + name + ": " + stock + "<br>" + price + " kr</html>"
        );

        // Create and store the product's stock animation.
        StockAnimationView stockView = new StockAnimationView();
        productButton.add(stockView);
        stockViews.put(name, stockView);

        //Position the text below the product image.
        productButton.setHorizontalTextPosition(SwingConstants.CENTER);
        productButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Connect the product button to its purchase action
        productButton.addActionListener(e -> onBuy.run());

        // Store the button so its stock text can be updated later
        productButtons.put(name, productButton);
        productPanel.add(productButton, BorderLayout.CENTER);

        // Set the initial height if the stock indicator.
        updateStockBar(name, stock, maxStock);
    }

    /**
     * Adds a button for displaying purchase statistics.
     *
     * @param onClick action performed when the button is clicked
     */
    public void addSummaryButton(Runnable onClick) {
        JButton summaryButton = new JButton("Summary");

        summaryButton.addActionListener(e -> onClick.run());
        moneyPanel.add(summaryButton);
    }

    /**
     * Displays a summary of all purchase attempts.
     *
     * @param successful number of successful purchases
     * @param insufficient number of attempts with insufficient funds
     * @param outOfStock number of attempts with unavailable products
     */
    public void showSummary(int successful, int insufficient, int outOfStock) {

        // Build the summary message using the purchase statistics.
        String message =
                "successful purchase: " + successful +
                        "\nInsufficient funds: " + insufficient +
                        "\nOut of stock: " + outOfStock +
                        "\nTotal purchase attempts: " +
                        (successful + insufficient + outOfStock);

        // Display the statistics in a dialog window.
        JOptionPane.showMessageDialog(
                this,
                message,
                "Summary",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Appends a message to the display.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        receiptLog.append(message);
    }

    /**
     * Updates the balance displayed in the GUI.
     *
     * @param balance the current balance
     */
    public void updateBalance(int balance) {
        balanceLabel.setText("Balance: " + balance + " kr");
    }

    /**
     * Updates the stock quantity displayed on a product button.
     *
     * @param name the product name
     * @param price the product price
     * @param stock the remaining stock quantity
     */
    public void updateStock(String name, int price, int stock) {

        // Find the button associated with the product.
        JButton button = productButtons.get(name);

        // Update its text if the button exists.
        if(button != null) {
            button.setText(
                    "<html><center>" + name + ": " + stock +
                    "<br>" + price + " kr<center></html>"
            );
        }
    }

    /**
     * Updates the height of a product's stock indicator
     * based on the percentage of the remaining stock.
     *
     * @param name the product name
     * @param currentStock the remaining stock quantity
     * @param maxStock the maximum stock capacity
     */
    public void updateStockBar(String name, int currentStock, int maxStock) {

        // Find the stock animation associated with the product.
        StockAnimationView view = stockViews.get(name);

        // Stop if the animation is missing or maxStock is invalid.
        if (view == null || maxStock <= 0) return;

        // Calculate the remaining stock as a percentage.
        double percentage = (double) currentStock/maxStock;

        // Maximum bar height
        int MAX_BAR_HEIGHT = 66;

        // Convert the percentage into a visual bar height.
        int newHeight = (int) (percentage * MAX_BAR_HEIGHT);

        // Update the animated stock indicator
        view.setStockHeight(newHeight);
    }
}

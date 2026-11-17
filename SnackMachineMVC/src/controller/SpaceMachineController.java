package controller;

import model.SpaceMachine;

import view.SpaceMachineView;
import se.umu.cs.spacemachine.*;

import java.util.List;

public class SpaceMachineController {

    // References to the View and Model.
    // final prevents these references from being reassigned.
    private final SpaceMachineView view;
    private final SpaceMachine machine;

    int receiptNum = 0;

    /**
     * Initializes the Controller and connects the Model and View.
     * Creates money buttons, the summary button, and product buttons.
     *
     * @param view the graphical user interface
     * @param products the list of available products
     */
    public SpaceMachineController(SpaceMachineView view, List<Product> products) {
        this.view = view;
        this.machine = new SpaceMachine();

        // Create buttons for inserting money.
        view.addMoneyButton(1, () -> addMoney(1));
        view.addMoneyButton(5, () -> addMoney(5));
        view.addMoneyButton(10, () -> addMoney(10));
        view.addMoneyButton(20, () -> addMoney(20));

        // Create a button for displaying purchase statistics.
        view.addSummaryButton(() -> showSummary());

        // Display the initial balance.
        view.updateBalance(machine.getBalance());

        // Create a button for each available product.
        for (Product product : products) {

            // Select image based on the product's name.
            String imagePath = switch (product.getName()) {
                case "Alien" -> "/alien.png";
                case "Planet" -> "/planet.png";
                case "UFO" -> "/ufo.png";
                case "Rocket Ship" -> "/rocketShip.png";
                default -> "";
            };

            // Create a product button with its name, price,
            // current stock, maximum stock, and image.
            // Clicking the button calls buyProduct().
            view.addProductButton(
                    product.getName(),
                    product.getPrice(),
                    product.getStock(),
                    product.getMaxStock(),
                    imagePath,
                    () -> buyProduct(product)
            );

            // Initialize the product's stock indicator.
            view.updateStockBar(
                    product.getName(),
                    product.getStock(),
                    product.getMaxStock()
            );
        }
    }

    /**
     * Attempts to purchase the selected product.
     * Handles successful purchases and exceptions for declined purchases.
     *
     * @param product the product the user wants to purchase
     */
    private void buyProduct(Product product) {

        try {
            // Ask the Model to process the purchase.
            machine.purchase(product);

            // Add a purchase confirmation to the receipt log.
            view.showMessage(
                    receiptNum + ". Purchased " + product.getName() +
                    "! Stock left: " + product.getStock() + "\n"
            );

            // Update the displayed stock quantity.
            view.updateStock(
                    product.getName(),
                    product.getPrice(),
                    product.getStock()
            );

            // Update the stock indicator after a successful purchase.
            view.updateStockBar(
                    product.getName(),
                    product.getStock(),
                    product.getMaxStock()
            );

        } catch (InsufficientFundsException e) {
            // Handle purchases declined due to insufficient funds.
            // Display the exception type and message in the receipt log.
            view.showMessage(
                    receiptNum + ". " +
                    e.getClass().getSimpleName() + ": " + e.getMessage() + "\n"
            );

        } catch (OutOfStockException e) {
            // Handle purchases declined because the product is out of stock.
            // Display the exception type in the receipt log.
            view.showMessage(
                    receiptNum + ". " +
                    product.getName() + " is out of stock!\n"
            );
        }

        // Increment the receipt number after every purchase attempt,
        // regardless of whether the purchase succeeded or failed.
        receiptNum++;

        // Refresh the displayed balance after the purchase attempt.
        view.updateBalance(machine.getBalance());
    }

    /**
     * Adds money to the machine and updates the displayed balance.
     *
     * @param amount the amount of money to insert
     */
    private void addMoney(int amount) {
        // Update the balance in the Model.
        machine.addMoney(amount);

        // Display the updated balance in the View.
        view.updateBalance(machine.getBalance());
    }

    /**
     * Retrieves purchase statistics from the Model
     * and displays the summary in the View.
     */
    private void showSummary() {
        view.showSummary(
                machine.getSuccessfulPurchases(),
                machine.getInsufficientFunds(),
                machine.getOutOfStock()
        );
    }
}

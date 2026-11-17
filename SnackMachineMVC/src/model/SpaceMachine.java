package model;

import se.umu.cs.spacemachine.*;

public class SpaceMachine {

    // Current balance available for purchases.
    private int balance = 0;

    // Counters used to track purchase attempts and their outcomes.
    private int successfulPurchases = 0;
    private int insufficientFunds = 0;
    private int outOfStock = 0;

    /**
     * Returns the current balance of the machine.
     *
     * @return the current balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Adds money to the machine's balance.
     *
     * @param amount the amount of money to insert
     * @throws IllegalArgumentException if the money is not positive
     */
    public void addMoney(int amount) {

        // Prevent zero or negative amounts from being inserted.
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        // Increase the balance by the inserted amount.
        balance += amount;
    }

    /**
     * Attempts to purchase a product.
     * Checks the balance, dispense the product, and deducts its price.
     *
     * @param product the product to purchase
     * @throws InsufficientFundsException if the balance is too low
     * @throws OutOfStockException if the product is out of stock
     */
    public void purchase (Product product)
            throws InsufficientFundsException, OutOfStockException {

        // Check whether the balance is sufficient for the purchase.
        if (balance < product.getPrice()) {

            // Record the failed attempt and throw an exception.
            insufficientFunds++;
            throw new InsufficientFundsException(
                    "Not enough money to buy " + product.getName()
            );
        }

        // Attempt to dispense the selected product.
        try {
            product.dispense();

        } catch (OutOfStockException e) {
            outOfStock++;
            throw e;
        }

        // Deduct the price only after the product is dispensed.
        balance -= product.getPrice();

        // Record the successful purchase.
        successfulPurchases++;
    }

    /**
     * Returns the number of successful purchases.
     *
     * @return the number of successful purchases
     */
    public int getSuccessfulPurchases() {
        return successfulPurchases;
    }

    /**
     * Returns the number of purchases declined due to insufficient funds.
     *
     * @return the number of insufficient funds attempts
     */
    public int getInsufficientFunds() {
        return insufficientFunds;
    }

    /**
     * Returns the number of purchases due to empty stock.
     *
     * @return the number of out-of-stock attempts
     */
    public int getOutOfStock() {
        return outOfStock;
    }
}

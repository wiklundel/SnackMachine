package model;

import se.umu.cs.spacemachine.*;

public class SpaceMachine {
    private int balance = 0;
    private int successfulPurchases = 0;
    private int insufficientFunds = 0;
    private int outOfStock = 0;

    public int getBalance() {
        return balance;
    }

    public void addMoney(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        balance += amount;
    }

    public void purchase (Product product)
            throws InsufficientFundsException, OutOfStockException {

        if (balance < product.getPrice()) {
            insufficientFunds++;
            throw new InsufficientFundsException(
                    "Not enough money to buy " + product.getName()
            );
        }

        try {
            product.dispense();
            balance -= product.getPrice();
        } catch (OutOfStockException e) {
            outOfStock++;
            throw e;
        }

        balance -= product.getPrice();
        successfulPurchases++;
    }

    public int getSuccessfulPurchases() {
        return successfulPurchases;
    }

    public int getInsufficientFunds() {
        return insufficientFunds;
    }

    public int getOutOfStock() {
        return outOfStock;
    }
}

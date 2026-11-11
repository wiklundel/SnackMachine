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

    public boolean purchase(Product product) {
        if (product.getStock() == 0) {
            outOfStock++;
            return false;
        }

        if (balance < product.getPrice()) {
            insufficientFunds++;
            return false;
        }

        try {
            product.dispense();
            balance -= product.getPrice();
            successfulPurchases++;
            return true;
        } catch (OutOfStockException e) {
            outOfStock++;
            return false;
        }
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

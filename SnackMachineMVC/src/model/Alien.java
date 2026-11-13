package model;

import se.umu.cs.spacemachine.Product;
import se.umu.cs.spacemachine.OutOfStockException;

public class Alien implements Product{

    private String name;
    private int price;
    private int currentStock;
    private int maxStock;

    public Alien(String name, int price, int maxStock) {
        this.name = name;
        this.price = price;
        this.currentStock = maxStock;
        this.maxStock = maxStock;
    }

    public String getName()  {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return currentStock;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void dispense() throws OutOfStockException {
        if (currentStock < 1) throw new OutOfStockException("Product is out of stock");
        currentStock--;
    }
}

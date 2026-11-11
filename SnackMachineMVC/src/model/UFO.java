package model;

import se.umu.cs.snackmachine.Product;
import se.umu.cs.snackmachine.OutOfStockException;

public class UFO implements Product {

    private String name;
    private int price;
    private int currentStock;
    private int maxStock;

    public UFO(String name, int price, int currentStock, int maxStock) {
        this.name = name;
        this.price = price;
        this.currentStock = currentStock;
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
        if (currentStock < 1) throw new OutOfStockException("Product is out os stock");
        currentStock --;
    }
}

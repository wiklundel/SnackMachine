package model;

import se.umu.cs.snackmachine.Product;
import se.umu.cs.snackmachine.OutOfStockException;

public class Alien implements Product{

    private String name;
    private int price;
    private int currentStock;
    private int maxStock;

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

    public void dispense() {}
}

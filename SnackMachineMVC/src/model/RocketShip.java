package model;

import se.umu.cs.spacemachine.Product;
import se.umu.cs.spacemachine.OutOfStockException;

public class RocketShip implements Product {

    private final String name;
    private final int price;
    private int currentStock;
    private final int maxStock;

    /**
     * Creates a Rocket Ship product with name, price, and maximum stock.
     * The initial stock is set to the maximum stock capacity.
     *
     * @param name the product name
     * @param price the product price
     * @param maxStock the maximum number of items in stock
     */
    public RocketShip(String name, int price, int maxStock) {
        this.name = name;
        this.price = price;
        this.currentStock = maxStock;
        this.maxStock = maxStock;
    }

    /**
     * Returns the name of the product.
     *
     * @return the product name
     */
    public String getName()  {
        return name;
    }

    /**
     * Returns the price of the product.
     *
     * @return the product price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the current stock quantity.
     *
     * @return the number of remaining items
     */
    public int getStock() {
        return currentStock;
    }

    /**
     * Returns the maximum stock quantity.
     *
     * @return the maximum number of items
     */
    public int getMaxStock() {
        return maxStock;
    }

    /**
     * Dispenses one product by decreasing the current stock.
     * Throws an exception if no products are available.
     *
     * @throws OutOfStockException if the product is out of stock
     */
    public void dispense() throws OutOfStockException {
        if (currentStock < 1) throw new OutOfStockException("Product is out of stock");
        currentStock--;
    }
}

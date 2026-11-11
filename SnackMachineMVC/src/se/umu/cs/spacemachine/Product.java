package se.umu.cs.spacemachine;

public interface Product {
    String getName();
    int getPrice();      // pris i kronor
    int getStock();      // antal kvar i automaten
    int getMaxStock();   // ursprungligt/max antal
    void dispense() throws OutOfStockException;
}

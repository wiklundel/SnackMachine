package controller;

import view.SpaceMachineView;
import se.umu.cs.spacemachine.*;

import java.util.List;

public class SpaceMachineController {
    private final SpaceMachineView view;

    public SpaceMachineController(SpaceMachineView view, List<Product> products) {
        this.view = view;

        for (Product product : products) {
            view.addProductButton(
                    product.getName(),
                    product.getPrice(),
                    () -> buyProduct(product)
            );
        }
    }

    private void buyProduct(Product product) {
        try {
            product.dispense();
            view.showMessage(
                    "Purchased " + product.getName() + "! Stock left: " + product.getStock()
            );
        } catch (OutOfStockException e) {
            view.showMessage(
                    product.getName() + "is out of stock!"
            );
        }
    }
}

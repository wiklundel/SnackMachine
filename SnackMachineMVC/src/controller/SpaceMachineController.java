package controller;

import view.SpaceMachineView;
import se.umu.cs.spacemachine.*;

import java.util.List;

public class SpaceMachineController {
    private final SpaceMachineView view;

    public SpaceMachineController(SpaceMachineView view, List<Product> products) {
        this.view = view;

        for (Product product : products) {

            String imagePath = switch (product.getName()) {
                case "Alien" -> "/alien.png";
                case "Planet" -> "/planet.png";
                case "UFO" -> "/ufo.png";
                case "RocketShip" -> "/rocketShip.png";
                default -> "";
            };

            view.addProductButton(
                    product.getName(),
                    product.getPrice(),
                    imagePath,
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

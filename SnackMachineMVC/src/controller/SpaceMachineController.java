package controller;

import model.SpaceMachine;
import model.PurchaseResult;

import view.SpaceMachineView;
import se.umu.cs.spacemachine.*;

import java.util.List;

public class SpaceMachineController {
    private final SpaceMachineView view;
    private final SpaceMachine machine;

    public SpaceMachineController(SpaceMachineView view, List<Product> products) {
        this.view = view;
        this.machine = new SpaceMachine();

        view.addMoneyButton(1, () -> addMoney(1));
        view.addMoneyButton(5, () -> addMoney(5));
        view.addMoneyButton(10, () -> addMoney(10));
        view.addMoneyButton(20, () -> addMoney(20));

        view.updateBalance(machine.getBalance());

        for (Product product : products) {

            String imagePath = switch (product.getName()) {
                case "Alien" -> "/alien.png";
                case "Planet" -> "/planet.png";
                case "UFO" -> "/ufo.png";
                case "Rocket Ship" -> "/rocketShip.png";
                default -> "";
            };

            view.addProductButton(
                    product.getName(),
                    product.getPrice(),
                    product.getStock(),
                    product.getMaxStock(),
                    imagePath,
                    () -> buyProduct(product)
            );

            view.updateStockBar(product.getName(), product.getStock(), product.getMaxStock());
        }
    }

    private void buyProduct(Product product) {

        PurchaseResult result = machine.purchase(product);

        switch (result) {
            case SUCCESS -> {
                view.showMessage(
                        "Purchased " + product.getName() +
                                "! Stock left: " + product.getStock()
                );
                view.updateStock(product.getName(), product.getPrice(), product.getStock());
                view.updateStockBar(product.getName(), product.getStock(), product.getMaxStock());
            }
            case OUT_OF_STOCK ->
                view.showMessage(
                        product.getName() + " is out of stock!"
                );

            case INSUFFICIENT_FUND ->
                view.showMessage(
                        "Insufficient funds! " +
                        "Price: " + product.getPrice() + " kr"
                );
        }

        view.updateBalance(machine.getBalance());
    }

    private void addMoney(int amount) {
        machine.addMoney(amount);

        view.updateBalance(machine.getBalance());

        view.showMessage("Added " + amount + " kr");
    }
}

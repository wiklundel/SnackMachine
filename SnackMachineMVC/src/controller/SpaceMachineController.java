package controller;

import model.SpaceMachine;
import model.PurchaseResult;

import view.SpaceMachineView;
import se.umu.cs.spacemachine.*;

import java.util.List;

public class SpaceMachineController {
    private final SpaceMachineView view;
    private final SpaceMachine machine;

    int receiptNum = 0;

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

        try {
            machine.purchase(product);

            view.showMessage(
                    receiptNum + ". Purchased " + product.getName() +
                    "! Stock left: " + product.getStock() + "\n"
            );

            view.updateStock(
                    product.getName(),
                    product.getPrice(),
                    product.getStock()
            );
            view.updateStockBar(
                    product.getName(),
                    product.getStock(),
                    product.getMaxStock()
            );

        } catch (InsufficientFundsException e) {
            view.showMessage(
                    receiptNum + ". " +
                    e.getClass().getSimpleName() + ": " + e.getMessage() + "\n"
            );

        } catch (OutOfStockException e) {
            view.showMessage(
                    receiptNum + ". " +
                    product.getName() + " is out of stock!\n"
            );
        }

        receiptNum++;
        view.updateBalance(machine.getBalance());
    }

    private void addMoney(int amount) {
        machine.addMoney(amount);

        view.updateBalance(machine.getBalance());
        //view.showMessage("Added " + amount + " kr\n");
    }
}

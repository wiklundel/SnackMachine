package controller;

import model.SpaceMachine;
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

        boolean success = machine.purchase(product);
        if (success) {
            view.showMessage("Purchased " + product.getName() + "! Stock left: " + product.getStock());
        } else {
            view.showMessage("Purchase declined");
        }

        view.updateBalance(machine.getBalance());
    }

    private void addMoney(int amount) {
        machine.addMoney(amount);

        view.updateBalance(machine.getBalance());

        view.showMessage("Added " + amount + " kr");
    }
}

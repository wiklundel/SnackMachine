import model.*;
import view.SpaceMachineView;
import controller.SpaceMachineController;
import se.umu.cs.spacemachine.*;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            List<Product> products = List.of(
                    new Alien("Alien", 20, 3, 10),
                    new Planet("Planet", 15, 6, 6),
                    new RocketShip("Rocket Ship", 30, 4, 4),
                    new UFO("UFO", 25, 6, 6)
            );

            SpaceMachineView view = new SpaceMachineView();

            new SpaceMachineController(view, products);

            view.setVisible(true);
        });
    }
}
import model.*;
import view.SpaceMachineView;
import controller.SpaceMachineController;
import se.umu.cs.spacemachine.*;

import javax.swing.*;
import java.util.List;

public class Main {

    /**
     * Entry point of the application.
     * Initializes the products, View, and Controller.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Run the GUI initialization on the Swing Event Dispatch Thread
        // to ensure that Swing components are created safely.
        SwingUtilities.invokeLater(() -> {

            // Create the available products with their names, prices,
            // and maximum stock capacities.
            List<Product> products = List.of(
                    new Alien("Alien", 20, 10),
                    new Planet("Planet", 15, 6),
                    new RocketShip("Rocket Ship", 4, 4),
                    new UFO("UFO", 25, 6)
            );

            // Create the graphical user interface.
            SpaceMachineView view = new SpaceMachineView();

            // Create the Controller and connect it to the View
            // and the list of available products.
            new SpaceMachineController(view, products);

            // Display the application window.
            view.setVisible(true);
        });
    }
}
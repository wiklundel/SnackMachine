import model.*;
import se.umu.cs.snackmachine.OutOfStockException;

public class Main {
    public static void main(String[] args) {
        Alien alien = new Alien("Alien", 20, 5, 5);
        Planet planet = new Planet("Planet", 20, 5, 5);
        UFO ufo = new UFO("UFO", 15, 5, 5);
        RocketShip rocketShip = new RocketShip("Rocket Ship", 10, 0, 5);

        System.out.println("current stock och alien: " + alien.getStock());
        System.out.println("current stock of planet: " + planet.getStock());
        System.out.println("current stock of ufo: " + ufo.getStock());
        System.out.println("current stock of rocket ship: " + rocketShip.getStock());

        try {
            alien.dispense();

            System.out.println("Stock left of alien after dispense: " + alien.getStock());

        } catch (OutOfStockException e) {
            System.out.println(e.getMessage());
        }

        try {
            planet.dispense();

            System.out.println("Stock left of planet after dispense: " + planet.getStock());

        } catch (OutOfStockException e) {
            System.out.println(e.getMessage());
        }

        try {
            ufo.dispense();

            System.out.println("Stock left of ufo after dispense: " + ufo.getStock());

        } catch (OutOfStockException e) {
            System.out.println(e.getMessage());
        }

        try {
            rocketShip.dispense();

            System.out.println("Stock left of rocket ship after dispense: " + rocketShip.getStock());

        } catch (OutOfStockException e) {
            System.out.println(e.getMessage());
        }
    }
}
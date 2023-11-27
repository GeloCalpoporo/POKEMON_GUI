package ABC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MAINGame {
    public static void main(String[] args) {
        // Create the model
        GameModel model = new GameModel();

        // Create the view
        GameView view = new GameView(model);

        // Create the controller
        GameController controller = new GameController(model, view);

        // Initialize the game
        controller.initGame();

        // Run the game
        controller.runGame();
    }}

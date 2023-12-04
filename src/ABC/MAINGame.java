package ABC;

/**
 * The MAINGame class contains the main method to launch the game application.
 * It initializes the model, view, and controller components, setting up the initial state of the game.
 *
 * @author Jose Angelo Calpoporo
 * @author Miguel Sebastian Carlos
 */
public class MAINGame {
    /**
     * The main method to start the game.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create the model
        GameModel model = new GameModel();

        // Create the view
        GameView view = new GameView(model, null); // Pass null initially

        // Create the controller and set it in the view
        GameController controller = new GameController(model, view);
        view.setController(controller);

        // Call pickStarterCreature to prompt the user to choose a starter creature
        controller.pickStarterCreature();
    }
}

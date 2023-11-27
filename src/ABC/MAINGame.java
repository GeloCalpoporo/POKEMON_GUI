package ABC;

public class MAINGame {
    public static void main(String[] args) {
        // Create the model
        GameModel model = new GameModel();

        // Create the controller
        GameController controller = new GameController();

        // Run the game
        controller.startGame();
    }
}

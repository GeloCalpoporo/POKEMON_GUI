package ABC;

public class MAINGame {
    public static void main(String[] args) {
        // Create the model
        GameModel model = new GameModel();

        // Create the view
        GameView view = new GameView(model, null); // Pass null initially

        // Create the controller and set it in the view
        GameController controller = new GameController(model, view);
        view.setController(controller);

        // Set the controller in the view
        view.setController(controller);

        // Display the GUI
        view.setVisible(true);
    }
}

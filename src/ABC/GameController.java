package ABC;
class GameController {
    private GameModel model;
    private GameView view;
    private Scanner scanner;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void initGame() {
        // Initialize the game, e.g., choose starter creature, set up initial state
        // ...

        // Update the view based on the initial state
        view.updateView();
    }

    public void runGame() {
        int userChoice;

        do {
            // Get user input
            System.out.println("Choose what you want to do:");
            System.out.println("1. View Inventory");
            System.out.println("2. Explore An Area");
            System.out.println("3. Evolve Creature");
            System.out.println("4. Exit");
            System.out.println("Choose a number:");
            userChoice = scanner.nextInt();

            // Handle user input and update model/view accordingly
            switch (userChoice) {
                case 1:
                    // View Inventory
                    view.displayInventory(model.getInventory());
                    break;
                case 2:
                    // Explore an Area
                    view.displayExploreAreaMenu();
                    int areaChoice = scanner.nextInt();
                    // Handle area exploration logic based on the choice
                    // Update model/view accordingly
                    break;
                case 3:
                    // Evolve Creature
                    view.displayEvolveCreatureMenu();
                    // Handle creature evolution logic
                    // Update model/view accordingly
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (userChoice != 4);
    }
}
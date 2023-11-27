package ABC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private GameModel model;
    private GameView view;
    private Scanner scanner;
    List<Creature> starterCreatures = new ArrayList<>();
    CreatureList creatureList = new CreatureList();
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void initGame() {
        // Initialize the game, e.g., choose starter creature, set up initial state
        // For example, let's add a starter creature to the inventory
        Creature starterCreature = new Creature(1, "Starter", "Type", "Family", 1);
        model.getInventory().addCreature(starterCreature);

        // Set the starter creature as the active creature
        model.getInventory().setActiveCreature(starterCreature);

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
                    if (areaChoice == 1) {
                        // Create an instance of Area1
                        Area1 area1 = new Area1(model.getInventory(), model.getInventory().getActiveCreature(), model.getCreaturesEL1());
                        // Explore Area1
                        area1.exploreArea();
                    } else {
                        System.out.println("Invalid area choice.");
                    }
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

            // Update the view after each user action
            view.updateView();

        } while (userChoice != 4);
    }
}

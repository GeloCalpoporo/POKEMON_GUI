package ABC;

import javax.swing.*;
import java.util.List;
import java.awt.*;

public class GameController {
    private GameModel model;
    private GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    private void  setPokemonUIStyle() {
        // Customize UI elements with arcade-style styles
        UIManager.put("OptionPane.background", new Color(220, 186, 74)); // Black background
        UIManager.put("Panel.background", new Color(236, 93, 93));
        UIManager.put("OptionPane.messageFont", new Font("Press Start 2P", Font.BOLD, 14)); // Use a retro font
        UIManager.put("OptionPane.messageForeground", new Color(255, 255, 255)); // White text
        UIManager.put("OptionPane.buttonFont", new Font("Press Start 2P", Font.PLAIN, 12));
        UIManager.put("OptionPane.buttonBackground", new Color(255, 69, 0)); // Red buttons
        UIManager.put("OptionPane.buttonForeground", new Color(255, 255, 255)); // White button text
    }

    public void pickStarterCreature() {
        setPokemonUIStyle();
        List<Creature> starterCreatures = model.getCreatureList().getEl1Creatures();

        // Convert List<Creature> to array for JComboBox
        Creature[] creaturesArray = starterCreatures.toArray(new Creature[0]);

        JComboBox<Creature> starterCreatureComboBox = new JComboBox<>(creaturesArray);

        Object[] message = { "Choose your starter creature:", starterCreatureComboBox };

        int option = JOptionPane.showConfirmDialog(view, message, "Pick Starter Creature", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int selectedIndex = starterCreatureComboBox.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < starterCreatures.size()) {
                Creature selectedStarter = starterCreatures.get(selectedIndex);
                model.setActiveCreature(selectedStarter);
                view.setVisible(true);
            }
        }
    }


    public void displayAreaOptions() {
        setPokemonUIStyle();
        // Display area exploration options using JOptionPane
        Object[] options = {"Area 1", "Area 2", "Area 3"};
        int choice = JOptionPane.showOptionDialog(view, "Choose an area to explore:", "Explore Area",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                // Explore Area 1
                model.exploreArea1();
                break;
            case 1:
                // Explore Area 2
                model.exploreArea2();
                break;
            case 2:
                // Explore Area 3
                model.exploreArea3();
                break;
            default:
                // Invalid choice
                JOptionPane.showMessageDialog(view, "Invalid area choice", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayEvolveOptions() {
        setPokemonUIStyle();
        // Create an instance of Evolution and call the appropriate method
        Evolution evolution = new Evolution(model);
        evolution.displayEvolveOptions(view);
    }


    public void openInventoryScreen() {
        setPokemonUIStyle();
        InventoryScreen inventoryScreen = new InventoryScreen(model.getInventory(), model);
    }

}

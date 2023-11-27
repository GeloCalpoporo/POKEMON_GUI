package ABC;

import javax.swing.*;
import java.util.List;

public class GameController {
    private GameModel model;
    private GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void displayInventory() {
        // Display inventory information using JOptionPane
        StringBuilder inventoryText = new StringBuilder("Inventory:\n");
        for (Creature creature : model.getInventory().getCreatures()) {
            inventoryText.append(creature.getName()).append("\n");
        }

        JOptionPane.showMessageDialog(view, inventoryText.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayAreaOptions() {
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
        // Display creature evolution options using JOptionPane
        List<Creature> encounteredCreatures = model.getEncounteredCreatures();

        // Convert List<Creature> to array for JComboBox
        Creature[] creaturesArray = encounteredCreatures.toArray(new Creature[0]);

        JComboBox<Creature> creature1ComboBox = new JComboBox<>(creaturesArray);
        JComboBox<Creature> creature2ComboBox = new JComboBox<>(creaturesArray);

        Object[] message = {
                "Select the first creature:", creature1ComboBox,
                "Select the second creature:", creature2ComboBox
        };

        int option = JOptionPane.showConfirmDialog(view, message, "Evolve Creature", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int indexCreature1 = creature1ComboBox.getSelectedIndex();
            int indexCreature2 = creature2ComboBox.getSelectedIndex();

            // Notify the model to evolve creatures
            model.evolveCreatures(indexCreature1, indexCreature2);
        }
    }

}


package ABC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The Evolution class handles the evolution process of creatures in the game.
 * It allows the player to choose two creatures for evolution and performs the evolution if conditions are met.
 */
public class Evolution {

    private GameModel model;          // Reference to the GameModel
    private GameView view;            // Reference to the GameView
    private Creature evolvedCreature; // The creature after evolution

    /**
     * Constructs an Evolution object with the specified GameModel.
     *
     * @param model Reference to the GameModel.
     */
    public Evolution(GameModel model) {
        this.model = model;
    }

    /**
     * Displays the evolution options to the player through a dialog.
     *
     * @param view The GameView to display the dialog.
     */
    public void displayEvolveOptions(GameView view) {
        this.view = view;

        List<Creature> creatures = model.getInventory().getCreatures();
        Creature[] creaturesArray = creatures.toArray(new Creature[0]);

        JComboBox<Creature> creature1ComboBox = new JComboBox<>(creaturesArray);
        JComboBox<Creature> creature2ComboBox = new JComboBox<>(creaturesArray);
        JButton evolveButton = new JButton("Evolve");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Choose two creatures for evolution:"));
        panel.add(new JLabel("Creature 1:"));
        panel.add(creature1ComboBox);
        panel.add(new JLabel("Creature 2:"));
        panel.add(creature2ComboBox);
        panel.add(evolveButton);

        evolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performEvolution(creature1ComboBox, creature2ComboBox);
            }
        });

        int option = JOptionPane.showConfirmDialog(view, panel, "Evolve Creatures", JOptionPane.OK_CANCEL_OPTION);

        if (option != JOptionPane.OK_OPTION) {
            // User canceled the evolution
            return;
        }
    }

    /**
     * Performs the evolution of two selected creatures.
     *
     * @param creature1ComboBox JComboBox for selecting the first creature.
     * @param creature2ComboBox JComboBox for selecting the second creature.
     */
    private void performEvolution(JComboBox<Creature> creature1ComboBox, JComboBox<Creature> creature2ComboBox) {
        Creature selectedCreature1 = (Creature) creature1ComboBox.getSelectedItem();
        Creature selectedCreature2 = (Creature) creature2ComboBox.getSelectedItem();

        // Check if the selected creatures are different
        if (selectedCreature1.equals(selectedCreature2)) {
            JOptionPane.showMessageDialog(view, "Please select different creatures for evolution.", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = evolveCreatures(selectedCreature1, selectedCreature2);

        if (success) {
            JOptionPane.showMessageDialog(view, "Evolution successful! Evolved creature: " +
                    evolvedCreature.getName() + " Level: " + evolvedCreature.getEvolutionLevel(), "Evolution Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Selected creatures are not eligible for evolution.", "Evolution Failure", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Evolves two creatures if they meet the eligibility criteria.
     *
     * @param creature1 The first creature selected for evolution.
     * @param creature2 The second creature selected for evolution.
     * @return True if evolution is successful, false otherwise.
     */
    private boolean evolveCreatures(Creature creature1, Creature creature2) {
        if (isEligibleForEvolution(creature1, creature2)) {
            int newLevel = creature1.getEvolutionLevel() + 1;

            // Update the creature's name based on evolution
            switch (creature1.getName()) {
                case "STRAWANDER":
                    creature1.setName("STRAWLEON");
                    break;
                // Add cases for other creatures...

                default:
                    creature1.setName("idk");
            }

            evolvedCreature = creature1;
            creature1.setEvolutionLevel(newLevel);

            // Remove evolved creatures from inventory and add the evolved creature
            model.getInventory().deleteCreature(creature1);
            model.getInventory().deleteCreature(creature2);
            model.getInventory().addCreature(evolvedCreature);

            return true;
        }

        return false;
    }

    /**
     * Checks if two creatures are eligible for evolution based on certain criteria.
     *
     * @param creature1 The first creature selected for evolution.
     * @param creature2 The second creature selected for evolution.
     * @return True if the creatures are eligible for evolution, false otherwise.
     */
    private boolean isEligibleForEvolution(Creature creature1, Creature creature2) {
        return creature1.getEvolutionLevel() == creature2.getEvolutionLevel() &&
                creature1.getFamily().equals(creature2.getFamily()) &&
                creature1.getEvolutionLevel() < 3 &&
                creature2.getEvolutionLevel() < 3;
    }
}

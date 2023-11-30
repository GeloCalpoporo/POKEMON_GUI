package ABC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Evolution {

    private GameModel model;
    private GameView view;
    private Creature evolvedCreature;

    public Evolution(GameModel model) {
        this.model = model;
    }

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

    private void performEvolution(JComboBox<Creature> creature1ComboBox, JComboBox<Creature> creature2ComboBox) {
        Creature selectedCreature1 = (Creature) creature1ComboBox.getSelectedItem();
        Creature selectedCreature2 = (Creature) creature2ComboBox.getSelectedItem();

        boolean success = evolveCreatures(selectedCreature1, selectedCreature2);

        if (success) {
            JOptionPane.showMessageDialog(view, "Evolution successful! Evolved creature: " +
                    evolvedCreature.getName() + " Level: " + evolvedCreature.getEvolutionLevel(), "Evolution Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Selected creatures are not eligible for evolution.", "Evolution Failure", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean evolveCreatures(Creature creature1, Creature creature2) {
        if (isEligibleForEvolution(creature1, creature2)) {
            int newLevel = creature1.getEvolutionLevel() + 1;
            model.getInventory().deleteCreature(creature1);

            switch (creature1.getName()) {
                case "STRAWANDER":
                    creature1.setName("STRAWLEON");
                    break;
                case "STRAWLEON":
                    creature1.setName("STRAWIZARD");
                    break;
                case "CHOCOWOOL":
                    creature1.setName("CHOCOFLUFF");
                    break;
                case "CHOCOFLUFF":
                    creature1.setName("CANDROS");
                    break;
                case "PARFWIT":
                    creature1.setName("PARFURE");
                    break;
                case "PARFURE":
                    creature1.setName("PARFELURE");
                    break;
                case "BROWNISAUR":
                    creature1.setName("CHOCOSAUR");
                    break;
                case "CHOCOSAUR":
                    creature1.setName("FUDGASAUR");
                    break;
                case "FRUBAT":
                    creature1.setName("GOLBERRY");
                    break;
                case "GOLBERRY":
                    creature1.setName("CROBERRY");
                    break;
                case "MALTS":
                    creature1.setName("KIRLICAKE");
                    break;
                case "KIRLICAKE":
                    creature1.setName("VELVEVOIR");
                    break;
                case "SQUIRPIE":
                    creature1.setName("TARTORTLE");
                    break;
                case "TARTORTLE":
                    creature1.setName("PIESTOISE");
                    break;
                case "CHOCOLITE":
                    creature1.setName("CHOCOLISH");
                    break;
                case "CHOCOLISH":
                    creature1.setName("ICESUNDAE");
                    break;
                case "OSHACONE":
                    creature1.setName("DEWICE");
                    break;
                case "DEWICE":
                    creature1.setName("SAMURCONE");
                    break;
                default:
                    creature1.setName("idk");
            }
            creature1.setEvolutionLevel(newLevel);
            model.getInventory().deleteCreature(creature2);
            model.getInventory().addCreature(creature1);

            evolvedCreature = creature1;

            return true;
        }

        return false;
    }

    private boolean isEligibleForEvolution(Creature creature1, Creature creature2) {
        return creature1.getEvolutionLevel() == creature2.getEvolutionLevel() &&
                creature1.getFamily().equals(creature2.getFamily()) &&
                creature1.getEvolutionLevel() < 3 &&
                creature2.getEvolutionLevel() < 3;
    }
}

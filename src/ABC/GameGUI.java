package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameGUI extends JFrame {
    private GameModel model;

    public GameGUI(GameModel model) {
        this.model = model;
        initGUI();
    }

    private void initGUI() {
        // Set layout for the frame
        setLayout(new BorderLayout());

        // Create a label for displaying active creature information
        JLabel activeCreatureLabel = new JLabel("Active Creature: None");
        add(activeCreatureLabel, BorderLayout.NORTH);

        // Create buttons for different actions
        JButton viewInventoryButton = new JButton("View Inventory");
        JButton exploreAreaButton = new JButton("Explore Area");
        JButton evolveCreatureButton = new JButton("Evolve Creature");

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewInventoryButton);
        buttonPanel.add(exploreAreaButton);
        buttonPanel.add(evolveCreatureButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners to buttons
        viewInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display inventory
                displayInventory();
            }
        });

        exploreAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display area exploration options
                displayAreaOptions();
            }
        });

        evolveCreatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display creature evolution options
                displayEvolveOptions();
            }
        });

        // Set frame properties
        setTitle("Game GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void displayInventory() {
        // Display inventory information using JOptionPane
        StringBuilder inventoryText = new StringBuilder("Inventory:\n");
        for (Creature creature : model.getInventory().getCreatures()) {
            inventoryText.append(creature.getName()).append("\n");
        }

        JOptionPane.showMessageDialog(this, inventoryText.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayAreaOptions() {
        // Display area exploration options using JOptionPane
        Object[] options = {"Area 1", "Area 2", "Area 3"};
        int choice = JOptionPane.showOptionDialog(this, "Choose an area to explore:", "Explore Area",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                // Explore Area 1
                JOptionPane.showMessageDialog(this, "Exploring Area 1");
                break;
            case 1:
                // Explore Area 2
                JOptionPane.showMessageDialog(this, "Exploring Area 2");
                break;
            case 2:
                // Explore Area 3
                JOptionPane.showMessageDialog(this, "Exploring Area 3");
                break;
            default:
                // Invalid choice
                JOptionPane.showMessageDialog(this, "Invalid area choice", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayEvolveOptions() {
        // Display creature evolution options using JOptionPane
        List<Creature> creatures = model.getInventory().getCreatures().subList(0, Math.min(2, model.getInventory().getCreatures().size()));

        JComboBox<Creature> creature1ComboBox = new JComboBox<>(creatures.toArray(new Creature[0]));
        JComboBox<Creature> creature2ComboBox = new JComboBox<>(creatures.toArray(new Creature[0]));

        Object[] message = {
                "Select the first creature:", creature1ComboBox,
                "Select the second creature:", creature2ComboBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Evolve Creature", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            Creature creature1 = (Creature) creature1ComboBox.getSelectedItem();
            Creature creature2 = (Creature) creature2ComboBox.getSelectedItem();

            if (creature1 != null && creature2 != null) {
                // Perform evolution logic
                evolveCreatures(creature1, creature2);
            } else {
                JOptionPane.showMessageDialog(this, "Please select two creatures for evolution.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void evolveCreatures(Creature creature1, Creature creature2) {
        // Implement your evolution logic here
        // For demonstration purposes, display a message
        JOptionPane.showMessageDialog(this, "Evolution Successful! Evolved Creature: " + "EvolvedCreature", "Evolution", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Create the model
        GameModel model = new GameModel();

        // Create the GUI
        GameGUI gui = new GameGUI(model);

        // Display the GUI
        gui.setVisible(true);
    }
}

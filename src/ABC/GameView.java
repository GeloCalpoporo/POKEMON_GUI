package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {
    private GameModel model;

    public GameView(GameModel model) {
        this.model = model;
        initGUI();
    }

    private void initGUI() {
        // Initialize GUI components
        // Add labels, buttons, or other components based on your design

        // Example: Add a label to display the active creature's information
        JLabel activeCreatureLabel = new JLabel();
        Creature activeCreature = model.getInventory().getActiveCreature();
        activeCreatureLabel.setText("Active Creature: " + (activeCreature != null ? activeCreature.getName() : "None"));
        add(activeCreatureLabel);

        // Example: Add a button to explore an area
        JButton exploreAreaButton = new JButton("Explore Area");
        exploreAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.exploreArea();  // Adjust this based on your exploreArea method in GameModel
            }
        });
        add(exploreAreaButton);

        // Example: Add a button to evolve creatures
        JButton evolveCreatureButton = new JButton("Evolve Creatures");
        evolveCreatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayEvolveCreatureMenu();
            }
        });
        add(evolveCreatureButton);

        // Add other GUI components based on your design

        // Set layout and other properties
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    public void displayInventory(Inventory inventory) {
        // Display inventory information (already implemented in a previous response)

        // Assuming you have a prompt for user input
        int userChoice = getUserChoice();
        handleInventoryOption(userChoice);
    }

    public void displayExploreAreaMenu() {
        // Placeholder for displaying the explore area menu
    }

    public void displayEvolveCreatureMenu() {
        JFrame frame = new JFrame("Evolve Creature");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel selectLabel = new JLabel("Select creatures for evolution:");
        panel.add(selectLabel);

        JComboBox<Creature> creature1ComboBox = new JComboBox<>(model.getInventory().getCreatures().toArray(new Creature[0]));
        JComboBox<Creature> creature2ComboBox = new JComboBox<>(model.getInventory().getCreatures().toArray(new Creature[0]));

        panel.add(creature1ComboBox);
        panel.add(new JLabel("and"));
        panel.add(creature2ComboBox);

        JButton evolveButton = new JButton("Evolve");
        evolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Creature creature1 = (Creature) creature1ComboBox.getSelectedItem();
                Creature creature2 = (Creature) creature2ComboBox.getSelectedItem();

                if (creature1 != null && creature2 != null) {
                    // Call the evolveCreatures method from GameModel with indices
                    model.evolveCreatures(model.getInventory().getCreatures().indexOf(creature1),
                            model.getInventory().getCreatures().indexOf(creature2));
                    frame.dispose(); // Close the frame after evolution attempt
                    updateView();  // Update the main view after evolution
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select two creatures for evolution.");
                }
            }
        });

        panel.add(evolveButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

    public void updateView() {
        // Clear existing components from the frame
        getContentPane().removeAll();

        // Reinitialize GUI components based on the updated model state
        initGUI();

        // Pack and repaint the frame to reflect changes
        pack();
        repaint();
    }

    // Other methods for handling different views and user inputs

    private int getUserChoice() {
        // Implement the logic to get the user's choice (e.g., using Scanner)
        // You can use this method for user input in various parts of your views
        return 0; // Placeholder, replace with actual user input
    }

    private void handleInventoryOption(int option) {
        // Implement the logic to handle the selected inventory option
        switch (option) {
            case 1:
                // Change Active Creature
                model.getInventory().prompt();
                break;
            case 2:
                // Back
                // You can add more logic or update the view accordingly
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

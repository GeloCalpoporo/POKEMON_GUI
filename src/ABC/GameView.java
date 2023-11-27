package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameView extends JFrame {
    private GameModel model;
    private GameController controller;

    public GameView(GameModel model, GameController controller) {
        this.model = model;
        this.controller = controller;
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
                // Notify the controller to display inventory
                controller.displayInventory();
            }
        });

        exploreAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Notify the controller to display area options
                controller.displayAreaOptions();
            }
        });

        evolveCreatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Notify the controller to display evolve options
                controller.displayEvolveOptions();
            }
        });

        // Set frame properties
        setTitle("Game GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setActiveCreature(String creatureName) {
        // Update the active creature label
        // This method can be called by the controller to update the view
        // with the active creature information
    }
}

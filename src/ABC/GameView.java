package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameView extends JFrame {
    private GameModel model;
    private GameController controller;
    private Creature activeCreature;
    private Inventory userInventory;
    private JLabel activeCreatureLabel; // Added JLabel for active creature information

    public GameView(GameModel model, GameController controller) {
        this.model = model;
        this.controller = controller;
        initGUI();
    }

    public Inventory getUserInventory() {
        return userInventory;
    }

    private void initGUI() {
        // Set layout for the frame
        setLayout(new BorderLayout());


        // Create a label for displaying active creature information
        /*
        activeCreature = model.getActiveCreature();
        activeCreatureLabel = new JLabel("Active Creature: " + activeCreature.getName());
        add(activeCreatureLabel, BorderLayout.NORTH); */

        // Create buttons for different actions

        JButton exploreAreaButton = new JButton("Explore Area");
        JButton evolveCreatureButton = new JButton("Evolve Creature");
        JButton viewInventoryButton = new JButton("View Inventory");

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(exploreAreaButton);
        buttonPanel.add(evolveCreatureButton);
        buttonPanel.add(viewInventoryButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners to buttons
        exploreAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.displayAreaOptions();
            }
        });

        evolveCreatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.displayEvolveOptions();
            }
        });

        viewInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.openInventoryScreen();
            }
        });

        // Set frame properties
        setTitle("Game GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setActiveCreature(String creatureName) {
        // Update the active creature label
        // This method can be called by the controller to update the view
        // with the active creature information
    }
    public void updateActiveCreatureLabel(String creatureInfo) {
        activeCreatureLabel.setText("Active Creature: " + creatureInfo);
    }
}

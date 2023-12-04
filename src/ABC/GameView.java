package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The GameView class represents the graphical user interface of the game.
 */
public class GameView extends JFrame {
    private GameModel model;
    private GameController controller;
    private Creature activeCreature;
    private Inventory userInventory;
    private JLabel activeCreatureLabel; // Added JLabel for active creature information

    /**
     * Constructs a GameView instance with the specified model and controller.
     *
     * @param model      The GameModel instance.
     * @param controller The GameController instance.
     */
    public GameView(GameModel model, GameController controller) {
        this.model = model;
        this.controller = controller;
        initGUI();
    }

    /**
     * Gets the user's inventory.
     *
     * @return The user's inventory.
     */
    public Inventory getUserInventory() {
        return userInventory;
    }

    private void initGUI() {
        // Set layout for the frame
        setLayout(new BorderLayout());

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

    /**
     * Sets the controller for the view.
     *
     * @param controller The GameController instance.
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Sets the active creature based on the provided creature name.
     *
     * @param creatureName The name of the active creature.
     */
    public void setActiveCreature(String creatureName) {
    }

    /**
     * Updates the active creature label with the provided creature information.
     *
     * @param creatureInfo The information to be displayed for the active creature.
     */
    public void updateActiveCreatureLabel(String creatureInfo) {
        activeCreatureLabel.setText("Active Creature: " + creatureInfo);
    }
}

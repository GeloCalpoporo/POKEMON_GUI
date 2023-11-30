package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Area1Screen class represents the graphical user interface (GUI) for Area 1 in the game.
 * It allows the player to navigate the area and potentially encounter creatures.
 *
 * This class extends JFrame to create a window for the game area.
 */
public class Area1Screen extends JFrame {
    // Constants for tile size and area dimensions
    private static final int TILE_SIZE = 50; // Size of each tile in pixels
    private static final int AREA_WIDTH = 5;  // Area width
    private static final int AREA_HEIGHT = 1; // Area height

    // Player's current position
    private int currentX = 0;
    private int currentY = 0;

    // List of creatures that can be encountered in the area
    private List<Creature> encounteredCreatures;

    // List of EL1 (Evolution Level 1) creatures
    private List<Creature> creaturesEL1;

    // Reference to the GameModel
    private GameModel gameModel;

    /**
     * Constructs an Area1Screen with the specified encountered creatures and a reference to the GameModel.
     *
     * @param encounteredCreatures List of creatures that can be encountered in the area.
     * @param gameModel            Reference to the GameModel.
     */
    public Area1Screen(List<Creature> encounteredCreatures, GameModel gameModel) {
        this.gameModel = gameModel; // Set the reference to the GameModel

        // Set up the JFrame properties
        setTitle("Area 1");
        setSize(AREA_WIDTH * TILE_SIZE, AREA_HEIGHT * TILE_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize encountered creatures and EL1 creatures
        this.encounteredCreatures = encounteredCreatures;

        // Create a JPanel for drawing the area
        JPanel areaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTiles(g);
                drawPlayer(g);
            }
        };
        areaPanel.setPreferredSize(new Dimension(AREA_WIDTH * TILE_SIZE, AREA_HEIGHT * TILE_SIZE));

        // Create buttons for player movement and exit
        JButton upButton = new JButton("↑");
        JButton downButton = new JButton("↓");
        JButton leftButton = new JButton(" ←");
        JButton rightButton = new JButton("→");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to the movement buttons
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(0, -1);
                checkEncounter(); // Check for creature encounter after moving
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(0, 1);
                checkEncounter(); // Check for creature encounter after moving
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(-1, 0);
                checkEncounter(); // Check for creature encounter after moving
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(1, 0);
                checkEncounter(); // Check for creature encounter after moving
            }
        });

        // Add action listener to the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMenu();
            }
        });

        // Create a JPanel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(upButton);
        buttonPanel.add(downButton);
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);
        buttonPanel.add(exitButton);

        // Set layout and add components to the JFrame
        setLayout(new BorderLayout());
        add(areaPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Draws the tiles of the game area.
     *
     * @param g Graphics object for drawing.
     */
    private void drawTiles(Graphics g) {
        for (int y = 0; y < AREA_HEIGHT; y++) {
            for (int x = 0; x < AREA_WIDTH; x++) {
                g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        for (Creature creature : encounteredCreatures) {
            int creatureX = creature.getPosition()[0];
            int creatureY = creature.getPosition()[1];

            g.setColor(Color.BLUE);
            g.drawString(creature.getName(), creatureX * TILE_SIZE, creatureY * TILE_SIZE);
        }
    }

    /**
     * Draws the player's character on the game area.
     *
     * @param g Graphics object for drawing.
     */
    private void drawPlayer(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(currentX * TILE_SIZE, currentY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    /**
     * Moves the player's character in the game area.
     *
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     */
    public void movePlayer(int dx, int dy) {
        if (currentX + dx >= 0 && currentX + dx < AREA_WIDTH && currentY + dy >= 0 && currentY + dy < AREA_HEIGHT) {
            currentX += dx;
            currentY += dy;
            repaint();
        }
    }

    /**
     * Checks if specific conditions are met.
     *
     * @return true if the conditions are met, false otherwise.
     */
    private boolean conditionsMet() {
        // Add your conditions here
        // For example, you might want to check if the player is in a specific position
        return (currentX == 2 && currentY == 0); // Just an example condition
    }

    /**
     * Checks for a creature encounter with a random chance.
     * If a creature is encountered, starts a battle.
     */
    private void checkEncounter() {
        Random random = new Random();
        if (random.nextInt(100) < 40) { // 40% chance to encounter a creature
            Creature enemy = getRandomCreature();
            gameModel.setEnemy(enemy);
            gameModel.startBattle(gameModel.getActiveCreature(), gameModel.getEnemy(), gameModel.getInventory()); // Open battle screen
        }
    }

    /**
     * Retrieves a random EL1 (Evolution Level 1) creature from the collection of encountered creatures.
     *
     * @return A random EL1 creature, or null if there are no EL1 creatures available.
     */
    private Creature getRandomCreature() {
        Random random = new Random();
        List<Creature> enemy = new ArrayList<>();

        for (Creature creature : encounteredCreatures) {
            if (creature.getEvolutionLevel() == 1) {
                enemy.add(creature);
            }
        }

        if (!enemy.isEmpty()) {
            int randomIndex = random.nextInt(enemy.size());
            return enemy.get(randomIndex);
        }

        return null; // Return null if no eligible creatures are found
    }

    /**
     * Returns to the main menu or the previous screen.
     */
    private void returnToMenu() {
        dispose();
    }
}

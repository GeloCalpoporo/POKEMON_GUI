package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Area3Screen class represents the graphical user interface for Area 3 in the game.
 * It allows the player to navigate the area, encounter creatures, and take actions.
 */
public class Area3Screen extends JFrame {
    private static final int TILE_SIZE = 50; // Size of each tile in pixels
    private int currentX = 0;
    private int currentY = 0;

    private static final int AREA_WIDTH = 3; // Area width
    private static final int AREA_HEIGHT = 3; // Area height

    private List<Creature> encounteredCreatures;

    private GameModel gameModel; // Reference to the GameModel

    /**
     * Constructs an Area3Screen instance.
     *
     * @param encounteredCreatures The list of creatures that can be encountered in the area.
     * @param gameModel            The reference to the GameModel.
     */
    public Area3Screen(List<Creature> encounteredCreatures, GameModel gameModel) {
        this.gameModel = gameModel; // Set the reference to the GameModel

        setTitle("Area 3");
        setSize(AREA_WIDTH * TILE_SIZE, AREA_HEIGHT * TILE_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.encounteredCreatures = encounteredCreatures;

        JPanel areaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTiles(g);
                drawPlayer(g);
            }
        };
        areaPanel.setPreferredSize(new Dimension(AREA_WIDTH * TILE_SIZE, AREA_HEIGHT * TILE_SIZE));

        JButton upButton = new JButton("↑");
        JButton downButton = new JButton("↓");
        JButton leftButton = new JButton(" ←");
        JButton rightButton = new JButton("→");
        JButton exitButton = new JButton("Exit");

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

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMenu();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(upButton);
        buttonPanel.add(downButton);
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);
        buttonPanel.add(exitButton);

        setLayout(new BorderLayout());
        add(areaPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Draws the tiles representing the area on the graphics component.
     *
     * @param g The Graphics object to draw on.
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
     * Draws the player character on the graphics component.
     *
     * @param g The Graphics object to draw on.
     */
    private void drawPlayer(Graphics g) {
        // Draw a simple human figure
        int bodyHeight = TILE_SIZE / 2;
        int headRadius = TILE_SIZE / 4;

        // Draw body
        g.setColor(Color.RED);
        g.fillRect(currentX * TILE_SIZE, (currentY + 1) * TILE_SIZE - bodyHeight, TILE_SIZE, bodyHeight);

        // Draw head
        g.setColor(Color.YELLOW); // Head color
        g.fillOval(currentX * TILE_SIZE + TILE_SIZE / 4, (currentY + 1) * TILE_SIZE - bodyHeight - headRadius, headRadius * 2, headRadius * 2);
    }

    /**
     * Moves the player character in the specified direction.
     *
     * @param dx The change in the x-direction.
     * @param dy The change in the y-direction.
     */
    public void movePlayer(int dx, int dy) {
        if (currentX + dx >= 0 && currentX + dx < AREA_WIDTH && currentY + dy >= 0 && currentY + dy < AREA_HEIGHT) {
            currentX += dx;
            currentY += dy;
            repaint();
        }
    }

    /**
     * Checks the conditions for triggering a creature encounter and initiates a battle if conditions are met.
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
     * Retrieves a random creature from the list of encountered creatures.
     *
     * @return A random creature, or null if no eligible creatures are found.
     */
    private Creature getRandomCreature() {
        Random random = new Random();
        List<Creature> enemy = new ArrayList<>();

        for (Creature creature : encounteredCreatures) {
            if (creature.getEvolutionLevel() == 1 || creature.getEvolutionLevel() == 2 || creature.getEvolutionLevel() == 3) {
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
     * This method is called when the "Exit" button is clicked.
     */
    private void returnToMenu() {
        // Implement logic to return to the menu screen
        // For now, let's just close the current area screen
        dispose();
    }
}

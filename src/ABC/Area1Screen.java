// Area1Screen.java
package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Area1Screen extends JFrame {
    private static final int TILE_SIZE = 50; // Size of each tile in pixels
    private int currentX = 0;
    private int currentY = 0;

    private static final int AREA_WIDTH = 5; // Area width
    private static final int AREA_HEIGHT = 1; // Area height

    private List<Creature> encounteredCreatures;

    private List<Creature> creaturesEL1; // List of EL1 creatures
    private GameModel gameModel; // Reference to the GameModel

    public Area1Screen(List<Creature> encounteredCreatures, GameModel gameModel) {
        this.gameModel = gameModel; // Set the reference to the GameModel

        setTitle("Area 1");
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

        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
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

    private void drawPlayer(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(currentX * TILE_SIZE, currentY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public void movePlayer(int dx, int dy) {
        if (currentX + dx >= 0 && currentX + dx < AREA_WIDTH && currentY + dy >= 0 && currentY + dy < AREA_HEIGHT) {
            currentX += dx;
            currentY += dy;
            repaint();
        }
    }

    private boolean conditionsMet() {
        // Add your conditions here
        // For example, you might want to check if the player is in a specific position
        return (currentX == 2 && currentY == 0); // Just an example condition
    }

    private void checkEncounter() {
        Random random = new Random();
        if (random.nextInt(100) < 40) { // 40% chance to encounter a creature
            Creature enemy = getRandomCreature();
            gameModel.setEnemy(enemy);
            gameModel.startBattle(gameModel.getActiveCreature(), gameModel.getEnemy(), gameModel.getInventory()); // Open battle screen
        }
    }


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


    private void returnToMenu() {
        // Implement logic to return to the menu screen
        // For now, let's just close the current area screen
        dispose();
    }
}

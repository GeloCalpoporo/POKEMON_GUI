package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Area1Screen extends JFrame {
    private static final int TILE_SIZE = 50;
    private int currentX = 0;
    private int currentY = 0;

    private static final int AREA_WIDTH = 5;
    private static final int AREA_HEIGHT = 1;

    private List<Creature> encounteredCreatures;

    public Area1Screen(List<Creature> encounteredCreatures) {
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
            }
        });

        // ... (similar listeners for other buttons)

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
        // Draw area tiles
        for (int y = 0; y < AREA_HEIGHT; y++) {
            for (int x = 0; x < AREA_WIDTH; x++) {
                g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // Draw labels for encountered creatures
        for (Creature creature : encounteredCreatures) {
            if (creature.getEvolutionLevel() == 1) {
                int creatureX = creature.getPosition()[0];
                int creatureY = creature.getPosition()[1];

                g.setColor(Color.BLUE);
                g.drawString(creature.getName(), creatureX * TILE_SIZE, creatureY * TILE_SIZE);
            }
        }
    }

    private void drawPlayer(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(currentX * TILE_SIZE, currentY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public void movePlayer(int dx, int dy) {
        // Check if the new position is within the bounds of the area
        if (currentX + dx >= 0 && currentX + dx < AREA_WIDTH && currentY + dy >= 0 && currentY + dy < AREA_HEIGHT) {
            currentX += dx;
            currentY += dy;
            repaint();
        }
    }
}

package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class Area2Screen extends JFrame {

    private static final int TILE_SIZE = 50; // Size of each tile in pixels
    private int currentX = 0;
    private int currentY = 0;

    private static final int AREA_WIDTH = 3; // Area width for Area2
    private static final int AREA_HEIGHT = 3; // Area height for Area2

    public Area2Screen() {
        setTitle("Area 2");
        setSize(AREA_WIDTH * TILE_SIZE, AREA_HEIGHT * TILE_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(0, 1);
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(-1, 0);
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(1, 0);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void drawTiles(Graphics g) {
        for (int y = 0; y < AREA_HEIGHT; y++) {
            for (int x = 0; x < AREA_WIDTH; x++) {
                g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
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


}

package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryScreen {
    private Inventory inventory;
    private JFrame frame;
    private JLabel activeCreatureImageLabel; // Added for active creature image
    private JLabel activeCreatureDetailsLabel; // Display active creature details
    private CreatureImages creatureImages;
    private GameModel model;
    private String imageDirectory = "res\\Creatures_List\\";

    public InventoryScreen(Inventory inventory, GameModel model) {
        this.inventory = inventory;
        this.model = model;

        frame = new JFrame("Creature Inventory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600); // Adjusted size
        frame.setLayout(new BorderLayout());

        // Left Panel (Active Creature)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Active Creature"));

        activeCreatureImageLabel = new JLabel();
        leftPanel.add(activeCreatureImageLabel, BorderLayout.NORTH); // Place image label at the top

        activeCreatureDetailsLabel = new JLabel("", SwingConstants.CENTER);
        activeCreatureDetailsLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        leftPanel.add(activeCreatureDetailsLabel, BorderLayout.CENTER); // Place details label below image

        // Right Panel (List of Creatures)
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("List of Creatures"));

        creatureImages = new CreatureImages(inventory.getCreatures());
        creatureImages.setPreferredSize(new Dimension(580, 400)); // Adjusted width
        rightPanel.add(new JScrollPane(creatureImages), BorderLayout.CENTER);

        // Set Preferred Size for Left and Right Panels
        leftPanel.setPreferredSize(rightPanel.getPreferredSize());

        // Add Left and Right Panels to the Frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        // Buttons Panel (Top)
        JButton changeActiveButton = new JButton("Change Active Creature");
        changeActiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showActiveCreatureDialog();
            }
        });

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(changeActiveButton);
        buttonPanel.add(goBackButton);

        frame.add(buttonPanel, BorderLayout.NORTH);

        updateActiveCreatureLabel();

        frame.setVisible(true);
    }

    private void updateActiveCreatureLabel() {
        Creature activeCreature = model.getActiveCreature();

        if (activeCreature != null) {
            String imagePath = imageDirectory + activeCreature.getName() + ".jpg";
            ImageIcon icon = createImageIcon(imagePath);
            activeCreatureImageLabel.setIcon(icon);

            // Update active creature details label
            String detailsText = String.format("<html><div style='text-align: center;'>"
                            + "<img src='" + imagePath + "'/><br>"
                            + "Name: %s<br>Type: %s<br>Family: %s<br>Evolution Level: %s</div></html>",
                    activeCreature.getName(), activeCreature.getType(), activeCreature.getFamily(), activeCreature.getEvolutionLevel());
            activeCreatureDetailsLabel.setText(detailsText);
        } else {
            activeCreatureImageLabel.setIcon(null);
            activeCreatureDetailsLabel.setText("");
        }
    }

    private void showActiveCreatureDialog() {
        List<Creature> el1Creatures = model.getInventory().getCreatures();

        if (!el1Creatures.isEmpty()) {
            JFrame dialogFrame = new JFrame("Select Active Creature");
            dialogFrame.setSize(300, 200);
            dialogFrame.setLayout(new BorderLayout());

            JComboBox<String> creaturesComboBox = new JComboBox<>();
            updateCreaturesComboBox(creaturesComboBox);

            JButton setButton = new JButton("Set Active Creature");
            setButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedCreatureName = (String) creaturesComboBox.getSelectedItem();
                    for (Creature creature : el1Creatures) {
                        if (creature.getName().equals(selectedCreatureName)) {
                            int confirmOption = JOptionPane.showConfirmDialog(frame,
                                    "Do you want to swap the active creature with " + creature.getName() + "?",
                                    "Swap Confirmation", JOptionPane.YES_NO_OPTION);

                            if (confirmOption == JOptionPane.YES_OPTION) {
                                model.getInventory().deleteCreature(creature);
                                model.getInventory().addCreature(model.getActiveCreature());
                                model.setActiveCreature(creature);
                                updateActiveCreatureLabel();
                                updateCreaturesComboBox(creaturesComboBox);
                                dialogFrame.dispose();
                            }
                            break;
                        }
                    }
                }
            });

            JButton backButton = new JButton("Go Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogFrame.dispose();
                }
            });

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            buttonPanel.add(setButton);
            buttonPanel.add(backButton);

            dialogFrame.add(creaturesComboBox, BorderLayout.CENTER);
            dialogFrame.add(buttonPanel, BorderLayout.SOUTH);
            dialogFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "No predefined creatures available.");
        }
    }

    private void updateCreaturesComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        List<Creature> creatures = model.getInventory().getCreatures();

        for (Creature creature : creatures) {
            comboBox.addItem(creature.getName());
        }
    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


}

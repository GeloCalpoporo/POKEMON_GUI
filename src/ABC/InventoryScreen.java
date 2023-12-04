package ABC;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InventoryScreen {
    private Inventory inventory;
    private JFrame frame;
    private JLabel activeCreatureImageLabel;
    private JLabel activeCreatureDetailsLabel;
    private CreatureImages creatureImages;
    private CreatureImages activeCreatureImage;
    private GameModel model;
    private String imageDirectory = "res\\Creatures_List\\";

    public InventoryScreen(Inventory inventory, GameModel model) {
        this.inventory = inventory;
        this.model = model;

        frame = new JFrame("Creature Inventory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Active Creature"));

        activeCreatureImageLabel = new JLabel();
        leftPanel.add(activeCreatureImageLabel, BorderLayout.NORTH);

        activeCreatureImage =  new CreatureImages(model.getActiveCreature());
        leftPanel.add(activeCreatureImage, BorderLayout.CENTER);

        activeCreatureDetailsLabel = new JLabel("", SwingConstants.CENTER);
        activeCreatureDetailsLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        leftPanel.add(activeCreatureDetailsLabel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("List of Creatures"));

        creatureImages = new CreatureImages(inventory.getCreatures());
        creatureImages.setPreferredSize(new Dimension(580, 400));
        rightPanel.add(new JScrollPane(creatureImages), BorderLayout.CENTER);

        leftPanel.setPreferredSize(rightPanel.getPreferredSize());

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        JButton changeActiveButton = new JButton("Change Active Creature");
        changeActiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showActiveCreatureDialog();
            }
        });

        JButton filterByTypeButton = new JButton("Filter by Type");
        JPanel typeFilterPanel = new JPanel(new GridLayout(1, 2));
        typeFilterPanel.add(filterByTypeButton);

        JButton filterFireButton = new JButton("Fire Type");
        JButton filterGrassButton = new JButton("Grass Type");
        JButton filterWaterButton = new JButton("Water Type");
        JButton filterAllTypesButton = new JButton("All Types"); // Added button for All Types


        filterFireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterCreaturesByType("Fire");
            }
        });

        filterGrassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterCreaturesByType("Grass");
            }
        });

        filterWaterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterCreaturesByType("Water");
            }
        });

        filterAllTypesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show all creatures (remove type filter)
                creatureImages.updateCreatureListImages(model.getInventory().getCreatures());
            }
        });

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 7)); // Adjusted layout to accommodate new button
        buttonPanel.add(changeActiveButton);
        buttonPanel.add(filterFireButton);
        buttonPanel.add(filterGrassButton);
        buttonPanel.add(filterWaterButton);
        buttonPanel.add(filterAllTypesButton); // Added button for All Types
        buttonPanel.add(goBackButton);

        frame.add(typeFilterPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }


    private void filterCreaturesByType(String selectedType) {
        List<Creature> allCreatures = model.getInventory().getCreatures();
        List<Creature> filteredCreatures = new ArrayList<>();

        for (Creature creature : allCreatures) {
            if (creature.getType().equalsIgnoreCase(selectedType)) {
                filteredCreatures.add(creature);
            }
        }

        creatureImages.updateCreatureListImages(filteredCreatures);
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
                                updateCreaturesComboBox(creaturesComboBox);
                                updateActiveCreatureImage();
                                updateCreatureListImages();
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

    private void updateActiveCreatureImage() {
        activeCreatureImage.updateActiveCreatureImage(model.getActiveCreature());
    }

    private void updateCreatureListImages() {
        creatureImages.updateCreatureListImages(model.getInventory().getCreatures());
    }

    private void updateCreaturesComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        List<Creature> creatures = model.getInventory().getCreatures();

        for (Creature creature : creatures) {
            comboBox.addItem(creature.getName());
        }
    }

}

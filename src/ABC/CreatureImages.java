package ABC;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.util.List;
public class CreatureImages extends JPanel {

    private static final Map<String, String> FILE_NAMES = createFileNamesMap();
    private JLabel imageLabel;

    public CreatureImages(List<Creature> userCreatures) {
        // Set layout manager for JPanel
        setLayout(new GridLayout(5, 6, 10, 10)); // Adjust rows and columns based on your preference

        // Create a panel for each creature and add it to the main panel
        for (Creature creature : userCreatures) {
            JPanel creaturePanel = createCreaturePanel(creature);
            add(creaturePanel);
        }
    }

    public CreatureImages(Creature activeCreature) {
        setLayout(new BorderLayout());

        JPanel creaturePanel = createCreaturePanel(activeCreature);
        add(creaturePanel, BorderLayout.CENTER);
    }

    public void updateActiveCreatureImage(Creature activeCreature) {
        removeAll(); // Remove existing components from the panel

        JPanel creaturePanel = createCreaturePanel(activeCreature);
        add(creaturePanel, BorderLayout.CENTER);

        revalidate(); // Revalidate the panel to reflect the changes
        repaint(); // Repaint the panel to display the updated image
    }

    public void updateCreatureListImages(List<Creature> creatures) {
        removeAll(); // Remove existing components from the panel

        // Set layout manager for JPanel
        setLayout(new GridLayout(5, 6, 10, 10)); // Adjust rows and columns based on your preference

        // Create a panel for each creature and add it to the main panel
        for (Creature creature : creatures) {
            JPanel creaturePanel = createCreaturePanel(creature);
            add(creaturePanel);
        }

        revalidate(); // Revalidate the panel to reflect the changes
        repaint(); // Repaint the panel to display the updated images
    }

    private JPanel createCreaturePanel(Creature creature) {
        JPanel creaturePanel = new JPanel();
        creaturePanel.setLayout(new BorderLayout());

        try {
            // Load image using ImageIO
            InputStream inputStream = getClass().getResourceAsStream(FILE_NAMES.get(creature.getName()));
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Calculate the scaled width and height for inventory creatures
            int originalWidth = bufferedImage.getWidth();
            int originalHeight = bufferedImage.getHeight();
            int maxWidth = 200; // Adjust this value based on your preference
            int maxHeight = 200; // Adjust this value based on your preference

            double widthScale = (double) maxWidth / originalWidth;
            double heightScale = (double) maxHeight / originalHeight;
            double scale = Math.min(widthScale, heightScale);

            int scaledWidth = (int) (originalWidth * scale);
            int scaledHeight = (int) (originalHeight * scale);

            // Resize the image
            Image scaledImage = bufferedImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

            // Draw the scaled image using paintIcon
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon(scaledImage));

            // Add labels for creature details
            JPanel detailsPanel = new JPanel(new GridLayout(4, 1));
            JLabel nameLabel = new JLabel("Name: " + creature.getName());
            JLabel typeLabel = new JLabel("Type: " + creature.getType());
            JLabel familyLabel = new JLabel("Family: " + creature.getFamily());
            JLabel evolutionLabel = new JLabel("Evolution Level: " + creature.getEvolutionLevel());

            detailsPanel.add(nameLabel);
            detailsPanel.add(typeLabel);
            detailsPanel.add(familyLabel);
            detailsPanel.add(evolutionLabel);

            creaturePanel.add(imageLabel, BorderLayout.CENTER);
            creaturePanel.add(detailsPanel, BorderLayout.SOUTH);

            // Add a mouse listener for testing
            creaturePanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // Handle mouse click for the creature (if needed)
                    System.out.println("Clicked on " + creature.getName());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return creaturePanel;
    }


    private static Map<String, String> createFileNamesMap() {
        Map<String, String> fileNames = new HashMap<>();
        fileNames.put("BROWNISAUR", "/BROWNISAUR.jpg");
        fileNames.put("CANDAROS", "/CANDAROS.jpg");
        fileNames.put("CHOCOFLUFF", "/CHOCOFLUFF.jpg");
        fileNames.put("CHOCOLISH", "/CHOCOLISH.jpg");
        fileNames.put("CHOCOLITE", "/CHOCOLITE.jpg");
        fileNames.put("CHOCOSAUR", "/CHOCOSAUR.jpg");
        fileNames.put("CHOCOWOOL", "/CHOCOWOOL.jpg");
        fileNames.put("CROBERRY", "/CROBERRY.jpg");
        fileNames.put("DEWICE", "/DEWICE.jpg");
        fileNames.put("FRUBAT", "/FRUBAT.jpg");
        fileNames.put("FUDGASAUR", "/FUDGASAUR.jpg");
        fileNames.put("GOLBERRY", "/GOLBERRY.jpg");
        fileNames.put("ICESUNDAE", "/ICESUNDAE.jpg");
        fileNames.put("KIRLICAKE", "/KIRLICAKE.jpg");
        fileNames.put("MALTS", "/MALTS.jpg");
        fileNames.put("OSHACONE", "/OSHACONE.jpg");
        fileNames.put("PARFELURE", "/PARFELURE.jpg");
        fileNames.put("PARFURE", "/PARFURE.jpg");
        fileNames.put("PARFWIT", "/PARFWIT.jpg");
        fileNames.put("PIETOISE", "/PIETOISE.jpg");
        fileNames.put("SAMURCONE", "/SAMURCONE.jpg");
        fileNames.put("SQUIRPIE", "/SQUIRPIE.jpg");
        fileNames.put("STRAWANDER", "/STRAWANDER.jpg");
        fileNames.put("STRAWIZARD", "/STRAWIZARD.jpg");
        fileNames.put("STRAWLEON", "/STRAWLEON.jpg");
        fileNames.put("TARTORTLE", "/TARTORTLE.jpg");
        fileNames.put("VELVEVOIR", "/VELVEVOIR.jpg");

        return fileNames;
    }
}

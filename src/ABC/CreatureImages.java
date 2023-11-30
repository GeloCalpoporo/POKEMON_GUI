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

    public CreatureImages(List<Creature> userCreatures) {
        // Set layout manager for JPanel
        setLayout(new GridLayout(5, 6, 10, 10)); // Adjust rows and columns based on your preference

        // Create a panel for each creature and add it to the main panel
        for (Creature creature : userCreatures) {
            JPanel creaturePanel = createCreaturePanel(creature);
            add(creaturePanel);
        }
    }


    private JPanel createCreaturePanel(Creature creature) {
        JPanel creaturePanel = new JPanel();
        creaturePanel.setLayout(new BorderLayout());

        // Load image using ImageIO
        try {
            InputStream inputStream = getClass().getResourceAsStream(FILE_NAMES.get(creature.getName()));
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);

            // Convert BufferedImage to Image
            Image image = bufferedImage.getScaledInstance(
                    bufferedImage.getWidth(),
                    bufferedImage.getHeight(),
                    Image.SCALE_SMOOTH
            );

            // Draw the image using paintIcon
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon(image));
            creaturePanel.add(imageLabel, BorderLayout.CENTER);

            // Add a label for the creature name
            JLabel nameLabel = new JLabel(creature.getName());
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            creaturePanel.add(nameLabel, BorderLayout.SOUTH);

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

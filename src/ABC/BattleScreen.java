package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.List;

/**
 * The BattleScreen class represents the graphical user interface (GUI) for the battle sequence in the game.
 * It allows the player to perform various actions during battle, such as attacking, swapping creatures, attempting to catch,
 * and running away.
 *
 * This class extends JFrame to create a window for the battle screen.
 */
public class BattleScreen extends JFrame {

    private GameModel gameModel; // Reference to the combined GameModel
    private int userActions = 3; // Number of available user actions

    // UI Components
    private JProgressBar enemyHealthBar;
    private JLabel activeCreatureLabel;
    private JLabel enemyInfoLabel;
    private JButton attackButton;
    private JButton swapButton;
    private JButton catchButton;
    private JButton runButton;

    private JPanel creaturePanel;
    private JPanel actionPanel;

    /**
     * Constructs a BattleScreen with the specified GameModel.
     *
     * @param gameModel Reference to the GameModel.
     */
    public BattleScreen(GameModel gameModel) {
        this.gameModel = gameModel;

        setTitle("Battle Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize UI components
        activeCreatureLabel = new JLabel("Active Creature: " + gameModel.getActiveCreature().getName() + " (EL" + gameModel.getActiveCreature().getEvolutionLevel() + ", " + gameModel.getActiveCreature().getType() + ")");
        enemyInfoLabel = new JLabel("Enemy: " + gameModel.getEnemy().getName() + " (EL" + gameModel.getEnemy().getEvolutionLevel() + ", " + gameModel.getEnemy().getType() + ")");
        enemyHealthBar = new JProgressBar(0, gameModel.getEnemyMaxHealth());
        enemyHealthBar.setStringPainted(true);
        enemyHealthBar.setMaximum(gameModel.getEnemyMaxHealth());
        enemyHealthBar.setValue(gameModel.getEnemyHealth());
        enemyHealthBar.setString(gameModel.getEnemyHealth() + "/" + gameModel.getEnemyMaxHealth());

        attackButton = new JButton("Attack");
        swapButton = new JButton("Swap");
        catchButton = new JButton("Catch");
        runButton = new JButton("Run");

        // Add action listeners to the buttons
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAttack();
            }
        });

        swapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSwap();
            }
        });

        catchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCatch();
            }
        });


        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performRun();
            }
        }); //PALITAN MO TO NG ITOWW-->


        creaturePanel = new JPanel();
        creaturePanel.setLayout(new GridLayout(5, 1));
        creaturePanel.add(activeCreatureLabel);
        creaturePanel.add(enemyInfoLabel);
        creaturePanel.add(enemyHealthBar);

        actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 4));
        actionPanel.add(attackButton);
        actionPanel.add(swapButton);
        actionPanel.add(catchButton);
        actionPanel.add(runButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(creaturePanel, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Updates the UI components based on the GameModel state.
     */
    private void updateUI() {
        activeCreatureLabel.setText("Active Creature: " + gameModel.getActiveCreature().getName() + " (EL" + gameModel.getActiveCreature().getEvolutionLevel() + ", " + gameModel.getActiveCreature().getType() + ")");
        enemyInfoLabel.setText("Enemy: " + gameModel.getEnemy().getName() + " (EL" + gameModel.getEnemy().getEvolutionLevel() + ", " + gameModel.getEnemy().getType() + ")");
        enemyHealthBar.setMaximum(gameModel.getEnemyMaxHealth());
        enemyHealthBar.setValue(gameModel.getEnemyHealth());
        enemyHealthBar.setString(gameModel.getEnemyHealth() + "/" + gameModel.getEnemyMaxHealth());

        checkBattleOutcome();
    }

    /**
     * Checks the outcome of the battle and takes appropriate actions.
     */
    private void checkBattleOutcome() {
        if (gameModel.getEnemyHealth() <= 0) {
            JOptionPane.showMessageDialog(this, "You defeated the enemy!");
            dispose(); // Close the Battle Screen
        } else if (gameModel.getUserActions() <= 0) {
            JOptionPane.showMessageDialog(this, "Out of actions. Returning to the area screen.");
            dispose(); // Close the Battle Screen
        }
    }

    /**
     * Performs the attack action during battle.
     */
    private void performAttack() {
        if (userActions == 0) {
            JOptionPane.showMessageDialog(this, "You've reached the maximum number of attempts (3).");
            return;
        }
        userActions--;

        int damage = calculateDamage();
        gameModel.reduceEnemyHealth(damage);

        JOptionPane.showMessageDialog(this, "You attacked the enemy for " + damage + " damage!");
        updateUI();
    }

    /**
     * Performs the swap action during battle.
     */
    private void performSwap() {
        // Display a dialog with a list of EL1 creatures from the user's inventory
        List<Creature> el1Creatures = gameModel.getInventory().getCreatures();

        if (el1Creatures.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No EL1 creatures available for swapping.");
            return;
        }

        JComboBox<Creature> creatureComboBox = new JComboBox<>(el1Creatures.toArray(new Creature[0]));

        int option = JOptionPane.showConfirmDialog(this, creatureComboBox, "Swap Creature", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            Creature selectedCreature = (Creature) creatureComboBox.getSelectedItem();

            // Check if the selected creature is the same as the active creature
            if (selectedCreature.equals(gameModel.getActiveCreature())) {
                JOptionPane.showMessageDialog(this, "You can't swap with the same creature!");
            } else {
                // Swap the active creature with the selected creature
                gameModel.getInventory().deleteCreature(selectedCreature);
                gameModel.getInventory().addCreature(gameModel.getActiveCreature());
                gameModel.setActiveCreature(selectedCreature);

                JOptionPane.showMessageDialog(this, "Swapped to " + selectedCreature.getName() + "!");
                updateUI();
            }
        }
    }

    /**
     * Performs the catch action during battle.
     */


   private void performCatch() {
        if (userActions == 0) {
            JOptionPane.showMessageDialog(this, "You've reached the maximum number of attempts (3).");
            return;
        }

        double catchRate = calculateCatchRate();
        userActions--;

        if (catchRate > Math.random()) {
            gameModel.getUserInventory().addCreature(gameModel.getEnemy());
            gameModel.getUserInventory().addCreature(gameModel.getEnemy());
            JOptionPane.showMessageDialog(this, "You successfully caught the enemy!");
            gameModel.setEnemyHealth(0);
            updateUI();
        } else {
            JOptionPane.showMessageDialog(this, "Catch attempt failed.");
        }

        checkBattleOutcome();
    }


    /**
     * Performs the run action during battle.
     */
    private void performRun() {
        JOptionPane.showMessageDialog(this, "You ran away from the battle.");
        dispose(); // Close the Battle Screen
    }

    /**
     * Checks if there is a type advantage for the attacker over the defender.
     *
     * @param attackerType Type of the attacking creature.
     * @param defenderType Type of the defending creature.
     * @return True if there is a type advantage, false otherwise.
     */
    private boolean isTypeAdvantage(String attackerType, String defenderType) {
        return (attackerType.equals("Fire") && defenderType.equals("Grass")) ||
                (attackerType.equals("Grass") && defenderType.equals("Water")) ||
                (attackerType.equals("Water") && defenderType.equals("Fire"));
    }

    /**
     * Calculates the damage inflicted during an attack based on random factors and type advantages.
     *
     * @return The calculated damage value.
     */
    private int calculateDamage() {
        Random random = new Random();
        int baseDamage = random.nextInt(10) + 1;
        int damage = baseDamage * gameModel.getActiveCreature().getEvolutionLevel();

        System.out.println("You attacked the enemy for " + baseDamage + " base damage.");

        if (isTypeAdvantage(gameModel.getActiveCreature().getType(), gameModel.getEnemy().getType())) {
            damage = (int) (damage * 1.5);
            System.out.println("Multiplied Damage: " + damage);
        } else {
            System.out.println("No type advantage. Damage remains: " + damage);
        }

        return damage;
    }

    /**
     * Calculates the catch rate during a catch attempt based on a specific formula.
     *
     * @return The calculated catch rate.
     */
    private double calculateCatchRate() {
        int enemyHealth = gameModel.getEnemyHealth();
        return (40.0 + 50.0 - enemyHealth) / 100.0;
    }
}

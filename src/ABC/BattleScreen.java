package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleScreen extends JFrame {

    private GameModel gameModel; // Reference to the combined GameModel
    // UI Components
    private JLabel activeCreatureLabel;
    private JLabel enemyInfoLabel;
    private JLabel enemyHealthLabel;
    private JButton attackButton;
    private JButton swapButton;
    private JButton catchButton;
    private JButton runButton;

    public BattleScreen(GameModel gameModel) {
        this.gameModel = gameModel;

        setTitle("Battle Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        activeCreatureLabel = new JLabel("Active Creature: " + gameModel.getActiveCreature().getName() + " (EL" + gameModel.getActiveCreature().getEvolutionLevel() + ", " + gameModel.getActiveCreature().getType() + ")");
        enemyInfoLabel = new JLabel("Enemy: " + gameModel.getEnemy().getName() + " (EL" + gameModel.getEnemy().getEvolutionLevel() + ", " + gameModel.getEnemy().getType() + ")");
        enemyHealthLabel = new JLabel("Enemy Health: " + gameModel.getEnemyHealth());

        attackButton = new JButton("Attack");
        swapButton = new JButton("Swap");
        catchButton = new JButton("Catch");
        runButton = new JButton("Run");

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
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(activeCreatureLabel);
        panel.add(enemyInfoLabel);
        panel.add(enemyHealthLabel);
        panel.add(attackButton);
        panel.add(swapButton);
        panel.add(catchButton);
        panel.add(runButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateUI() {
        // Update UI components based on the GameModel state
        activeCreatureLabel.setText("Active Creature: " + gameModel.getActiveCreature().getName() + " (EL" + gameModel.getActiveCreature().getEvolutionLevel() + ", " + gameModel.getActiveCreature().getType() + ")");
        enemyInfoLabel.setText("Enemy: " + gameModel.getEnemy().getName() + " (EL" + gameModel.getEnemy().getEvolutionLevel() + ", " + gameModel.getEnemy().getType() + ")");
        enemyHealthLabel.setText("Enemy Health: " + gameModel.getEnemyHealth());

        checkBattleOutcome();
    }

    private void checkBattleOutcome() {
        if (gameModel.getEnemyHealth() <= 0) {
            JOptionPane.showMessageDialog(this, "You defeated the enemy!");
            dispose(); // Close the Battle Screen
        } else if (gameModel.getUserActions() <= 0) {
            JOptionPane.showMessageDialog(this, "Out of actions. Returning to the area screen.");
            dispose(); // Close the Battle Screen
        }
    }

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

    private void performSwap() {
        // Display a dialog with a list of EL1 creatures from the user's inventory
        List<Creature> el1Creatures = gameModel.getCreatureList().getEl1Creatures();

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
                gameModel.getUserInventory().deleteCreature(selectedCreature);
                gameModel.getUserInventory().addCreature(gameModel.getActiveCreature());
                gameModel.setActiveCreature(selectedCreature);
                JOptionPane.showMessageDialog(this, "Swapped to " + selectedCreature.getName() + "!");
                updateUI();
            }
        }
    }




    private void performCatch() {
        double catchRate = calculateCatchRate();
        if (catchRate > Math.random()) {
            gameModel.getUserInventory().addCreature(gameModel.getEnemy());
            JOptionPane.showMessageDialog(this, "You successfully caught the enemy!");
            gameModel.setEnemyHealth(0); // Enemy is caught
            updateUI();
        } else {
            JOptionPane.showMessageDialog(this, "Catch attempt failed.");
        }

        checkBattleOutcome();
    }

    private void performRun() {
        JOptionPane.showMessageDialog(this, "You ran away from the battle.");
        dispose(); // Close the Battle Screen
    }

    private boolean isTypeAdvantage(String attackerType, String defenderType) {
        if ((attackerType.equals("Fire") && defenderType.equals("Grass")) ||
                (attackerType.equals("Grass") && defenderType.equals("Water")) ||
                (attackerType.equals("Water") && defenderType.equals("Fire"))) {
            return true;
        }
        return false;
    }

    private int calculateDamage() {
        // Implement your damage calculation logic here
        // This is a placeholder, replace it with your logic
        return 10;
    }

    private double calculateCatchRate() {
        // Calculate catch rate based on the specified formula
        int enemyHealth = gameModel.getEnemyHealth();
        return (40.0 + 50.0 - enemyHealth) / 100.0;
    }


}

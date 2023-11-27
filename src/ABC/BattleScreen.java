package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleScreen extends JFrame {

    private Creature activeCreature;
    private Creature enemy;
    private int enemyHealth;
    private Inventory userInventory;
    private int userActions;

    // UI Components
    private JLabel activeCreatureLabel;
    private JLabel enemyInfoLabel;
    private JLabel enemyHealthLabel;
    private JButton attackButton;
    private JButton swapButton;
    private JButton catchButton;
    private JButton runButton;

    public BattleScreen(Creature activeCreature, Creature enemy, Inventory userInventory) {
        this.activeCreature = activeCreature;
        this.enemy = enemy;
        this.userInventory = userInventory;
        this.enemyHealth = 50;
        this.userActions = 3; // Maximum allowed actions

        setTitle("Battle Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        activeCreatureLabel = new JLabel("Active Creature: " + activeCreature.getName() + " (EL" + activeCreature.getEvolutionLevel() + ", " + activeCreature.getType() + ")");
        enemyInfoLabel = new JLabel("Enemy: " + enemy.getName() + " (EL" + enemy.getEvolutionLevel() + ", " + enemy.getType() + ")");
        enemyHealthLabel = new JLabel("Enemy Health: " + enemyHealth);

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

    private void performAttack() {
        int damage = calculateDamage();
        enemyHealth -= damage;
        JOptionPane.showMessageDialog(this, "You attacked the enemy for " + damage + " damage!");
        updateEnemyHealthLabel();

        checkBattleOutcome();
    }

    private void performSwap() {
        // Implement swap logic
        JOptionPane.showMessageDialog(this, "SWAP option is not implemented in this example.");
    }

    private void performCatch() {
        double catchRate = calculateCatchRate();
        if (catchRate > Math.random()) {
            userInventory.addCreature(enemy);
            JOptionPane.showMessageDialog(this, "You successfully caught the enemy!");
            enemyHealth = 0; // Enemy is caught
            updateEnemyHealthLabel();
        } else {
            JOptionPane.showMessageDialog(this, "Catch attempt failed.");
        }

        checkBattleOutcome();
    }

    private void performRun() {
        JOptionPane.showMessageDialog(this, "You ran away from the battle.");
        dispose(); // Close the Battle Screen
    }

    private void updateEnemyHealthLabel() {
        enemyHealthLabel.setText("Enemy Health: " + enemyHealth);
    }

    private void checkBattleOutcome() {
        if (enemyHealth <= 0) {
            JOptionPane.showMessageDialog(this, "You defeated the enemy!");
            dispose(); // Close the Battle Screen
        } else if (userActions <= 0) {
            JOptionPane.showMessageDialog(this, "Out of actions. Returning to the area screen.");
            dispose(); // Close the Battle Screen
        }
    }

    private int calculateDamage() {
        // Implement your damage calculation logic here
        // This is a placeholder, replace it with your logic
        return 10;
    }

    private double calculateCatchRate() {
        // Implement your catch rate calculation logic here
        // This is a placeholder, replace it with your logic
        return 0.5;
    }

    public static void main(String[] args) {
        // Example usage
        Creature activeCreature = new Creature(1, "Charmander", "Fire", "SomeType", 1);
        Creature enemy = new Creature(1, "Bulbasaur", "Grass", "SomeType", 1);

        Inventory userInventory = new Inventory();
        BattleScreen battleScreen = new BattleScreen(activeCreature, enemy, userInventory);
    }
}

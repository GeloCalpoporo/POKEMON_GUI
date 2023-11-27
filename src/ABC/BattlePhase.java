package ABC;

import java.util.Random;
import java.util.Scanner;

/**
 * The BattlePhase class represents a battle phase between the user's active creature and an enemy creature.
 * It allows users to engage in battles, perform various actions, and calculate battle outcomes.
 */
public class BattlePhase {
    private Creature activeCreature;
    private Creature enemy;
    private Inventory userInventory;
    private int enemyHealth;
    private int userActions;

    /**
     * Creates an instance of BattlePhase with the user's active creature, an enemy creature, and the user's inventory.
     *
     * @param activeCreature  The user's active creature.
     * @param enemy           The enemy creature.
     * @param userInventory   The user's inventory.
     */
    public BattlePhase(Creature activeCreature, Creature enemy, Inventory userInventory) {
        this.activeCreature = activeCreature;
        this.enemy = enemy;
        this.userInventory = userInventory;
        this.enemyHealth = 50;
        this.userActions = 3; // Maximum allowed actions
    }

    /**
     * Initiates the battle phase, allowing the user to make choices and battle with the enemy creature.
     */
    public void startBattlePhase() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Battle Start!");
        System.out.println("Active Creature: " + activeCreature.getName() + " (" + activeCreature.getType() + ", EL" + activeCreature.getEvolutionLevel() + ")");
        System.out.println("Enemy: " + enemy.getName() + " (" + enemy.getType() + ", EL" + enemy.getEvolutionLevel() + ") with " + enemyHealth + " health.");

        while (userActions > 0 && enemyHealth > 0) {
            System.out.println("\nAvailable Actions:");
            System.out.println("1. ATTACK");
            System.out.println("2. SWAP (if more creatures are available)");
            System.out.println("3. CATCH");
            System.out.println("4. RUN (Ends the battle)");

            System.out.println("Enemy HP: " + enemyHealth); // Add this line to display enemy's HP

            System.out.print("Choose an action: ");
            int actionChoice = scanner.nextInt();

            switch (actionChoice) {
                case 1:
                    int damage = calculateDamage();
                    enemyHealth -= damage;
                    System.out.println("You attacked the enemy for " + damage + " damage!");
                    break;
                case 2:
                    // Implement SWAP logic to change the active creature
                    System.out.println("SWAP option is not implemented in this example.");
                    break;
                case 3:
                    double catchRate = (40 + 50 - enemyHealth) / enemyHealth;
                    if (catchRate > Math.random()) {
                        userInventory.addCreature(enemy);
                        System.out.println("You successfully caught the enemy!");
                        enemyHealth = 0; // Enemy is caught
                    } else {
                        System.out.println("Catch attempt failed.");
                    }
                    break;
                case 4:
                    System.out.println("You ran away from the battle.");
                    return; // Ends the battle phase
                default:
                    System.out.println("Invalid choice. Choose a valid action.");
            }

            userActions--; // Deduct user action
        }

        System.out.println("Battle Phase ended. Returning to the area screen.");
    }


    /**
     * Checks if the attacker has a type advantage over the defender.
     *
     * @param attackerType The type of the attacker.
     * @param defenderType The type of the defender.
     * @return True if there's a type advantage; otherwise, false.
     */
    private boolean isTypeAdvantage(String attackerType, String defenderType) {
        if ((attackerType.equals("Fire") && defenderType.equals("Grass")) ||
                (attackerType.equals("Grass") && defenderType.equals("Water")) ||
                (attackerType.equals("Water") && defenderType.equals("Fire"))) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the damage dealt by the user's active creature to the enemy creature.
     *
     * @return The damage dealt in the battle.
     */
    private int calculateDamage() {
        Random random = new Random();
        int baseDamage = random.nextInt(10) + 1;
        int damage = baseDamage * activeCreature.getEvolutionLevel();

        System.out.println("You attacked the enemy for " + baseDamage + " base damage.");

        if (isTypeAdvantage(activeCreature.getType(), enemy.getType())) {
            damage = (int)(damage * 1.5);
            System.out.println("Multiplied Damage: " + damage);
        } else {
            System.out.println("No type advantage. Damage remains: " + damage);
        }

        return damage;
    }

    /**
     * Calculates the catch rate for capturing the enemy creature.
     *
     * @return The catch rate for capturing the enemy creature.
     */
    private double calculateCatchRate() {
        return (40.0 + 50 - enemyHealth);
    }
}

package ABC;

import java.util.Random;

/**
 * The Area class represents a generic area within the game. It allows users to explore the area,
 * encounter creatures, and engage in battles.
 */
public class Area {
    protected int[][] areaGrid; // Represents the area's grid
    protected int[] userPosition; // Current position of the user
    protected Inventory userInventory; // User's inventory
    protected Creature activeCreature; // User's active creature
    protected static final double ENCOUNTER_CHANCE = 0.4; // 40% chance of encountering a creature

    /**
     * Creates an instance of Area with the provided inventory and active creature.
     *
     * @param inventory      The user's inventory.
     * @param activeCreature The user's active creature.
     */
    public Area(Inventory inventory, Creature activeCreature) {
        this.userInventory = inventory;
        this.activeCreature = activeCreature;
    }

    /**
     * Displays the current state of the area, including the user's position.
     */
    public void displayArea() {
        // Implementation for displaying the area grid
    }

    /**
     * Moves the user within the area based on the specified direction.
     *
     * @param direction The direction in which the user wishes to move.
     */
    public void moveUser(Direction direction) {
        // Implementation for moving the user within the area
    }

    /**
     * Determines whether the user encounters a creature in the area based on a 40% chance.
     *
     * @return True if the user encounters a creature; otherwise, false.
     */
    public boolean encounterCreature() {
        // Implementation for determining if the user encounters a creature
        Random random = new Random();
        double randomValue = random.nextDouble();
        return randomValue < ENCOUNTER_CHANCE;
    }

    /**
     * Retrieves a random creature based on the area's specific rules.
     *
     * @return A random creature from the list.
     */
    public Creature getRandomCreature() {
        // Implementation for getting a random creature
        return null; // Placeholder, should be overridden in subclasses
    }


    /**
     * Starts the battle phase when the user encounters a creature.
     *
     * @param enemy The creature encountered by the user.
     */
    protected void startBattlePhase(Creature enemy) {
        // Assuming you have a BattlePhase class, create an instance and start the battle
        BattlePhase battlePhase = new BattlePhase(activeCreature, enemy, userInventory);
        battlePhase.startBattlePhase();
    }
}

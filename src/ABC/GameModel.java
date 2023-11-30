package ABC;

/**
 * The GameModel class represents the core model of the game, managing the game state and logic.
 * It includes functionalities related to the player's inventory, encountered creatures, evolution, and battles.
 */
import java.util.List;

public class GameModel {
    private Inventory inventory;
    private CreatureList creatureList;
    private Evolution evolution;

    // Battle-related properties
    private Creature activeCreature;
    private Creature enemy;

    private int enemyHealth;
    private Inventory userInventory;
    private int userActions;

    /**
     * Constructs a GameModel instance, initializing the inventory, creature list, and evolution components.
     */
    public GameModel() {
        this.inventory = new Inventory();
        this.creatureList = new CreatureList();
        this.evolution = new Evolution(this);
    }

    /**
     * Retrieves the player's inventory.
     *
     * @return The Inventory object representing the player's collection of creatures.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Retrieves a list of creatures encountered in the game.
     *
     * @return A list of Creature objects representing encountered creatures.
     */
    public List<Creature> getEncounteredCreatures() {
        return creatureList.getAllCreatures();
    }

    /**
     * Retrieves the CreatureList object containing predefined creatures and their details.
     *
     * @return The CreatureList object representing the list of creatures in the game.
     */
    public CreatureList getCreatureList() {
        return creatureList;
    }

    /**
     * Retrieves the Evolution object responsible for managing creature evolution.
     *
     * @return The Evolution object handling creature evolution in the game.
     */
    public Evolution getEvolution() {
        return evolution;
    }

    /**
     * Initiates the exploration of Area 1 in the game.
     */
    public void exploreArea1() {
        List<Creature> encounteredCreatures = getEncounteredCreatures();
        Area1Screen area1Screen = new Area1Screen(encounteredCreatures, this);
        area1Screen.setVisible(true);
    }

    /**
     * Initiates the exploration of Area 2 in the game.
     */
    public void exploreArea2() {
        List<Creature> encounteredCreatures = getEncounteredCreatures();
        Area2Screen area2Screen = new Area2Screen(encounteredCreatures, this);
        area2Screen.setVisible(true);
    }

    /**
     * Initiates the exploration of Area 3 in the game.
     */
    public void exploreArea3() {
        List<Creature> encounteredCreatures = getEncounteredCreatures();
        Area3Screen area3Screen = new Area3Screen(encounteredCreatures, this);
        area3Screen.setVisible(true);
    }

    /**
     * Initiates a battle in the game with the specified active creature, enemy, and user inventory.
     *
     * @param activeCreature The player's active creature in the battle.
     * @param enemy          The enemy creature in the battle.
     * @param userInventory  The player's inventory during the battle.
     */
    public void startBattle(Creature activeCreature, Creature enemy, Inventory userInventory) {
        // Initialize battle properties
        this.activeCreature = activeCreature;
        this.enemy = enemy;
        this.userInventory = userInventory;
        this.enemyHealth = 50;
        this.userActions = 3;

        BattleScreen battleScreen = new BattleScreen(this);
        battleScreen.setVisible(true);
    }


    /**
     * Retrieves the player's captured creatures.
     *
     * @return The List of Creature objects representing the player's captured creatures.
     */
    public List<Creature> getCapturedCreatures() {
        return inventory.getCreatures();
    }

    /**
     * Retrieves the currently active creature in the game.
     *
     * @return The active creature controlled by the player.
     */
    public Creature getActiveCreature() {
        return activeCreature;
    }

    /**
     * Sets the active creature in the game.
     *
     * @param selectedCreature The creature to set as the active one.
     */
    public void setActiveCreature(Creature selectedCreature) {
        this.activeCreature = selectedCreature;
    }

    /**
     * Retrieves the enemy creature in the current battle.
     *
     * @return The enemy creature in the battle.
     */
    public Creature getEnemy() {
        return enemy;
    }

    /**
     * Sets the enemy creature in the current battle.
     *
     * @param enemy The enemy creature to set for the battle.
     */
    public void setEnemy(Creature enemy) {
        this.enemy = enemy;
    }

    /**
     * Retrieves the current health of the enemy creature in the battle.
     *
     * @return The current health of the enemy creature.
     */
    public int getEnemyHealth() {
        return enemyHealth;
    }

    /**
     * Retrieves the maximum health of the enemy creature in the battle.
     *
     * @return The maximum health of the enemy creature.
     */
    public int getEnemyMaxHealth() {
        return 50;
    }

    /**
     * Sets the health of the enemy creature in the battle.
     *
     * @param health The health value to set for the enemy creature.
     */
    public void setEnemyHealth(int health) {
        this.enemyHealth = health;
    }

    /**
     * Retrieves the player's inventory during the battle.
     *
     * @return The Inventory object representing the player's inventory in the battle.
     */
    public Inventory getUserInventory() {
        return userInventory;
    }

    /**
     * Retrieves the remaining number of actions the player can take in the battle.
     *
     * @return The remaining number of actions the player has in the battle.
     */
    public int getUserActions() {
        return userActions;
    }

    /**
     * Reduces the health of the enemy creature in the battle by the specified damage amount.
     *
     * @param damage The amount of damage to be applied to the enemy creature.
     */
    public void reduceEnemyHealth(int damage) {
        enemyHealth -= damage;
    }
}

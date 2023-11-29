// GameModel.java
package ABC;

import java.util.List;

public class GameModel {
    private Inventory inventory;
    private CreatureList creatureList;
    private Evolution evolution;

    // Battle-related properties
    private Creature activeCreature;
    private Creature enemy;

    private int[] position;


    private int enemyHealth;
    private Inventory userInventory;
    private int userActions;

    public GameModel() {
        this.inventory = new Inventory();
        this.creatureList = new CreatureList();
        this.evolution = new Evolution(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Creature> getEncounteredCreatures() {
        return creatureList.getAllCreatures();
    }

    public CreatureList getCreatureList() {
        return creatureList;
    }


    public Evolution getEvolution() {
        return evolution;
    }

    public void exploreArea1() {
        List<Creature> encounteredCreatures = getEncounteredCreatures();
        Area1Screen area1Screen = new Area1Screen(encounteredCreatures, this);
        area1Screen.setVisible(true);
    }

    public void exploreArea2() {
        List<Creature> encounteredCreatures = getEncounteredCreatures();
        Area2Screen area2Screen = new Area2Screen(encounteredCreatures, this);
        area2Screen.setVisible(true);
    }

    public void exploreArea3() {
        // Implement logic for exploring Area 3
        // You can add logic to encounter creatures and perform other actions
        List<Creature> encounteredCreatures = getEncounteredCreatures();
        Area3Screen area3Screen = new Area3Screen(encounteredCreatures, this);
        area3Screen.setVisible(true);
    }

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

    // Add other methods and data relevant to the game logic

    // Battle-related methods

    public Creature getActiveCreature() {
        return activeCreature;
    }

    public void setActiveCreature(Creature SelectedCreature) {
        this.activeCreature = SelectedCreature;
    }

    public Creature getEnemy() {
        return enemy;
    }

    public void setEnemy(Creature enemy) {
        this.enemy = enemy;
    }


    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int health) {
        this.enemyHealth = health;
    }

    public int[] getPosition() {
        return this.position;
    }

    public Inventory getUserInventory() {
        return userInventory;
    }

    public int getUserActions() {
        return userActions;
    }

    public int reduceUserActions() {
       return userActions--;
    }

    public void reduceEnemyHealth(int damage) {
        enemyHealth -= damage;
    }
}

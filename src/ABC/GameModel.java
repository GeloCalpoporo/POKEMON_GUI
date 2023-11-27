package ABC;

import java.util.ArrayList;
import java.util.List;

class GameModel {
    private Inventory inventory;
    private List<Creature> encounteredCreatures;
    private CreatureList creatureList;  // Added CreatureList

    public GameModel() {
        this.inventory = new Inventory();
        this.encounteredCreatures = new ArrayList<>();
        this.creatureList = new CreatureList();  // Initialize CreatureList
        // You can add other game data and logic initialization here
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Creature> getEncounteredCreatures() {
        return encounteredCreatures;
    }

    public CreatureList getCreatureList() {
        return creatureList;
    }

    // Placeholder method for exploring an area
    public void exploreArea(int areaChoice) {
        // Implement logic for exploring the chosen area
        System.out.println("Exploring Area " + areaChoice);
    }

    // Placeholder method for evolving creatures
    public void evolveCreatures(int indexCreature1, int indexCreature2) {
        // Implement logic for evolving creatures
        System.out.println("Evolving creatures at indices " + indexCreature1 + " and " + indexCreature2);
    }

    // Add other methods and data relevant to the game logic
}

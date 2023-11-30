package ABC;

import java.util.ArrayList;
import java.util.List;

/**
 * The Inventory class manages the user's collection of creatures.
 */
public class Inventory {
    private List<Creature> creatures = new ArrayList<>();
    private Creature activeCreature;

    /**
     * Adds a creature to the inventory.
     *
     * @param creature The creature to add to the inventory.
     */
    public void addCreature(Creature creature) {
        Creature newCreature = new Creature(
                creature.getUserChoice(),
                creature.getName(),
                creature.getType(),
                creature.getFamily(),
                creature.getEvolutionLevel()
        );

        this.creatures.add(newCreature);
    }

    /**
     * Sets the active creature in the inventory.
     *
     * @param activeCreature The creature to set as the active one.
     */
    public void setActiveCreature(Creature activeCreature) {
        this.activeCreature = activeCreature;
    }

    /**
     * Retrieves the list of creatures in the inventory.
     *
     * @return The list of creatures in the inventory.
     */
    public List<Creature> getCreatures() {
        return creatures;
    }

    /**
     * Retrieves the currently active creature from the inventory.
     *
     * @return The active creature, or null if none is selected.
     */
    public Creature getActiveCreature() {
        return this.activeCreature;
    }

    /**
     * Changes the active creature in the inventory based on the provided index.
     *
     * @param index The index of the creature to set as the active creature.
     */
    public void changeActiveCreature(int index) {
        if (index >= 0 && index < this.creatures.size()) {
            this.activeCreature = this.creatures.get(index);
            System.out.println("Active creature changed to: " + this.activeCreature.getName());
        } else {
            System.out.println("Invalid creature index.");
        }
    }

    /**
     * Deletes the specified creature from the inventory.
     *
     * @param creature The creature to delete from the inventory.
     */
    public void deleteCreature(Creature creature) {
        creatures.remove(creature);
    }

    /**
     * Adds a creature to the inventory.
     *
     * @param creature The creature to add to the inventory.
     */
    public void addInventory(Creature creature) {
        creatures.add(creature);
    }

    /**
     * Swaps the positions of two creatures in the inventory.
     *
     * @param index1 The index of the first creature.
     * @param index2 The index of the second creature.
     */
    public void swapCreatures(int index1, int index2) {
        if (index1 >= 0 && index1 < creatures.size() && index2 >= 0 && index2 < creatures.size()) {
            // Swap the creatures at the specified indices
            Creature temp = creatures.get(index1);
            creatures.set(index1, creatures.get(index2));
            creatures.set(index2, temp);
        } else {
            System.out.println("Invalid indices for creature swap.");
        }
    }
}

package ABC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public List<Creature> getcreatures(){
        return this.creatures;
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


    public void deleteCreature(Creature creature) {
        creatures.remove(creature);
    }

    public void addInventory(Creature creature) {
        creatures.add(creature);
    }

    /**
     * Retrieves the list of creatures in the inventory.
     *
     * @return The list of creatures in the inventory.
     */
    public List<Creature> getCreatures() {
        return creatures;
    }


}

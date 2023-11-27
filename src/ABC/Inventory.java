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

    /**
     * Retrieves the currently active creature from the inventory.
     *
     * @return The active creature, or null if none is selected.
     */
    public Creature getActiveCreature() {
        return this.activeCreature;
    }

    /**
     * Lists all creatures in the inventory.
     */
    public void listInventory() {
        for (int var1 = 0; var1 < this.creatures.size(); ++var1) {
            System.out.println("Creature #" + (var1 + 1));
            System.out.println(this.creatures.get(var1));
            System.out.println("----------------------");
        }

        System.out.println("Active Creature:");
        if (this.activeCreature != null) {
            System.out.println(this.activeCreature);
        } else {
            System.out.println("No active creature selected.");
        }
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

    public void prompt() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Change Active Creature? Yes/No");
        String changeActive = inputScanner.next();
        if (changeActive.equalsIgnoreCase("Yes")) {
            if (this.creatures.size() > 1) { // Check if there is more than one creature in the inventory
                System.out.println("Enter the index of the creature you want to set as the active creature:");
                int index = inputScanner.nextInt();
                changeActiveCreature(index - 1);
                System.out.println("Active creature changed.");
            } else {
                System.out.println("You cannot change the active creature because you have only one creature.");
            }
        } else {
            System.out.println("Active creature remains unchanged.");
        }
    }
}

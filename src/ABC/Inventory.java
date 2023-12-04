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


    public List<Creature> getCreatures() {
        return creatures;
    }


    /**
     * Deletes the specified creature from the inventory.
     *
     * @param creature The creature to delete from the inventory.
     */
    public void deleteCreature(Creature creature) {
        creatures.remove(creature);
    }

}

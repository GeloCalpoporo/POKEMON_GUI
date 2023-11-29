package ABC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The CreatureList class manages a list of creatures and provides methods to access them.
 */
public class CreatureList {
    private List<Creature> creatures = new ArrayList<>();
    private List<Creature> el1Creatures;

    /**
     * Constructs a CreatureList instance and initializes it with a predefined set of creatures.
     */
    public CreatureList() {
        // Initialize the list of creatures
        this.creatures.add(new Creature(1, "STRAWANDER", "Fire", "A", 1));
        this.creatures.add(new Creature(1, "STRAWLEON", "Fire", "A", 2));
        this.creatures.add(new Creature(1, "STRAWIZARD", "Fire", "A", 3));
        this.creatures.add(new Creature(2, "CHOCOWOOL", "Fire", "B", 1));
        this.creatures.add(new Creature(2, "CHOCOFLUFF", "Fire", "B", 2));
        this.creatures.add(new Creature(2, "CANDAROS", "Fire", "B", 3));
        this.creatures.add(new Creature(3, "PARFWIT", "Fire", "C", 1));
        this.creatures.add(new Creature(3, "PARFURE", "Fire", "C", 2));
        this.creatures.add(new Creature(3, "PAREFELURE", "Fire", "C", 3));

        this.creatures.add(new Creature(4, "BROWNISAUR", "Grass", "D", 1));
        this.creatures.add(new Creature(4, "CHOCOSAUR", "Grass", "D", 2));
        this.creatures.add(new Creature(4, "FUDGASAUR", "Grass", "D", 3));
        this.creatures.add(new Creature(5, "FRUBAT", "Grass", "E", 1));
        this.creatures.add(new Creature(5, "GOLBERRY", "Grass", "E", 2));
        this.creatures.add(new Creature(5, "CROBERRY", "Grass", "E", 3));
        this.creatures.add(new Creature(6, "MALTS", "Grass", "F", 1));
        this.creatures.add(new Creature(6, "KIRLICAKE", "Grass", "F", 2));
        this.creatures.add(new Creature(6, "VELVEVOIR", "Grass", "F", 3));

        this.creatures.add(new Creature(7, "SQUIRPIE", "Water", "G", 1));
        this.creatures.add(new Creature(7, "TARTORTLE", "Water", "G", 2));
        this.creatures.add(new Creature(7, "PIETOISE", "Water", "G", 3));
        this.creatures.add(new Creature(8, "CHOCOLITE", "Water", "H", 1));
        this.creatures.add(new Creature(8, "CHOCOLISH", "Water", "H", 2));
        this.creatures.add(new Creature(8, "ICESUNDAE", "Water", "H", 3));
        this.creatures.add(new Creature(9, "OSHACONE", "Water", "I", 1));
        this.creatures.add(new Creature(9, "DEWICE", "Water", "I", 2));
        this.creatures.add(new Creature(9, "SAMURCONE", "Water", "I", 3));

        // Initialize the EL1 creatures list
        el1Creatures = new ArrayList<>();
        for (Creature creature : this.creatures) {
            if (creature.getEvolutionLevel() == 1) {
                el1Creatures.add(creature);
            }
        }
    }

    /**
     * Retrieves a list of all creatures available in the collection.
     *
     * @return A list of all creatures.
     */
    public List<Creature> getAllCreatures() {
        return this.creatures;
    }

    public List<Creature> getEl1Creatures() {
        return this.el1Creatures;
    }

    /**
     * Retrieves a random EL1 (Evolution Level 1) creature from the collection.
     *
     * @return A random EL1 creature, or null if there are no EL1 creatures available.
     */
    public Creature getRandomEL1Creature() {
        // Check if the EL1 creatures list is not empty
        if (!el1Creatures.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(el1Creatures.size());
            return el1Creatures.get(randomIndex);
        } else {
            // Handle the case where there are no EL1 creatures
            return null; // You can return null or handle it differently
        }
    }
}

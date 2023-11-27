package ABC;

import java.util.Random;

public class Evolution {

    private Inventory creatureInventory;
    private Random random = new Random();

    public Evolution(Inventory creatureInventory) {
        this.creatureInventory = creatureInventory;
    }

    public EvolutionResult evolveCreatures(Creature creature1, Creature creature2) {
        if (isEligibleForEvolution(creature1, creature2)) {
            String name = calculateEvolvedName(creature1);

            Creature evolvedCreature = new Creature(name, creature1.getType(), creature1.getFamily(), creature1.getEvolutionLevel() + 1);

            // Remove selected creatures from the inventory
            creatureInventory.deleteCreature(creature1);
            creatureInventory.deleteCreature(creature2);

            // Add the evolved creature to the end of the inventory
            creatureInventory.addInventory(evolvedCreature);
            return new EvolutionResult(true, evolvedCreature);
        }
        else {
            return new EvolutionResult(false, null);
        }
    }

    private boolean isEligibleForEvolution(Creature creature1, Creature creature2) {
        return creature1.getEvolutionLevel() == creature2.getEvolutionLevel() &&
                creature1.getFamily().equals(creature2.getFamily()) &&
                creature1.getEvolutionLevel() < 3 &&
                creature2.getEvolutionLevel() < 3;
    }

    private String calculateEvolvedName(Creature creature) {
        String type = creature.getType();
        String family = creature.getFamily();
        int level = creature.getEvolutionLevel() + 1; // Use getEvolutionLevel() instead of getLevel()

        if (family.equals("A")) {
            if (type.equals("FIRE")) {
                if (level == 2) {
                    return "STRAWLEON";
                } else {
                    return "STRAWIZARD";
                }
            }
        } else if (family.equals("B")) {
            if (type.equals("FIRE")) {
                if (level == 2) {
                    return "CHOCOFLUFF";
                } else {
                    return "CANDROS";
                }
            }
        } else if (family.equals("C")) {
            if (type.equals("FIRE")) {
                if (level == 2) {
                    return "PARFURE";
                } else {
                    return "PARFELURE";
                }
            }
        } else if (family.equals("D")) {
            if (type.equals("LEAF")) {
                if (level == 2) {
                    return "CHOCOSAUR";
                } else {
                    return "FUDGASAUR";
                }
            }
        } else if (family.equals("E")) {
            if (type.equals("LEAF")) {
                if (level == 2) {
                    return "GOLBERRY";
                } else {
                    return "CROBERRY";
                }
            }
        } else if (family.equals("F")) {
            if (type.equals("LEAF")) {
                if (level == 2) {
                    return "KIRLICAKE";
                } else {
                    return "VELVEVOIR";
                }
            }
        } else if (family.equals("G")) {
            if (type.equals("WATER")) {
                if (level == 2) {
                    return "TARTORTLE";
                } else {
                    return "PIESTOISE";
                }
            }
        } else if (family.equals("H")) {
            if (type.equals("WATER")) {
                if (level == 2) {
                    return "CHOCOLISH";
                } else {
                    return "ICESUNDAE";
                }
            }
        } else if (family.equals("I")) {
            if (type.equals("WATER")) {
                if (level == 2) {
                    return "DEWICE";
                } else {
                    return "SAMURCONE";
                }
            }
        }

        return null;
    }
}

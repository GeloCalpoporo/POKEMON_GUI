package ABC;

import java.util.ArrayList;
import java.util.List;

class GameModel {
    private Inventory inventory;
    private CreatureList creatureList; // Use CreatureList directly
    private Evolution evolution;

    public GameModel() {
        this.inventory = new Inventory(); // Initialize Inventory
        this.creatureList = new CreatureList();
        this.evolution = new Evolution(inventory);
        // You can add other game data and logic initialization here
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Creature> getEncounteredCreatures() {
        return creatureList.getAllCreatures(); // Use getAllCreatures from CreatureList
    }

    public Evolution getEvolution() {
        return evolution;
    }

    public void exploreArea() {
        // Implement logic for exploring the chosen area
        System.out.println("Exploring Area");
    }

    public void evolveCreatures(int indexCreature1, int indexCreature2) {
        if (indexCreature1 >= 0 && indexCreature1 < inventory.getCreatures().size() &&
                indexCreature2 >= 0 && indexCreature2 < inventory.getCreatures().size()) {
            Creature creature1 = inventory.getCreatures().get(indexCreature1);
            Creature creature2 = inventory.getCreatures().get(indexCreature2);

            EvolutionResult evolutionResult = evolution.evolveCreatures(creature1, creature2);
            if (evolutionResult.isSuccess()) {
                System.out.println("Evolution successful! Evolved creature: " + evolutionResult.getEvolvedCreature().getName());
            } else {
                System.out.println("Evolution failed. Check if the selected creatures are eligible.");
            }
        } else {
            System.out.println("Invalid creature indices. Please try again.");
        }
    }

    public List<Creature> getCreaturesEL1() {
        List<Creature> creaturesEL1 = new ArrayList<>();
        Creature el1Creature = creatureList.getRandomEL1Creature();
        if (el1Creature != null) {
            creaturesEL1.add(el1Creature);
        }
        return creaturesEL1;
    }

    public void exploreArea1() {
        // Implement logic for exploring Area 1
        // You can add logic to encounter creatures and perform other actions
        List<Creature> encounteredCreatures = getCreaturesEL1(); // Use the existing method
        Area1Screen area1Screen = new Area1Screen(encounteredCreatures);
        area1Screen.setVisible(true);
    }

    public void exploreArea2() {
        // Implement logic for exploring Area 2
        // You can add logic to encounter creatures and perform other actions
        Area2Screen area2Screen = new Area2Screen();
        area2Screen.setVisible(true);
    }

    public void exploreArea3() {
        // Implement logic for exploring Area 3
        // You can add logic to encounter creatures and perform other actions
        Area3Screen area3Screen = new Area3Screen();
        area3Screen.setVisible(true);
    }

    // Add other methods and data relevant to the game logic
}

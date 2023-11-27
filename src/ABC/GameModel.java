class GameModel {
    private Inventory inventory;
    private List<Creature> encounteredCreatures;

    public GameModel() {
        this.inventory = new Inventory();
        this.encounteredCreatures = new ArrayList<>();
        // Initialize other game data and logic
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Creature> getEncounteredCreatures() {
        return encounteredCreatures;
    }

    // Add other methods and data relevant to the game logic
}
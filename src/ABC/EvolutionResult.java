package ABC;

/**
 * The EvolutionResult class represents the result of an evolution attempt.
 */
public class EvolutionResult {

    private boolean success;
    private Creature evolvedCreature;

    /**
     * Constructs an EvolutionResult with the specified success status and evolved creature.
     *
     * @param success         true if the evolution was successful, false otherwise.
     * @param evolvedCreature The evolved creature, or null if the evolution failed.
     */
    public EvolutionResult(boolean success, Creature evolvedCreature) {
        this.success = success;
        this.evolvedCreature = evolvedCreature;
    }

    /**
     * Checks if the evolution was successful.
     *
     * @return true if the evolution was successful, false otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the evolved creature.
     *
     * @return The evolved creature, or null if the evolution failed.
     */
    public Creature getEvolvedCreature() {
        return evolvedCreature;
    }
}

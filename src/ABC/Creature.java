package ABC;
/**
 * The Creature class represents a creature with various attributes and
 * characteristics.
 * It provides methods to access and manipulate these attributes.
 */
public class Creature {
    private int userChoice;
    private String name;
    private String type;
    private String family;
    private int evolutionLevel;
    private int[] position; // Added position attribute

    /**
     * Creates a new Creature with the specified attributes.
     *
     * @param userChoice     The user's choice for this creature.
     * @param name           The name of the creature.
     * @param type           The type of the creature.
     * @param family         The family of the creature.
     * @param evolutionLevel The evolution level of the creature.
     * @param position       The position of the creature.
     */
    public Creature(int userChoice, String name, String type, String family, int evolutionLevel, int[] position) {
        this.userChoice = userChoice;
        this.name = name;
        this.type = type;
        this.family = family;
        this.evolutionLevel = evolutionLevel;
        this.position = position;
    }

    // Constructors for cases where position is not specified
    public Creature(int userChoice, String name, String type, String family, int evolutionLevel) {
        this(userChoice, name, type, family, evolutionLevel, new int[]{0, 0});
    }

    public Creature(String name, String type, String family, int evolutionLevel) {
        this.name = name;
        this.type = type;
        this.family = family;
        this.evolutionLevel = evolutionLevel;
    }

    /**
     * Get the user's choice for this creature.
     *
     * @return The user's choice for this creature.
     */
    public int getUserChoice() {
        return userChoice;
    }


    /**
     * Get the name of the creature.
     *
     * @return The name of the creature.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the type of the creature.
     *
     * @return The type of the creature.
     */
    public String getType() {
        return type;
    }


    /**
     * Get the family of the creature.
     *
     * @return The family of the creature.
     */
    public String getFamily() {
        return family;
    }

    /**
     * Get the evolution level of the creature.
     *
     * @return The evolution level of the creature.
     */
    public int getEvolutionLevel() {
        return evolutionLevel;
    }

    public void setEvolutionLevel(int evolutionLevel) {
        this.evolutionLevel = evolutionLevel;
    }

    /**
     * Get the position of the creature.
     *
     * @return The position of the creature.
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * Get the image path of the creature.
     *
     * @return The image path of the creature.
     */
    public String getImagePath() {
        // Customize this method based on your image naming convention and folder structure
        return "/" + name + ".jpg";
    }

    /**
     * Returns a string representation of the creature.
     *
     * @return A string containing the name, type, family, and evolution level of
     * the creature.
     */
    public String toString() {
        return "Name: " + name + ", Type: " + type + ", Family: " + family +
                ", Evolution Level: " + evolutionLevel + ", Position: (" + position[0] + ", " + position[1] + ")";
    }
}

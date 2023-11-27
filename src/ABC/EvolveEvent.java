package ABC;

import java.util.EventObject;

public class EvolveEvent extends EventObject {
    private Creature creature1;
    private Creature creature2;

    public EvolveEvent(Object source, Creature creature1, Creature creature2) {
        super(source);
        this.creature1 = creature1;
        this.creature2 = creature2;
    }

    public Creature getCreature1() {
        return creature1;
    }

    public Creature getCreature2() {
        return creature2;
    }
}

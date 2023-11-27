package ABC;

import java.util.List;
import java.util.Random;

public class Area1 extends Area {
    private static final int AREA_WIDTH = 5;
    private static final int AREA_HEIGHT = 1;
    private List<Creature> creaturesEL1;

    public Area1(Inventory inventory, Creature activeCreature, List<Creature> creaturesEL1) {
        super(inventory, activeCreature);
        this.areaGrid = new int[AREA_HEIGHT][AREA_WIDTH];
        this.userPosition = new int[]{0, 0};
        this.creaturesEL1 = creaturesEL1;
    }

    @Override
    public void displayArea() {
        System.out.println("Area 1:");

        for (int row = 0; row < AREA_HEIGHT; row++) {
            for (int col = 0; col < AREA_WIDTH; col++) {
                if (userPosition[0] == col && userPosition[1] == row) {
                    System.out.print("X ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }

    public void exploreArea() {
        // Implementation for exploring the area
    }

    @Override
    public Creature getRandomCreature() {
        Random random = new Random();
        int randomIndex = random.nextInt(creaturesEL1.size());
        return creaturesEL1.get(randomIndex);
    }
}

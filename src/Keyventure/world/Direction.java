package Keyventure.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    private static final List<Direction> VALUES = new ArrayList<>(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Gibt zufällige Richtung zurück
     * @return zufällige Richtung
     */
    public static Direction getRandomDirection() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

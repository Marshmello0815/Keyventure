package Keyventure.world;

import org.junit.Assert;
import org.junit.Test;

public class GameWorldCreatorTest {

    @Test
    public void testSetEnv() {
        GameWorld world = new GameWorld();
        GameWorldCreator creator = new GameWorldCreator(world);
        creator.setEnv(100,200);
        Assert.assertArrayEquals(new int[]{creator.getGameWidth(), creator.getGameHeight()}, new int[]{100, 200});
    }
}

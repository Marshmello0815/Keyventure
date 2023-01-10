package Keyventure.world.obj;

import Keyventure.world.GameWorld;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeyTest {

    @Test
    public void testKollisionWithPlayer() {
        GameWorld world = new GameWorld();
        world.player = new Player(world, 10, 13);
        Key key = new Key(world, 10, 10);
        world.addPassiveObject(key);
        world.playerUp();
        key.collisionWithPlayer();
        assertEquals(world.getPassiveObject().size(), 0);
    }
}
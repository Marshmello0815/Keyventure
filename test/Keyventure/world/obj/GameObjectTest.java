package Keyventure.world.obj;

import Keyventure.world.GameWorld;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameObjectTest {

    @Test
    public void testCheckKollision() {
        GameWorld world = new GameWorld();
        Player player = new Player(world, 10, 10);
        Door door = new Door(world, 10, 10, 20);
        assertTrue(player.checkKollision(door));
    }
}
package Keyventure.world.obj;

import Keyventure.world.GameWorld;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallTest {

    @Test
    public void testKollisionWithPlayer() {
        GameWorld world = new GameWorld();
        Player player = new Player(world, 10, 13);
        world.player = player;
        Wall wall = new Wall(world , 10, 10, 20);
        world.addPassiveObject(wall);
        world.playerUp();
        wall.collisionWithPlayer();
        assertEquals(10 + Player.NORMAL_STEP_SIZE, player.getY());
    }
}
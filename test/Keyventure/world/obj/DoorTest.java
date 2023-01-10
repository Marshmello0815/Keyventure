package Keyventure.world.obj;


import Keyventure.world.GameWorld;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoorTest {

    @Test
    public void testKollisionWithPlayer() {
        GameWorld world = new GameWorld();
        Player player = new Player(world, 10, 13);
        world.player = player;
        Door door = new Door(world, 10, 10, 30);
        world.addPassiveObject(door);
        world.playerUp();
        door.collisionWithPlayer();
        assertEquals(10 + Player.STEP_SIZE, player.getY());
    }

}
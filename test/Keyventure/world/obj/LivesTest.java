package Keyventure.world.obj;

import Keyventure.world.GameWorld;
import org.junit.Test;

import static org.junit.Assert.*;

public class LivesTest {

    @Test
    public void testGetLives() {
        GameWorld world = new GameWorld();
        Lives lives = new Lives(world, 10, 10);
        assertEquals(3, lives.getLives());
    }

    @Test
    public void testSetLives() {
        GameWorld world = new GameWorld();
        Lives lives = new Lives(world, 10, 10);
        lives.setLives(6);
        assertEquals(6, lives.getLives());
    }

    @Test
    public void testGetLostLives() {
        GameWorld world = new GameWorld();
        Lives lives = new Lives(world, 10, 10);
        assertEquals(0, lives.getLostLives());
    }

    @Test
    public void testSetLostLives() {
        GameWorld world = new GameWorld();
        Lives lives = new Lives(world, 10, 10);
        lives.setLostLives(3);
        assertEquals(3, lives.getLostLives());
    }
}
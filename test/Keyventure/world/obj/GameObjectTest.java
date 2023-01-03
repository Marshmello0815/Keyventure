package Keyventure.world.obj;

import Keyventure.world.GameWorld;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameObjectTest {

    @Test
    public void testCheckKollision() {
        GameWorld world = new GameWorld();
        Player player = new Player(world, 10,10);
        Door door = new Door(world, 10, 10, 20);
        assertTrue(player.checkKollision(door));
    }

    @Test
    public void testGetX() {
        GameWorld world = new GameWorld();
        Door door = new Door(world, 10, 10, 20);
        assertEquals(10, door.getX());
    }

    @Test
    public void testGetY() {
        GameWorld world = new GameWorld();
        Door door = new Door(world, 10, 10, 20);
        assertEquals(10, door.getY());
    }

    @Test
    public void testSetX() {
        GameWorld world = new GameWorld();
        Door door = new Door(world, 10, 10, 20);
        door.setX(20);
        assertEquals(20, door.getX());
    }

    @Test
    public void testSetY() {
        GameWorld world = new GameWorld();
        Door door = new Door(world, 10, 10, 20);
        door.setY(20);
        assertEquals(20, door.getY());
    }

    @Test
    public void testGetWidth() {
        GameWorld world = new GameWorld();
        Door door = new Door(world, 10, 10, 20);
        assertEquals(20, door.getWidth());
    }

    @Test
    public void testGetHeight() {
        GameWorld world = new GameWorld();
        Door door = new Door(world, 10, 10, 20);
        assertEquals(10, door.getHeight());
    }
}
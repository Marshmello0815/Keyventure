package Keyventure.world.obj;

import Keyventure.world.GameWorld;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testUp() {
        GameWorld world = new GameWorld();
        Player player = new Player(world, 10, 10);
        player.up();
        assertEquals(10 - Player.NORMAL_STEP_SIZE,player.getY());
    }

    @Test
    public void testDown() {
        GameWorld world = new GameWorld();
        Player player = new Player(world, 10, 10);
        player.down();
        assertEquals(10 + Player.NORMAL_STEP_SIZE,player.getY());
    }

    @Test
    public void testRight() {
        GameWorld world = new GameWorld();
        Player player = new Player(world, 10, 10);
        player.right();
        assertEquals(10 + Player.NORMAL_STEP_SIZE,player.getX());
    }

    @Test
    public void testLeft() {
        GameWorld world = new GameWorld();
        Player player = new Player(world, 10, 10);
        player.left();
        assertEquals(10 - Player.NORMAL_STEP_SIZE,player.getX());
    }

    @Test
    public void testMakeInvisible() {
        GameWorld world = new GameWorld();
        Player player = new Player(world, 10, 10);
        player.makeInvisible();
        assertEquals(Player.CYCLES_INVISIBILITY,player.getInvisible());
    }

}
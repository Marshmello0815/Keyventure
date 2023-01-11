package Keyventure.world;

import Keyventure.world.obj.*;
import org.junit.Test;
import processing.core.PApplet;

import java.util.List;

import static org.junit.Assert.*;

public class GameWorldTest extends PApplet {

    @Test
    public void testAddPassiveObject() {
        GameWorld world = new GameWorld();
        Key key = new Key(world,10,10);
        world.addPassiveObject(key);
        assertEquals(1, world.passiveObject.size());
    }

    @Test
    public void testAddActiveObject() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world, 10,10);
        world.addActiveObject(monster);
        assertEquals(1, world.activeObject.size());
    }

    @Test
    public void testAllObjects() {
        GameWorld world = new GameWorld();
        Key key = new Key(world,10,10);
        Monster monster = new Monster(world, 10,10);
        world.player = new Player(world, 10, 10);
        world.lives = new Lives(world, 10, 10);
        world.addPassiveObject(key);
        world.addActiveObject(monster);
        List<GameObject> allObjects = world.allObjects();
        assertEquals(4, allObjects.size());
    }

    @Test
    public void testMakePlayerInvisible() {
        GameWorld world = new GameWorld();
        world.player = new Player(world, 10, 10);
        world.makePlayerInvisible();
        assertEquals(Player.CYCLES_INVISIBILITY, world.player.getInvisible());
    }

    @Test
    public void testMove() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world, 10,10);
        world.addActiveObject(monster);
        monster.setDirection(Direction.RIGHT);
        world.move(this);
        assertEquals(10 + Monster.STEP_SIZE, monster.getX());
    }

    @Test
    public void testPickKey() {
        GameWorld world = new GameWorld();
        Key key = new Key(world,10,10);
        world.addPassiveObject(key);
        world.pickKey(key);
        assertTrue(world.hasKey);
    }

    @Test
    public void testPlayerTouchWall() {
        GameWorld world = new GameWorld();
        Wall wall = new Wall(world, 10, 10, 40);
        world.addPassiveObject(wall);
        world.player = new Player(world, 10, 10);
        world.up = true;
        world.playerTouchWall(wall);
        assertEquals(10 + Player.NORMAL_STEP_SIZE, world.player.getY());
    }

    @Test
    public void testEnterDoorWithKey() {
        GameWorld world = new GameWorld();
        world.player = new Player(world, 10, 10);
        Door door = new Door(world, 10, 10, 30);
        world.addPassiveObject(door);
        world.hasKey = true;
        world.enterDoor(door);
        assertTrue(world.gameWon);
    }

    @Test
    public void testEnterDoorWithoutKey() {
        GameWorld world = new GameWorld();
        world.player = new Player(world, 10, 10);
        Door door = new Door(world, 10, 10, 30);
        world.addPassiveObject(door);
        world.hasKey = false;
        world.up = true;
        world.enterDoor(door);
        assertEquals(10 + Player.NORMAL_STEP_SIZE, world.player.getY());
    }

    @Test
    public void testMonsterTouchWall() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world, 10,10);
        monster.setDirection(Direction.LEFT);
        world.monsterTouchNotPlayerObject(monster);
        assertEquals(10 + Monster.STEP_SIZE,monster.getX());
    }

    @Test
    public void testPlayerUp() {
        GameWorld world = new GameWorld();
        world.player = new Player(world, 10, 10);
        world.playerUp();
        assertEquals(10 - Player.NORMAL_STEP_SIZE, world.player.getY());
    }

    @Test
    public void testPlayerDown() {
        GameWorld world = new GameWorld();
        world.player = new Player(world, 10, 10);
        world.playerDown();
        assertEquals(10 + Player.NORMAL_STEP_SIZE, world.player.getY());
    }

    @Test
    public void testPlayerLeft() {
        GameWorld world = new GameWorld();
        world.player = new Player(world, 10, 10);
        world.playerLeft();
        assertEquals(10 - Player.NORMAL_STEP_SIZE, world.player.getX());
    }

    @Test
    public void testPlayerRight() {
        GameWorld world = new GameWorld();
        world.player = new Player(world, 10, 10);
        world.playerRight();
        assertEquals(10 + Player.NORMAL_STEP_SIZE, world.player.getX());
    }

}
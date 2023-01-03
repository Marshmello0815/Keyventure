package Keyventure.world.obj;

import Keyventure.world.GameWorld;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {

    @Test
    public void testSetDirection() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world,10,10);
        monster.setDirection(2);
        assertEquals(2, monster.getDirection());
    }

    @Test
    public void testGetDirection() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world,10,10);
        monster.setDirection(2);
        assertEquals(2, monster.getDirection());
    }

    @Test
    public void testKollisionWithWall() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world, 10,10);
        monster.setDirection(1);
        monster.kollisionWithWall();
        assertEquals(10 + Monster.STEP_SIZE,monster.getX());
    }

    @Test
    public void testChangeDirection() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world, 10,10);
        int direction = monster.getDirection();
        monster.changeDirection();
        int newDirection = monster.getDirection();
        if(direction == newDirection){
            assertEquals(direction, newDirection);
        }
        else {
            assertNotEquals(direction, newDirection);
        }
    }

    @Test
    public void testMove() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world, 10,10);
        monster.setDirection(1);
        monster.move();
        assertEquals(10 - Monster.STEP_SIZE,monster.getX());
    }
}
package Keyventure.world.obj;

import Keyventure.world.Direction;
import Keyventure.world.GameWorld;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {

    @Test
    public void testSetDirection() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world,10,10);
        monster.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, monster.getDirection());
    }

    @Test
    public void testGetDirection() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world,10,10);
        monster.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, monster.getDirection());
    }

    @Test
    public void testKollisionWithNotPlayerObject() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world, 10,10);
        monster.setDirection(Direction.RIGHT);
        monster.kollisionWithNotPlayerObject();
        assertEquals(10 - Monster.STEP_SIZE,monster.getX());
    }

    @Test
    public void testChangeDirection() {
        GameWorld world = new GameWorld();
        Monster monster = new Monster(world, 10,10);
        Direction direction = monster.getDirection();
        monster.changeDirection();
        Direction newDirection = monster.getDirection();
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
        monster.setDirection(Direction.LEFT);
        monster.move();
        assertEquals(10 - Monster.STEP_SIZE,monster.getX());
    }
}
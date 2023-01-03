package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

import java.util.Random;

public class Monster extends ActiveObject {

    private int direction;
    private final Random random;
    public static final int STEP_SIZE = 2;

    public Monster(IGameWorld world, int x, int y) {
        super(world, x, y, 30, 50);
        random = new Random();
        direction = random.nextInt(4);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void kollisionWithWall() {
        world.monsterTouchWall(this);
    }

    public void changeDirection(){
        int zufall = random.nextInt(2);
        if (zufall == 1) {
            int direction = random.nextInt(4);
            this.setDirection(direction);
        }
    }

    @Override
    public void move(){
        if(direction == 0){
            x += STEP_SIZE; //right
        }
        if(direction == 1){
            x -= STEP_SIZE; //left
        }
        if(direction == 2){
            y += STEP_SIZE; //down
        }
        if(direction == 3){
            y -= STEP_SIZE; //up
        }
    }

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.fill(20,200,20);
        app.rect(x, y, width, height);
        app.popStyle();
    }
}
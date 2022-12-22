package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

import java.util.Random;

public class Monster extends ActiveObject {

    private int direction;
    private final Random random;

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
            x += 2; //right
        }
        if(direction == 1){
            x -= 2; //left
        }
        if(direction == 2){
            y += 2; //down
        }
        if(direction == 3){
            y -= 2; //up
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

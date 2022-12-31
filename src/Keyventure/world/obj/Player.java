package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

public class Player extends GameObject {

    public static final int CYCLES_INVISIBILITY = 90;
    public static final int STEP_SIZE = 3;
    private int invisible = 0;

    public Player(IGameWorld world, int x, int y) {
        super(world, x, y, 20, 35);

    }

    public void up(){
         y -= STEP_SIZE;
    }
    public void down(){
         y += STEP_SIZE;
    }
    public void right(){
         x += STEP_SIZE;
    }
    public void left(){
         x -= STEP_SIZE;
    }

    public int getInvisible() {
        return invisible;
    }

    public void makeInvisible() {
        this.invisible = Math.max(this.invisible, CYCLES_INVISIBILITY);
    }

    @Override
    public void draw(PApplet app) {

        this.invisible-= 1;
        if (this.invisible % 2 == 1) {
            return;
        }

        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }

}
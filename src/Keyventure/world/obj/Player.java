package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

public class Player extends GameObject {

    public static final int CYCLES_INVISIBILITY = 90;
    private int invisible = 0;

    public Player(IGameWorld world, int x, int y) {
        super(world, x, y, 20, 35);

    }

    public void up(){
         y -= 3;
    }

    public void down(){
         y += 3;
    }

    public void right(){
         x += 3;
    }
    public void left(){
         x -= 3;
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
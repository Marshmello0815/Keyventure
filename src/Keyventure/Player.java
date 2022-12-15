package Keyventure;

import processing.core.PApplet;

public class Player extends GameObject {

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

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }

}
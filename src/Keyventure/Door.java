package Keyventure;

import processing.core.PApplet;

public class Door extends PassiveObject{

    public Door(IGameWorld world, int x, int y) {
        super(world, x, y, 20, 20);
    }

    public void kollisionWithPlayer(){

    }

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }
}

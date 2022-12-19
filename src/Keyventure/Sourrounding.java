package Keyventure;

import processing.core.PApplet;

public class Sourrounding extends PassiveObject{

    public Sourrounding(IGameWorld world) {
        super(world, 400, 400, 50, 50);
    }

    public void kollisionWithPlayer(){
        world.touchSourrounding(this);
    }

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }
}


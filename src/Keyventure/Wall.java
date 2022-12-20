package Keyventure;

import processing.core.PApplet;

public class Wall extends PassiveObject{


    public Wall(IGameWorld world, int x, int y) {
        super(world, x, y, 50, 50);
    }

    public void kollisionWithPlayer(){
        world.touchWall(this);
    }

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }
}

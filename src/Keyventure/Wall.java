package Keyventure;

import processing.core.PApplet;

public class Wall extends PassiveObject{

    public Wall(IGameWorld world, int x, int y, int width, int height) {
        super(world, x, y, width, height);
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

package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

public class Key extends PassiveObject {

    public Key(IGameWorld world, int x, int y) {
        super(world, x, y, 30, 30);
    }

    @Override
    public void kollisionWithPlayer(){
        world.pickKey(this);
    }

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }
}

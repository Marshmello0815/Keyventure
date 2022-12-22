package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

public class Door extends PassiveObject {

    public Door(IGameWorld world, int x, int y, int size) {
        super(world, x, y, size, size/2);
    }

    @Override
    public void kollisionWithPlayer(){
        world.enterDoor(this);
    }

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }
}

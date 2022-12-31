package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

public class Wall extends PassiveObject{


    public Wall(IGameWorld world, int x, int y , int size) {
        super(world, x, y, size , size);
    }

    public void kollisionWithPlayer(){
        world.playerTouchWall(this);
    }

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }
}

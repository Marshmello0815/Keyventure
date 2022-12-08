package Keyventure;

import processing.core.PApplet;

public class Door extends PassiveObject{

    public Door(int x, int y) {
        super(x, y, 20, 20);
    }

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }
}

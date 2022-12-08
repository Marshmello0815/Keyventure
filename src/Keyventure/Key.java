package Keyventure;

import processing.core.PApplet;

public class Key extends PassiveObject{

    public Key(int x, int y) {
        super(x, y, 30, 30);
    }

    @Override
    public void draw(PApplet app) {
        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }
}

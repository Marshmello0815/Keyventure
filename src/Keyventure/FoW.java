package Keyventure;

import processing.core.PApplet;

public class FoW {
    private int radius = 42;
    Player player;

    public FoW(Player player) {
        this.player = player;
    }

    public void draw(PApplet app) {
        app.pushStyle();
        app.noFill();
        app.ellipse((float) (player.getX() + player.getWidth() / 2.0), (float) (player.getY() + player.getHeight() / 2.0), 60, 60);
        app.popStyle();
    }
}

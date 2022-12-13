package Keyventure;

import processing.core.PApplet;

public class FoW {
    private int radius = 100;
    Player player;

    public FoW(Player player) {
        this.player = player;
    }

    public void draw(PApplet app) {
        app.pushStyle();
        app.beginShape();
        app.fill(0, 0, 0);
        app.vertex(0, 0);
        app.vertex(0, app.height);
        app.vertex(app.width, app.height);
        app.vertex(app.width, 0);
        app.beginContour();
        app.vertex((float) (player.getX() + player.getWidth() / 2 - radius), (float) (player.getY() + player.getHeight() / 2 - radius));
        app.vertex((float) (player.getX() + player.getWidth() / 2 + radius), (float) (player.getY() + player.getHeight() / 2 - radius));
        app.vertex((float) (player.getX() + player.getWidth() / 2 + radius), (float) (player.getY() + player.getHeight() / 2 + radius));
        app.vertex((float) (player.getX() + player.getWidth() / 2 - radius), (float) (player.getY() + player.getHeight() / 2 + radius));
        app.endContour();
        app.endShape();
        app.popStyle();
    }
}

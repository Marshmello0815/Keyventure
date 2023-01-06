package Keyventure.world.obj;

import Keyventure.world.GameWorld;
import processing.core.PApplet;

public class FoW {
    private int radius = 100;
    Player player;
    GameWorld world;

    public FoW(GameWorld world, Player player) {
        this.player = player;
        this.world = world;
    }

    /**
     * Zeichnen des Fog of War mit einer Kontur
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    public void draw(PApplet app) {
            app.pushStyle();
        if (world.isFowOn()) {
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
        }
            app.popStyle();
    }
}

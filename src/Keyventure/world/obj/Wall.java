package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

public class Wall extends PassiveObject {


    public Wall(IGameWorld world, int x, int y, int size) {
        super(world, x, y, size, size);
    }

    /**
     * Ruft die Methode "playerTouchWall()" in der Klasse "GameWorld" auf, bei Kollision des Spielers mit einer Wand
     */
    public void kollisionWithPlayer() {
        world.playerTouchWall(this);
    }

    /**
     * Symbolisches zeichnen des Spielers als Rechteck
     *
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    @Override
    public void draw(PApplet app) {
        if (world.isDevMode()) {
            app.pushStyle();
            super.draw(app);
            app.popStyle();
        }
    }
}

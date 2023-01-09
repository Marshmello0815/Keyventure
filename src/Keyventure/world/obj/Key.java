package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

public class Key extends PassiveObject {

    PImage KEY = null;

    public Key(IGameWorld world, int x, int y) {
        super(world, x, y, 30, 30);
    }

    /**
     * Ruft die Methode "pickKey()" in der Klasse "GameWorld" auf, bei Kollision des Spielers mit einem Schlüssel
     */
    @Override
    public void kollisionWithPlayer() {
        world.pickKey(this);
    }

    /**
     * Zeichnet den Schlüssel. Zusätzlich im Developer-Modus: Symbolisches zeichnen des Schlüssels als Rechteck (Hitbox).
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
        if (KEY == null) {
            KEY = app.loadImage("/resource/key/key.png");
        }
        app.image(KEY, x , y, width, height);
    }
}

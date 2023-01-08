package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

public class Key extends PassiveObject {

    PImage KEY;

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
     * Symbolisches zeichnen des Schlüssels als Rechteck
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
        KEY = app.loadImage("/resource/key.png");
        app.image(KEY, x , y, width, height);
    }
}

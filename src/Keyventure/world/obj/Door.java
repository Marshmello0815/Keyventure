package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

public class Door extends PassiveObject {

    PImage DOOR = null;

    public Door(IGameWorld world, int x, int y, int size) {
        super(world, x, y, size, (size / 3)*2);
    }

    /**
     * Ruft die Methode "enterDoor()" in der Klasse "GameWorld" auf, bei Kollision des Spielers mit einer Tür
     */
    @Override
    public void collisionWithPlayer() {
        world.enterDoor(this);
    }

    /**
     * Zeichnet die Tür. Zusätzlich im Developer-Modus: Symbolisches zeichnen der Tür als Rechteck (Hitbox).
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
        if (DOOR == null) {
            DOOR = app.loadImage("/resource/door/Door-locked.png");
        }
        app.image(DOOR, x , y, width, height);
    }
}

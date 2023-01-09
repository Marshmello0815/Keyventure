package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

public class Lives extends GameObject {

    PImage healthFull = null;
    PImage healthEmpty = null;

    int lives;
    int lostLives;

    public Lives(IGameWorld world, int x, int y) {
        super(world, x, y, 30, 30);
        lives = 3;
        lostLives = 0;
    }

    /**
     * Gibt Anzahl der Leben zurück
     *
     * @return Anzahl der Leben
     */
    public int getLives() {
        return lives;
    }

    /**
     * Setzt Anzahl der Leben auf den übergebenen Wert
     *
     * @param lives Der Wert auf den die Anzahl der Leben gesetzt werden soll
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Gibt Anzahl der verlorenen Leben zurück
     *
     * @return Anzahl der verlorenen Leben
     */
    public int getLostLives() {
        return lostLives;
    }

    /**
     * Setzt Anzahl der verlorenen Leben auf den übergebenen Wert
     *
     * @param lostLives Der Wert auf den die Anzahl der verlorenen Leben gesetzt werden soll
     */
    public void setLostLives(int lostLives) {
        this.lostLives = lostLives;
    }

    /**
     * Zeichen der Leben (volles Herz / leeres Herz)
     *
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    @Override
    public void draw(PApplet app) {
        if (healthFull == null) {
            healthFull = app.loadImage("/resource/health/health-full.png");
        }
        if (healthEmpty == null) {
            healthEmpty = app.loadImage("/resource/health/health-empty.png");
        }
        for (int i = 0; i < lives; i++) {
            app.image(healthFull, x + i * width, y, width, height);
        }
        for (int i = lives; i < lives + lostLives; i++) {
            app.image(healthEmpty, x + i * width, y, width, height);
        }
    }
}

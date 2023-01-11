package Keyventure.world.obj;

import Keyventure.world.Direction;
import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

public class Player extends GameObject {

    public static final int CYCLES_INVISIBILITY = 45;
    public static final int NORMAL_STEP_SIZE = 4;
    public static final int RUN_STEP_SIZE = 6;
    private int stepSize = NORMAL_STEP_SIZE;
    private int invisible = 0;
    private Direction direction = Direction.UP;

    PImage Back3 = null;
    PImage Front3 = null;
    PImage Right3 = null;
    PImage Left3 = null;

    public Player(IGameWorld world, int x, int y) {
        super(world, x, y, 25, 40);

    }

    /**
     * Gibt die aktuelle Bewegungsrichtung des Spielers als Zahl zurück (0: nach rechts, 1: nach links, 2: nach unten, 3: nach oben)
     *
     * @return Aktuelle Bewegungsrichtung des Spielers
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Setzt die Schrittgröße des Spielers auf den übergebenen Wert
     * @param stepSize Der Wert, auf den die Schrittgröße des Spielers gesetzt werden soll
     */
    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach oben durch
     */
    public void up() {
        y -= stepSize;
        direction = Direction.UP;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach unten durch
     */
    public void down() {
        y += stepSize;
        direction = Direction.DOWN;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach rechts durch
     */
    public void right() {
        x += stepSize;
        direction = Direction.RIGHT;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach links durch
     */
    public void left() {
        x -= stepSize;
        direction = Direction.LEFT;
    }

    /**
     * Gibt eine Zahl zwischen 0 und CYCLES_INVISIBILITY zurück, welche beschreibt ob der Spieler unsichtbar sein soll
     *
     * @return Zahl zwischen 0 und CYCLES_INVISIBILITY
     */
    public int getInvisible() {
        return invisible;
    }

    /**
     * Setzt das Attribut "invisible" auf den höheren Wert zwischen "invisible" und "CYCLES_INVISIBILITY"
     */
    public void makeInvisible() {
        this.invisible = Math.max(this.invisible, CYCLES_INVISIBILITY);
    }

    /**
     * Zeichnet den Spieler. Zusätzlich im Developer-Modus: Symbolisches zeichnen des Spielers als Rechteck (Hitbox). Macht Spieler ggf. unsichtbar
     *
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    @Override
    public void draw(PApplet app) {

        this.invisible -= 1;
        if (this.invisible % 2 == 1) {
            return;
        }

        if (world.isDevMode()) {
            app.pushStyle();
            super.draw(app);
            app.popStyle();
        }

        if (getDirection() == Direction.RIGHT) {
            if (Right3 == null) {
                Right3 = app.loadImage("/resource/player/PlayerRight_3.png");
            }
            app.image(Right3, x, y, width, height);
        }
        if (getDirection() == Direction.LEFT) {
            if (Left3 == null) {
                Left3 = app.loadImage("/resource/player/PlayerLeft_3.png");
            }
            app.image(Left3, x, y, width, height);
        }
        if (getDirection() == Direction.DOWN) {
            if (Front3 == null) {
                Front3 = app.loadImage("/resource/player/PlayerFront_3.png");
            }
            app.image(Front3, x, y, width, height);
        }
        if (getDirection() == Direction.UP) {
            if (Back3 == null) {
                Back3 = app.loadImage("/resource/player/PlayerBack_3.png");
            }
            app.image(Back3, x, y, width, height);
        }

    }

}
package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

public class Player extends GameObject {

    public static final int CYCLES_INVISIBILITY = 90;
    public static final int STEP_SIZE = 3;
    private int invisible = 0;
    private int direction;

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
    public int getDirection() {
        return direction;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach oben durch
     */
    public void up() {
        y -= STEP_SIZE;
        direction = 3;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach unten durch
     */
    public void down() {
        y += STEP_SIZE;
        direction = 2;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach rechts durch
     */
    public void right() {
        x += STEP_SIZE;
        direction = 0;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach links durch
     */
    public void left() {
        x -= STEP_SIZE;
        direction = 1;
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
     * Symbolisches zeichnen des Spielers als Rechteck. Macht Spieler ggf. unsichtbar
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

        if(getDirection()==0){
            if(Right3==null){
                Right3 = app.loadImage("/resource/PlayerRight_3.png");
            }
            app.image(Right3, x, y, width, height);
        }
        if(getDirection()==1){
            if(Left3==null){
                Left3 = app.loadImage("/resource/PlayerLeft_3.png");
            }
            app.image(Left3, x, y, width, height);
        }
        if(getDirection()==2){
            if(Front3==null){
                Front3 = app.loadImage("/resource/PlayerFront_3.png");
            }
            app.image(Front3, x, y, width, height);
        }
        if(getDirection()==3){
            if(Back3==null){
                Back3 = app.loadImage("/resource/PlayerBack_3.png");
            }
            app.image(Back3, x, y, width, height);
        }

    }

}
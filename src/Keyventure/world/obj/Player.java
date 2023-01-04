package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

public class Player extends GameObject {

    public static final int CYCLES_INVISIBILITY = 90;
    public static final int STEP_SIZE = 3;
    private int invisible = 0;

    public Player(IGameWorld world, int x, int y) {
        super(world, x, y, 20, 35);

    }

    /**
     * Führt die reguläre Bewegung des Spielers nach oben durch
     */
    public void up(){
         y -= STEP_SIZE;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach unten durch
     */
    public void down(){
         y += STEP_SIZE;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach rechts durch
     */
    public void right(){
         x += STEP_SIZE;
    }

    /**
     * Führt die reguläre Bewegung des Spielers nach links durch
     */
    public void left(){
         x -= STEP_SIZE;
    }

    /**
     * Gibt eine Zahl zwischen 0 und CYCLES_INVISIBILITY zurück, welche beschreibt ob der Spieler unsichtbar sein soll
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
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    @Override
    public void draw(PApplet app) {

        this.invisible-= 1;
        if (this.invisible % 2 == 1) {
            return;
        }

        app.pushStyle();
        app.rect(x, y, width, height);
        app.popStyle();
    }

}
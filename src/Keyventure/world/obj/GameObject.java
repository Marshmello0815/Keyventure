package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;

public abstract class GameObject {

    IGameWorld world;
    int x;
    int y;
    int width;
    int height;

    public GameObject(IGameWorld world, int x, int y, int width, int height) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Überprüft die mögliche Kollision zweier Objekte
     * @param that Das Objekt mit der die Kollision überprüft werden soll
     * @return Boolesche Werte welche die Kollision in X- und Y-Richtung beschreiben (hierbei steht "true" für Kollision)
     */
    public boolean checkKollision(GameObject that){
        boolean kollisionX = this.getX() + this.getWidth() >= that.getX() && that.getX() + that.getWidth() >= this.getX();
        boolean kollisionY = this.getY() + this.getHeight() >= that.getY() && that.getY() + that.getHeight() >= this.getY();
        return kollisionX && kollisionY;
    }

    /**
     * Gibt X-Koordinate des Objekts zurück
     * @return X-Koordinate des Objekts
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt Y-Koordinate des Objekts zurück
     * @return Y-Koordinate des Objekts
     */
    public int getY() {
        return y;
    }

    /**
     * Setzt X-Koordinate des Objekts auf den übergebenen Wert
     * @param x Der Wert auf den die X-Koordinate gesetzt werden soll
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setzt Y-Koordinate des Objekts auf den übergebenen Wert
     * @param y Der Wert auf den die Y-Koordinate gesetzt werden soll
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gibt Breite des Objekts zurück
     * @return Breite des Objekts
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gibt Höhe des Objekts zurück
     * @return Höhe des Objekts
     */
    public int getHeight() {
        return height;
    }

    /**
     * Symbolisches zeichnen des Objekts als Rechteck (Hitbox)
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    public void draw(PApplet app) {
        app.rect(x, y, width, height);
    }

}

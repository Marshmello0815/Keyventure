package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

public class Wall extends PassiveObject {

    PImage WALL = null;

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
     * Zeichnet die Wand. Zusätzlich im Developer-Modus: Symbolisches zeichnen der Wand als Rechteck (Hitbox).
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
        if(WALL == null){
            WALL = app.loadImage("/resource/wall/Brick.png");
        }
        app.image(WALL, x , y, width, height);
    }



}

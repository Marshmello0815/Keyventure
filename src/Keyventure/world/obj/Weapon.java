package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

public class Weapon extends PassiveObject{

    PImage WEAPON = null;

    public Weapon(IGameWorld world, int x, int y) {
        super(world, x, y, 40, 40);
    }
    /**
     * Ruft die Methode "pickWeapon()" in der Klasse "GameWorld" auf, bei Kollision des Spielers mit einer Waffe
     */
    @Override
    public void collisionWithPlayer(){
        world.pickWeapon(this);
    }

    @Override
    public void draw(PApplet app) {
        if (world.isDevMode()) {
            app.pushStyle();
            super.draw(app);
            app.popStyle();
        }
        if (WEAPON == null) {
            WEAPON = app.loadImage("/resource/sword.png");
        }
        app.image(WEAPON, x , y, width, height);
    }

}

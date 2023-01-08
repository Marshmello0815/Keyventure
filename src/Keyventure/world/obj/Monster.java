package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

public class Monster extends ActiveObject {

    private int direction;
    private final Random random;
    public static final int STEP_SIZE = 2;
    PImage Back1 = null;
    PImage Back2 = null;
    PImage Front1 = null;
    PImage Front2 = null;
    PImage Right1 = null;
    PImage Right2 = null;
    PImage Left1 = null;
    PImage Left2 = null;

    public Monster(IGameWorld world, int x, int y) {
        super(world, x, y, 30, 50);
        random = new Random();
        direction = random.nextInt(4);
    }

    /**
     * Setzt die Bewegungsrichtung des Monsters
     *
     * @param direction Die neue Bewegungsrichtung des Monsters
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Gibt die aktuelle Bewegungsrichtung des Monsters als Zahl zurück (0: nach rechts, 1: nach links, 2: nach unten, 3: nach oben)
     *
     * @return Aktuelle Bewegungsrichtung des Monsters
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Ruft die Methode "monsterTouchWall()" in der Klasse "GameWorld" auf, bei Kollision des Monsters mit einer Wand
     */
    @Override
    public void kollisionWithNotPlayerObject() {
        world.monsterTouchWall(this);
    }

    /**
     * Änderung der Bewegungsrichtung eines Monsters (Geschieht mit einer Wahrscheinlichkeit von ca. 50 %)
     */
    public void changeDirection() {
        int zufall = random.nextInt(2);
        if (zufall == 1) {
            int direction = random.nextInt(4);
            this.setDirection(direction);
        }
    }

    /**
     * Führt die reguläre Bewegung des Monsters durch (Richtungsabhängig).
     */
    @Override
    public void move() {
        if (direction == 0) {
            x += STEP_SIZE; //right
        }
        if (direction == 1) {
            x -= STEP_SIZE; //left
        }
        if (direction == 2) {
            y += STEP_SIZE; //down
        }
        if (direction == 3) {
            y -= STEP_SIZE; //up
        }
    }

    /**
     * Symbolisches zeichnen des Monsters als Rechteck
     *
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    @Override
    public void draw(PApplet app){
        if (world.isDevMode()) {
            app.pushStyle();
            app.fill(20, 200, 20);
            super.draw(app);
            app.popStyle();
        }
        //funktioniert noch nicht zuverlässig
        /*while(getDirection()==0){
            if(Right1 == null){
                Right1 = app.loadImage("/resource/MonsterRight_1.jpg");
                Right2 = app.loadImage("/resource/MonsterRight_2.jpg");
            }
            app.image(Right1, x , y, width, height);
            //app.image(Right2, x , y, width, height);
        }
        while(getDirection()==1){
            if(Left1 == null) {
                Left1 = app.loadImage("/resource/MonsterLeft_1.jpg");
                Left2 = app.loadImage("/resource/MonsterLeft_2.jpg");
            }
            app.image(Left1, x , y, width, height);
            //app.image(Left2, x , y, width, height);
        }
        while(getDirection()==2){
            if(Front1 == null){
                Front1 = app.loadImage("/resource/MonsterFront_1.jpg");
                Front2 = app.loadImage("/resource/MonsterFront_2.jpg");
            }
            app.image(Front1, x, y, width, height);
            //app.image(Front2, x , y, width, height);
        }
        while(getDirection()==3){
            if(Back1 == null){
                Back1 = app.loadImage("/resource/MonsterBack_1.jpg");
                Back2 = app.loadImage("/resource/MonsterBack_2.jpg");
            }
            app.image(Back1, x, y, width, height);
            //app.image(Back2, x , y, width, height);
        }

         */




    }
}

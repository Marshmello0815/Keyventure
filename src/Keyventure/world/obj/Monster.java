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
    PImage Back3 = null;
    PImage Front1 = null;
    PImage Front2 = null;
    PImage Front3 = null;
    PImage Right1 = null;
    PImage Right2 = null;
    PImage Right3 = null;
    PImage Left1 = null;
    PImage Left2 = null;
    PImage Left3 = null;
    private int steps = 0;

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
            steps++;
        }
        if (direction == 1) {
            x -= STEP_SIZE; //left
            steps++;
        }
        if (direction == 2) {
            y += STEP_SIZE; //down
            steps++;
        }
        if (direction == 3) {
            y -= STEP_SIZE; //up
            steps++;
        }
    }

    /**
     * Symbolisches zeichnen des Monsters als Rechteck
     *
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    @Override
    public void draw(PApplet app) {
        if (world.isDevMode()) {
            app.pushStyle();
            app.stroke(255, 255, 255);
            app.rect(x - 1, y - 1, width + 1, height + 1);
            app.popStyle();
        }
        //funktioniert noch nicht zuverlässig
        if (getDirection() == 0) {
            if (Right1 == null) {
                Right1 = app.loadImage("/resource/monster/MonsterRight_1.jpg");
                Right2 = app.loadImage("/resource/monster/MonsterRight_2.jpg");
                Right3 = app.loadImage("/resource/monster/MonsterRight_3.jpg");
            }
            app.image(Right3, x, y, width, height);
            if ((steps / 4) % 2 == 0) {
                app.image(Right1, x, y, width, height);
                return;
            }
            if ((steps / 6) % 3 == 0) {
                app.image(Right2, x, y, width, height);
                return;
            }

        }
        if (getDirection() == 1) {
            if (Left1 == null) {
                Left1 = app.loadImage("/resource/monster/MonsterLeft_1.jpg");
                Left2 = app.loadImage("/resource/monster/MonsterLeft_2.jpg");
                Left3 = app.loadImage("/resource/monster/MonsterLeft_3.jpg");
            }
            app.image(Left3, x, y, width, height);
            if ((steps / 4) % 2 == 0) {
                app.image(Left1, x, y, width, height);
                return;
            }
            if ((steps / 6) % 3 == 0 || steps % 11 == 0 || steps % 5 == 0) {
                app.image(Left2, x, y, width, height);
                return;
            }

        }
        if (getDirection() == 2) {
            if (Front1 == null) {
                Front1 = app.loadImage("/resource/monster/MonsterFront_1.jpg");
                Front2 = app.loadImage("/resource/monster/MonsterFront_2.jpg");
                Front3 = app.loadImage("/resource/monster/MonsterFront_3.jpg");
            }
            if ((steps / 4) % 2 == 0) {
                app.image(Front1, x, y, width, height);
                return;
            }
            if ((steps / 6) % 3 == 0) {
                app.image(Front2, x, y, width, height);
                return;
            }
            app.image(Front3, x, y, width, height);
        }
        if (getDirection() == 3) {
            if (Back1 == null) {
                Back1 = app.loadImage("/resource/monster/MonsterBack_1.jpg");
                Back2 = app.loadImage("/resource/monster/MonsterBack_2.jpg");
                Back3 = app.loadImage("/resource/monster/MonsterBack_2.jpg");
            }
            if ((steps / 4) % 2 == 0) {
                app.image(Back1, x, y, width, height);
                return;
            }
            if ((steps / 6) % 3 == 0) {
                app.image(Back2, x, y, width, height);
                return;
            }
            app.image(Back3, x, y, width, height);
        }


    }
}

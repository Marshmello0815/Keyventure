package Keyventure.world;

import Keyventure.world.obj.*;
import processing.core.PApplet;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWorld implements IGameWorld {
    List<PassiveObject> passiveObject;
    List<ActiveObject> activeObject;
    Player player;
    FoW fow;
    Lives lives;

    Random random = new Random();

    boolean hasKey = false;
    boolean gameWon = false;
    boolean gameLose = false;
    boolean changeDirectionPossible = false;

    int countKollisionWithMonster = 0;
    int invulnerableTime = 90;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    public GameWorld() {
        passiveObject = new ArrayList<>();
        activeObject = new ArrayList<>();
    }


    void addPassiveObject(PassiveObject passiveObject) {
        this.passiveObject.add(passiveObject);
    }

    void addActiveObject(ActiveObject activeObject) {
        this.activeObject.add(activeObject);
    }

    List<GameObject> allObjects() {
        List<GameObject> objects = new ArrayList<>();
        objects.addAll(passiveObject);
        objects.addAll(activeObject);
        objects.add(player);
        objects.add(lives);
        return objects;
    }


    public void draw(PApplet app) {
        app.background(200, 200, 200);
        for (GameObject object : this.allObjects()) {
            object.draw(app);
        }
        //fow.draw(app);
        for (PassiveObject pObject : this.passiveObject) {
            if (this.player.checkKollision(pObject)) {
                pObject.kollisionWithPlayer();
            }
        }
        for (ActiveObject aObject : this.activeObject) {
            if (this.player.checkKollision(aObject)) {
                if (countKollisionWithMonster == 0 && lives.getLives() > 0) {
                    lives.setLives(lives.getLives() - 1);
                    lives.setLostLives(lives.getLostLives() + 1);
                    makePlayerInvisible();
                    countKollisionWithMonster += 1;
                }
                if (lives.getLives() == 0) {
                    gameLose = true;
                }
            }
        }
        if (countKollisionWithMonster > 0) {
            countKollisionWithMonster += 1;
            if (countKollisionWithMonster == invulnerableTime) {
                countKollisionWithMonster = 0;
            }
        }
        if (gameLose) {
            app.pushStyle();
            app.fill(0, 0, 0);
            //app.rect(0,0, app.width, app.height);
            app.text("Game Over", (float) app.height / 2, (float) app.width / 2);
            app.popStyle();
        }

        if (gameWon) {
            app.pushStyle();
            app.fill(0, 0, 0);
            //app.rect(0,0, app.width, app.height);
            app.text("Game Won", (float) app.height / 2, (float) app.width / 2);
            app.popStyle();
        }

        //zeichnen uiiiiiiii
    }

    @Override
    public void makePlayerInvisible() {
        this.player.makeInvisible();
    }

    public void move(PApplet app) {
        moveMonster();
        if ((app.millis() / 1000) % 3 == 0) {
            if (changeDirectionPossible) {
                changeDirectionPossible = false;
                for (ActiveObject aObject : this.activeObject) {
                    if (aObject instanceof Monster) {
                        aObject.changeDirection();
                    }
                }
            }
        }
        else {
            changeDirectionPossible = true;
        }
    }

    @Override
    public void pickKey(Key key) {
        this.passiveObject.remove(key);
        hasKey = true;
    }

    @Override
    public void playerTouchWall(Wall wall) {
        movePlayerBack();
    }

    @Override
    public void enterDoor(Door door) {
        if (!hasKey) {
            movePlayerBack();
        }
        if (hasKey && this.player.checkKollision(door)) {
            gameWon = true;
        }
    }

    @Override
    public void monsterTouchWall(Monster monster) {
        int oldDirection = monster.getDirection();
        int direction = random.nextInt(4);
        if (oldDirection == 0) { //right
            monster.setX(monster.getX() - 2);
        }
        if (oldDirection == 1) { //left
            monster.setX(monster.getX() + 2);
        }
        if (oldDirection == 2) { //down
            monster.setY(monster.getY() - 2);
        }
        if (oldDirection == 3) { //up
            monster.setY(monster.getY() + 2);
        }
        monster.setDirection(direction);
    }

    private void moveMonster() {
        for (ActiveObject aObject : this.activeObject) {
            aObject.move();
            for (PassiveObject pObject : this.passiveObject) {
                if (aObject.checkKollision(pObject)) {
                    if (pObject instanceof Wall) {
                        aObject.kollisionWithWall();
                    }
                }
            }
        }
    }

    private void movePlayerBack() {
        if (up) {
            this.player.down();
        }
        if (down) {
            this.player.up();
        }
        if (left) {
            this.player.right();
        }
        if (right) {
            this.player.left();
        }
    }

    public void playerUp() {
        this.player.up();
        up = true;
        down = false;
        left = false;
        right = false;
    }

    public void playerDown() {
        this.player.down();
        down = true;
        up = false;
        left = false;
        right = false;
    }

    public void playerLeft() {
        this.player.left();
        left = true;
        down = false;
        up = false;
        right = false;
    }

    public void playerRight() {
        this.player.right();
        right = true;
        down = false;
        left = false;
        up = false;
    }

}

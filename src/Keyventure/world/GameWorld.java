package Keyventure.world;

import Keyventure.*;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class GameWorld implements IGameWorld{
    List<PassiveObject> passiveObject;
    List<ActiveObject> activeObject;
    Player player;
    FoW fow;

    boolean gameWon = false;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    public GameWorld() {
        passiveObject = new ArrayList<>();
        activeObject = new ArrayList<>();
    }


    void addPassiveObject(PassiveObject passiveObject){
        this.passiveObject.add(passiveObject);
    }
    void addActiveObject(ActiveObject activeObject){
        this.activeObject.add(activeObject);
    }

    List<GameObject> allObjects(){
        List<GameObject> objects = new ArrayList<>();
        objects.addAll(passiveObject);
        objects.addAll(activeObject);
        objects.add(player);
        return objects;
    }



    public void draw(PApplet app){
        app.background(200, 200, 200);
        for(GameObject object : this.allObjects()){
            object.draw(app);
        }
        //fow.draw(app);
        for(PassiveObject pObject : this.passiveObject){
            if(this.player.checkKollision(pObject)){
                pObject.kollisionWithPlayer();
            }
            if(gameWon){
                app.pushStyle();
                app.fill(0,0,0);
                //app.rect(0,0, app.width, app.height);
                app.text("Game Won", app.height/2, app.width/2);
                app.popStyle();
            }
        }

        //zeichnen uiiiiiiii
    }

    public void moveActiveObject(){
        //Monster Bewegungsmuster
    }

    @Override
    public void pickKey(Key key){
        this.passiveObject.remove(key);
    }

    @Override
    public void touchWall(Wall wall) {
        if(up){
            this.player.down();
        }
        if(down){
            this.player.up();
        }
        if(left){
            this.player.right();
        }
        if(right){
            this.player.left();
        }
    }
    @Override
    public void enterDoor(Door door){
        gameWon = true;
    }

    public void playerUp() {
        this.player.up();
        up = true;
        down = false;
        left = false;
        right = false;
    }

    public void playerDown(){
        this.player.down();
        down = true;
        up = false;
        left = false;
        right = false;
    }

    public void playerLeft(){
        this.player.left();
        left = true;
        down = false;
        up = false;
        right = false;
    }

    public void playerRight(){
        this.player.right();
        right = true;
        down = false;
        left = false;
        up = false;
    }

}

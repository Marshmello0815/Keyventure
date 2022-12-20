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

        app.rect(20, 20, app.width-40, app.height-40);


        for(GameObject object : this.allObjects()){
            object.draw(app);
        }
        //fow.draw(app);
        for(PassiveObject pObject : this.passiveObject){
            if(this.player.checkKollision(pObject)){
                pObject.kollisionWithPlayer();
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
        this.player.stop();
    }

    public void playerUp() {
        this.player.up();
    }

    public void playerDown(){
        this.player.down();
    }

    public void playerLeft(){
        this.player.left();
    }

    public void playerRight(){
        this.player.right();
    }

}

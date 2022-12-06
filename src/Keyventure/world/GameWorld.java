package Keyventure.world;

import Keyventure.*;


import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    List<PassiveObject> passiveObject;
    List<ActiveObject> activeObject;
    Player player;

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

    public void draw(/*PApplet app*/){
        //zeichnen uiiiiiiii
    }

    public void moveActiveObject(){
        //Monster Bewegungsmuster
    }

    public void playerUp(){
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

    /*public List<PassiveObject> getPassiveObjects() {
        return this.passiveObject;
    }

    public List<ActiveObject> getActiveObjects() {
        return this.activeObject;
    }

    public Player getPlayer(){
        return this.player;
    }
     */

}

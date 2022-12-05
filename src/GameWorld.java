import java.util.List;

public class GameWorld {
    List<PassiveObject> passiveObject;
    List<ActiveObject> activeObject;
    Player player;

    public List<PassiveObject> getPassiveObjects() {
        return this.passiveObject;
    }

    public List<ActiveObject> getActiveObjects() {
        return this.activeObject;
    }

    public Player getPlayer(){
        return this.player;
    }

}

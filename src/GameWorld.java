import java.util.List;

public class GameWorld {
    List<PassiveObjects> passiveObject;
    List<ActiveObjects> activeObject;
    Player player;

    public List<PassiveObjects> getPassiveObjects() {
        return this.passiveObject;
    }

    public List<ActiveObjects> getActiveObjects() {
        return this.activeObject;
    }

    public Player getPlayer(){
        return this.player;
    }

}

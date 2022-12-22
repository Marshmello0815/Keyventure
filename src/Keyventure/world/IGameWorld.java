package Keyventure.world;

import Keyventure.world.obj.Door;
import Keyventure.world.obj.Key;
import Keyventure.world.obj.Monster;
import Keyventure.world.obj.Wall;

public interface IGameWorld {

    void pickKey(Key key);
    void touchWall(Wall wall);
    void enterDoor(Door door);
    void monsterTouchWall(Monster monster);
    void makePlayerInvisible();
}

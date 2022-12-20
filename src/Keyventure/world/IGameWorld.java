package Keyventure.world;

import Keyventure.Door;
import Keyventure.Key;
import Keyventure.Monster;
import Keyventure.Wall;

public interface IGameWorld {

    void pickKey(Key key);
    void touchWall(Wall wall);
    void enterDoor(Door door);
    void monsterTouchWall(Monster monster);
}

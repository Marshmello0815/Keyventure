package Keyventure.world;

import Keyventure.world.obj.*;

public interface IGameWorld {

    void pickKey(Key key);

    void pickWeapon(Weapon weapon);

    void killMonster(ActiveObject monster);

    void playerTouchWall(Wall wall);

    void enterDoor(Door door);

    void monsterTouchNotPlayerObject(Monster monster);

    void makePlayerInvisible();

    boolean isDevMode();

    boolean isFowOn();

}

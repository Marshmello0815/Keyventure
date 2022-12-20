package Keyventure;

import Keyventure.world.IGameWorld;

public abstract class ActiveObject extends GameObject{
    public ActiveObject(IGameWorld world, int x, int y, int width, int height) {
        super(world, x, y, width, height);
    }

    public abstract void move();
    public abstract void kollisionWithWall();
}

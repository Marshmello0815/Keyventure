package Keyventure.world.obj;

import Keyventure.world.IGameWorld;

public abstract class ActiveObject extends GameObject {
    public ActiveObject(IGameWorld world, int x, int y, int width, int height) {
        super(world, x, y, width, height);
    }

    public abstract void move();

    public abstract void collisionWithNotPlayerObject();

    public abstract void collisionWithSword();

    public abstract void changeDirection();
}

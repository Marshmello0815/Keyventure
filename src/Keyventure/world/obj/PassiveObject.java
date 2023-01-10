package Keyventure.world.obj;

import Keyventure.world.IGameWorld;

public abstract class PassiveObject extends GameObject {

    public PassiveObject(IGameWorld world, int x, int y, int width, int height) {
        super(world, x, y, width, height);
    }

    public abstract void collisionWithPlayer();

}


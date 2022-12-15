package Keyventure;

public abstract class ActiveObject extends GameObject{
    public ActiveObject(IGameWorld world, int x, int y, int width, int height) {
        super(world, x, y, width, height);
    }
}

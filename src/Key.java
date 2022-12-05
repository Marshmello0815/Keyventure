public class Key extends PassiveObject{
    private int x = 42;
    private int y = 42;

    public Key(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(){
        System.out.println("Drawing Key ...");
    }
}

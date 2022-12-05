public class Monster extends ActiveObject{

    public Monster(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(){
        System.out.println("Drawing cute Monster ...");
    }
}

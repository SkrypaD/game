import java.awt.*;

public class HardBody extends GameObject{
    protected Vector2 position;
    protected Image sprite;
    protected int width;
    protected int height;
    protected int speed;

    public HardBody(Vector2 position, int width, int height, int speed, Image sprite){
        this.position = new Vector2(position);
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.speed = speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }

    public void onCollision(GameObject other){
    }


    @Override
    public void update(){
        super.update();
        //position.setX(position.getX() + (int)speed);
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}
}

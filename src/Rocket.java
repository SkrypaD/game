import javax.swing.*;
import java.awt.*;

public class Rocket extends HardBody{

    protected final Image sprite = new ImageIcon("rocket.png").getImage();
    protected double damage;
    public Rocket(Vector2 position, int speed,  double damage) {
        super(position, 6, 30, speed, loadDefaultRocketImage());
        this.damage = damage;
    }

    private static Image loadDefaultRocketImage() {
        return new ImageIcon("rocket.png").getImage();
    }

    @Override
    public void update(){
        super.update();
        position = new Vector2(position.getX(), position.getY() - speed);
        if(position.getY() < 0){
            toDispose = true;
        }
    }

    @Override
    public void onCollision(GameObject other){
        if(other instanceof Enemy){
            ((Enemy) other).takeDamage(damage);
            toDispose = true;
        }
    }
}

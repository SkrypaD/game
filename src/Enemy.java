import java.awt.*;

public class Enemy extends Character {

    public Enemy(Vector2 position, int width, int height, int speed, Image sprite, double health, double damage) {
        super(position, width, height, speed, sprite, health, damage);
    }

    @Override
    public void update(){
        super.update();
        position = new Vector2(position.getX(), position.getY() + speed);
        if(!isInWindow()){
            toDispose = true;
        }
    }

    @Override
    public void onCollision(GameObject other){
        if(other instanceof Player){
            ((Player) other).takeDamage(damage);
            toDispose = true;
        }
    }

    private boolean isInWindow(){
        return position.getY() < Game.WINDOW_HEIGHT;
    }
}

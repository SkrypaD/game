import java.awt.*;

public class Character extends HardBody{
    protected double health;
    protected double damage;
    public Character(Vector2 position, int width, int height, int speed, Image sprite, double health, double damage) {
        super(position, width, height, speed, sprite);
        this.health = health;
        this.damage = damage;
    }

    public void takeDamage(double damage){
        this.health -= damage;
        if(this.health <= 0){
            toDispose = true;
        }
    }

    public double getHealth() { return health; }
    public double getDamage() { return damage; }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class Player extends Character{

    HashSet<Integer> pressedKeys = new HashSet<>();
    private int rocketSpeed = 15;
    private int attackSpeed = 3;
    private Timer fireRate;

    public Player(Vector2 position, int width, int height, int speed, Image sprite, double health, double damage, HashSet<Integer> pressedKeys) {
        super(position, width, height, speed, sprite, health, damage);
        this.pressedKeys = pressedKeys;

        fireRate = new Timer(1000 / attackSpeed, e -> {
            Game.addGameObject(generateRocket());
        });
        fireRate.start();
    }

    @Override
    public void update(){

        if (pressedKeys.contains(KeyEvent.VK_LEFT) && position.getX() > 0) {
            position.setX(position.getX() - speed);
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT) && position.getX() < Game.WINDOW_WIDTH - width) {
            position.setX(position.getX() + speed);
        }
    }

    public Rocket generateRocket(){
        Vector2 pos = new Vector2(position.getX() + width / 2, position.getY());
        return new Rocket(pos, rocketSpeed, damage);
    }

    @Override
    public void onDispose(){
        fireRate.stop();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class Player extends Character{

    HashSet<Integer> pressedKeys = new HashSet<>();
    private int rocketSpeed = 15;
    private int attackSpeed = 3;
    private Timer fireRate;
    private int lastShot;

    public Player(int width, int height, int speed, Image sprite, double health, double damage, HashSet<Integer> pressedKeys) {
        super(new Vector2(Game.WINDOW_WIDTH / 2 - width, Game.WINDOW_HEIGHT - height * 2), width, height, speed, sprite, health, damage);
        this.pressedKeys = pressedKeys;

        fireRate = new Timer(1000 / attackSpeed, e -> {
            Game.addGameObject(generateRocket());
        });
        fireRate.start();
    }

    @Override
    public void update(){
        super.update();



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
    public void onDispose(){ fireRate.stop(); }

    public void increaseRocketSpeed(int upgrade){ rocketSpeed += upgrade; }
    public void increasePlayerSpeed(int upgrade){ speed += upgrade; }
    public void increaseAttackSpeed(int upgrade){ attackSpeed += upgrade; }
    public void increaseHealth(int upgrade){ health += upgrade; }
    public void increaseDamage(int upgrade){ damage += upgrade; }
}

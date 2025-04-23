import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EnemySpawner extends GameObject{
    private int enemiesPerSecond;
    private int enemiesDamage = 10;
    private int enemiesHealth = 50;
    private int enemiesSpeed = 10;
    private int currentTimeMultiplier = 10;
    private int statsIncrease = 0;

    public EnemySpawner(int enemiesPerSecond) {
        this.enemiesPerSecond = enemiesPerSecond;
        spawn();
    }

    public void spawn(){
        Timer timer = new Timer(1000 / enemiesPerSecond, e -> {
            Random rand = new Random();
            int randX = rand.nextInt(Game.WINDOW_WIDTH - 100);

            statsIncrease = Game.getCurrentTimeInMinutes() * currentTimeMultiplier;

            Image enemySprite = new ImageIcon("su34.png").getImage();
            Vector2 pos = new Vector2(randX,-200);

            Enemy enemy = new Enemy(pos, 100, 100, enemiesSpeed, enemySprite, enemiesHealth + statsIncrease, enemiesDamage + statsIncrease);
            Game.addGameObject(enemy);
        });
        timer.start();
    }
}

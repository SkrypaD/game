import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EnemySpawner extends GameObject{
    private int enemiesPerSecond = 3;
    private int enemiesDamage = 10;
    private int enemiesHealth = 100;
    private int enemiesSpeed = 10;
    private int currentTimeMultiplier = 10;
    private int statsIncrease = 0;

    public EnemySpawner() {
        spawn();
    }

    public void spawn(){
        Timer timer = new Timer(1000 / enemiesPerSecond, e -> {
            Random rand = new Random();
            int randX = rand.nextInt(Game.WINDOW_WIDTH - 100);


            statsIncrease = Game.getCurrentTimeInMinutes() * currentTimeMultiplier;

            System.out.println(statsIncrease);


            Image img2 = new ImageIcon("su34.png").getImage();
            Vector2 pos = new Vector2(randX,-200);

            Enemy enemy = new Enemy(pos , 100, 100, enemiesSpeed + statsIncrease / 10, img2, enemiesHealth + statsIncrease, enemiesDamage + statsIncrease);
            Game.addGameObject(enemy);
        });
        timer.start();
    }
}

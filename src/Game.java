import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public final class Game {
    private static Game instance;
    private static ArrayList<GameObject> gameObjects;
    private static ArrayList<GameObject> gameObjectsToBeAdded;
    private static GameFrame frame;
    private static GamePanel gamePanel;
    private static GameManager gameManager;
    private static Timer timer;

    private static int score;
    public static final int WINDOW_HEIGHT = 800;
    public static final int WINDOW_WIDTH = 800;
    private static boolean paused = false;

    private static double startTime;
    private static double currentTime;


    private static JLabel health;
    private static Player player;

    private Game(){
        gameObjects = new ArrayList<>();
        gameObjectsToBeAdded = new ArrayList<>();
        gameManager = new GameManager();
        gamePanel = new GamePanel(gameObjects);
        frame = new GameFrame(WINDOW_HEIGHT, WINDOW_WIDTH);
        frame.add(gamePanel);

        GameButton button = new GameButton("Start", new Vector2(WINDOW_WIDTH - 150, 50), 100, 50);
        button.addActionListener(e -> {
            paused = !paused;
            if(paused && timer.isRunning()) {
                timer.stop();
            }else {
                timer.restart();
            }
        });
        button.setForeground(new Color(240, 240, 240));

        EnemySpawner spawner = new EnemySpawner();



        Image img = new ImageIcon("f16.png").getImage();
        Vector2 pos = new Vector2(WINDOW_WIDTH / 2, WINDOW_HEIGHT - 200);
        player = new Player(pos, 100, 100, 15, img, 100, 100, gamePanel.getPressedKeys());
        gameObjects.add(player);

        health = new JLabel("health: " + player.getHealth());
        health.setFont(new Font("Arial", Font.BOLD, 24));
        health.setForeground(new Color(240, 240, 240));
        health.setBounds(10, 40, 250, 40);
        health.setHorizontalAlignment(SwingConstants.LEFT);

        gamePanel.add(health);
        gamePanel.add(button);

    }

    public void run(){
        timer = new Timer(32, e -> {
            currentTime = System.currentTimeMillis() - startTime;
            gameObjects.addAll(gameObjectsToBeAdded);
            gameObjectsToBeAdded.clear();

            health.setText("health: " + player.getHealth());

            gameManager.update(gameObjects);
            gamePanel.repaint();
            gameManager.checkCollision(gameObjects);
            gameManager.disposeOfObjects(gameObjects);
        });
        timer.start();
        startTime = System.currentTimeMillis();
    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public static void addGameObject(GameObject gameObject){
        if(gameObject != null)
            gameObjectsToBeAdded.add(gameObject);
    }

    public static void increaseScore(int change){
        if(change >= 0){
            score += change;
        }
    }

    public static int getScore(){ return score; }
    public static int getCurrentTimeInSeconds(){ return (int)(currentTime / 1000); }
    public static int getCurrentTimeInMinutes(){ return (int)(currentTime / 1000 / 60); }
}

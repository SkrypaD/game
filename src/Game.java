import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public final class Game {
    private static Game instance;
    private static ArrayList<GameObject> gameObjects;
    private static ArrayList<GameObject> gameObjectsToBeAdded;
    private static GameFrame frame;
    private static GamePanel gamePanel;
    private static MenuPanel menuPanel;
    private static Timer timer;
    private static EnemySpawner spawner;
    private static GameManager gameManager;

    public static final int WINDOW_HEIGHT = 800;
    public static final int WINDOW_WIDTH = 800;
    public static final int BONUS_CHANCE = 5;
    private static int score;
    private static int userId;
    private static boolean paused = false;

    private static double startTime;
    private static double currentTime;

    private static Player player;

    private Game(){
        gameObjects = new ArrayList<>();
        gameObjectsToBeAdded = new ArrayList<>();

        gamePanel = new GamePanel(gameObjects);
        menuPanel = new MenuPanel();

        frame = new GameFrame(WINDOW_HEIGHT, WINDOW_WIDTH);
        frame.add(menuPanel);
    }

    private void run(){
        player = new Player(100, 100, 15, new ImageIcon("f16.png").getImage(), 100, 100, gamePanel.getPressedKeys());
        spawner = new EnemySpawner(3);
        gameManager = new GameManager(player);

        gameObjects.add(player);

        timer = new Timer(32, e -> {
            currentTime = System.currentTimeMillis() - startTime;
            if(gameObjectsToBeAdded.size() > 0) {
                gameObjects.addAll(gameObjectsToBeAdded);
                gameObjectsToBeAdded.clear();
            }

            gameManager.update(gameObjects);
            gameManager.checkCollision(gameObjects);
            gameManager.disposeOfObjects(gameObjects);
            gamePanel.repaint();
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

    public static void startGame(){
        frame.remove(menuPanel);
        frame.add(gamePanel);
        frame.revalidate();
        frame.repaint();
        if(startTime != currentTime){
            unpauseGame();
        }else{
            instance.run();
        }
    }

    public static void restartGame(){
        gameObjects.clear();
        gameObjectsToBeAdded.clear();
        startTime = System.currentTimeMillis();
        score = 0;

        player.onDispose();
        player = new Player(100, 100, 15, new ImageIcon("f16.png").getImage(), 100, 100, gamePanel.getPressedKeys());
        gameObjects.add(player);
    }

    public static boolean isPaused(){ return paused; }
    public static void pauseGame(){
        paused = true;
        spawner.stopSpawn();
        timer.stop();
    }
    public static void unpauseGame(){
        paused = false;
        spawner.restartSpawn();
        timer.restart();
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

    public static void goToMainMenu(){
        Game.pauseGame();
        frame.remove(gamePanel);
        frame.add(menuPanel);
        menuPanel.repaint();
        frame.revalidate();
        frame.repaint();
    }

    public static void setUserId(int userId){ instance.userId = userId; }
    public static int getPlayerId(){ return userId; }
    public static double getPlayerHealth(){ return player.getHealth(); }
    public static int getScore(){ return score; }
    public static int getCurrentTimeInSeconds(){ return (int)(currentTime / 1000); }
    public static int getCurrentTimeInMinutes(){ return (int)(currentTime / 1000 / 60); }
}

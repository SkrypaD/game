import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class GamePanel extends JLayeredPane {
    private ArrayList<GameObject> gameObjects;
    private GameLabel score;
    private GameLabel health;

    private HashSet<Integer> pressedKeys = new HashSet<>();

    public GamePanel(final ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        setBackground(new Color(24, 24, 24));

        score = new GameLabel("Score");
        score.setBounds(10, 50, 250, 40);
        score.setHorizontalAlignment(SwingConstants.LEFT);

        health = new GameLabel("Health");
        health.setBounds(10, 90, 250, 40);
        health.setHorizontalAlignment(SwingConstants.LEFT);

        GameButton pauseButton = new GameButton("Pause");
        pauseButton.setBounds(Game.WINDOW_WIDTH - 200, 50, 150, 50);
        pauseButton.addActionListener(e -> {
            if(Game.isPaused())
                Game.unpauseGame();
            else
                Game.pauseGame();
        });

        GameButton restartButton = new GameButton("Restart");
        restartButton.setBounds(Game.WINDOW_WIDTH - 200, 120, 150, 50);
        restartButton.addActionListener(e -> { Game.restartGame(); });

        GameButton homeButton = new GameButton("Home");
        homeButton.setBounds(Game.WINDOW_WIDTH - 200, 190, 150, 50);
        homeButton.addActionListener(e -> { Game.goToMainMenu(); });

        setupKeyBindings();

        add(pauseButton);
        add(restartButton);
        add(homeButton);
        add(score, JLayeredPane.PALETTE_LAYER);
        add(health, JLayeredPane.PALETTE_LAYER);
    }


    @Override
    protected void paintComponent(Graphics g) {
        score.setText("Score: " + Game.getScore());
        health.setText("Health: " + Game.getPlayerHealth());

        super.paintComponent(g);
        for(GameObject obj : gameObjects) {
            if(obj instanceof HardBody) {
                HardBody hb = (HardBody)obj;
                g.drawImage(hb.sprite, hb.position.getX(), hb.position.getY(),
                        hb.width, hb.height, null);
            }
        }
    }

    public HashSet<Integer> getPressedKeys() {
        return pressedKeys;
    }

    private void setupKeyBindings() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "leftPressed");
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "rightPressed");

        inputMap.put(KeyStroke.getKeyStroke("released A"), "leftReleased");
        inputMap.put(KeyStroke.getKeyStroke("released D"), "rightReleased");

        actionMap.put("leftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add(KeyEvent.VK_LEFT);
            }
        });
        actionMap.put("rightPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add(KeyEvent.VK_RIGHT);
            }
        });

        actionMap.put("leftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove(KeyEvent.VK_LEFT);
            }
        });
        actionMap.put("rightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove(KeyEvent.VK_RIGHT);
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class GamePanel extends JLayeredPane {
    private ArrayList<GameObject> gameObjects;
    private JLabel score;

    private HashSet<Integer> pressedKeys = new HashSet<>();

    public GamePanel(final ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        setBackground(new Color(24, 24, 24));
        setLayout(null);

        score = new JLabel("Score");
        score.setFont(new Font("Arial", Font.BOLD, 24));
        score.setForeground(new Color(240, 240, 240));
        score.setBounds(10, 10, 250, 40);
        score.setHorizontalAlignment(SwingConstants.LEFT);

        setupKeyBindings();

        add(score, JLayeredPane.PALETTE_LAYER);
    }


    @Override
    protected void paintComponent(Graphics g) {
        score.setText("Score: " + Game.getScore());
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

        // Released actions
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

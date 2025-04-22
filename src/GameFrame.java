import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;
    public GameFrame(int windowHeight, int windowWidth){
        this.WINDOW_HEIGHT = windowHeight;
        this.WINDOW_WIDTH = windowWidth;

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(new Color(24, 24, 24));
        getContentPane().setBackground(new Color(24, 24, 24));
    }
}

import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {
    private Vector2 position;
    public GameButton(String text, Vector2 position, int width, int height) {
        super(text);
        setBounds(position.getX(), position.getY(), width, height);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 24));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


}

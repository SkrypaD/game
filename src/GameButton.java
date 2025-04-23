import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {
    private Vector2 position;
    public GameButton(String text){
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 24));
        setForeground(new Color(240, 240, 240));
    }
}

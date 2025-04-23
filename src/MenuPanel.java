import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        setBackground(new Color(24, 24, 24));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel panel = createInputPanel();
        this.add(panel);
    }

    private void setFontAndColor(JComponent component){
        component.setFont(new Font("Arial", Font.PLAIN, 20));
        component.setBackground(new Color(24, 24, 24));
        component.setForeground(new Color(240, 240, 240));
    }


    private JPanel createInputPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(24, 24, 24));

        mainPanel.add(Box.createVerticalGlue());

        GameLabel usernameLabel = new GameLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(usernameLabel);

        JTextField usernameField = new JTextField(15);
        usernameField.setMaximumSize(usernameField.getPreferredSize());
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        setFontAndColor(usernameField);
        mainPanel.add(usernameField);

        mainPanel.add(Box.createVerticalStrut(10));

        JButton submitButton = new JButton("Login");
        setFontAndColor(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> {
            System.out.println(usernameField.getText());
            Game.startGame();
        });
        mainPanel.add(submitButton);

        mainPanel.add(Box.createVerticalGlue());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return mainPanel;
    }
}

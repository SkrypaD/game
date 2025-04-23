import DataAccess.Entities.Record;
import DataAccess.Repositories.RecordRepository;
import DataAccess.Repositories.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuPanel extends JPanel {

    private JPanel inputPanel;
    private JPanel table;
    private GameLabel tableContent;

    private ArrayList<Record> records;
    public MenuPanel() {
        setBackground(new Color(24, 24, 24));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        inputPanel = createInputPanel();
        table = createTable();
        this.add(inputPanel);
        this.add(table);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        tableContent.setText(receiveData());
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

        JButton submitButton = new JButton("Play");
        setFontAndColor(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> {
            UserRepository userRepository = new UserRepository();
            try{
                userRepository.create( usernameField.getText());
                Game.setUserId(userRepository.getByUsername(usernameField.getText()).getId());
            }catch(Exception ex){
                System.out.println(ex);
            }
            Game.startGame();
        });
        mainPanel.add(submitButton);

        mainPanel.add(Box.createVerticalGlue());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return mainPanel;
    }


    private JPanel createTable(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 24, 24));
        tableContent = new GameLabel("");
        try{
            tableContent.setText(receiveData());
            panel.add(tableContent);
        }catch(Exception e){
            System.out.println(e);
        }

        return panel;
    }

    private String receiveData(){

        RecordRepository recordRepository = new RecordRepository();
        UserRepository userRepository = new UserRepository();

        try{
            records = recordRepository.getTopRecords();

            StringBuilder sb = new StringBuilder();
            sb.append("<html><table border='1' cellpadding='4' cellspacing='0'>");
            sb.append("<tr><th>User ID</th><th>Score</th><th>Play Time</th><th>Date</th></tr>");

            for (Record r : records) {
                sb.append("<tr>");
                sb.append("<td>").append(userRepository.getById(r.getUserId()).getUsername()).append("</td>");
                sb.append("<td>").append(r.getScore()).append("</td>");
                sb.append("<td>").append(r.getPlayTime().toString()).append("</td>");
                sb.append("<td>").append(r.getDate().toString()).append("</td>");
                sb.append("</tr>");
            }

            sb.append("</table></html>");
            return sb.toString();
        }catch(Exception e){
            System.out.println(e);
        }

        return "";
    }
}

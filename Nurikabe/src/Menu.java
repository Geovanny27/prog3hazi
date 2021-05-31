import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu implements ActionListener {
    private JButton startgame = new JButton("Start Game");
    private JButton leaderboard = new JButton("Leaderboard");
    private JButton quit = new JButton("Quit");
    private JFrame frame = new JFrame("Nurikabe");

    public Menu() {
        frame.setSize(300, 300);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(Box.createRigidArea(new Dimension(100, 70)));
        p.add(startgame);
        p.add(leaderboard);
        p.add(quit);
        startgame.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(p);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        startgame.addActionListener(this);
        quit.addActionListener(this);
        leaderboard.addActionListener(this);

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Leaderboard.read();
        new Menu();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(startgame)) {
            try {
                new Nurikabe();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            frame.dispose();
        } else if (actionEvent.getSource().equals(quit)) {
            System.exit(0);
        } else if (actionEvent.getSource().equals(leaderboard)) {
            boolean success = false;
            int mapid = -1;
            while (!success) {
                try {
                    String m = JOptionPane.showInputDialog(
                            frame,
                            "Which puzzle's leaderboard do you want to see?",
                            "Leaderboard",
                            JOptionPane.PLAIN_MESSAGE);
                    if (m == null) {
                        return;
                    }
                    mapid = Integer.parseInt(m);
                    if (mapid >= 0 && mapid < (Map.getMapcount()))
                        success = true;
                    else
                        JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                    success = false;
                }
            }
            try {
                new LeaderboardWindow(mapid);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            frame.dispose();
        }
    }
}

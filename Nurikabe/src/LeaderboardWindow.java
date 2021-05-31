import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LeaderboardWindow implements ActionListener {
    JFrame frame = new JFrame("Leaderboard");
    JPanel panel = new JPanel();
    JButton back = new JButton("Back");

    public LeaderboardWindow(int mapid) throws IOException, ClassNotFoundException {
        Leaderboard.sort();
        JTable table = new JTable(Leaderboard.getLeaderboard(mapid), new String[]{"Place", "Name", "Time"});
        panel.add(back);
        panel.setLayout(new FlowLayout());
        back.setAlignmentX(Component.LEFT_ALIGNMENT);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(new JScrollPane(table));
        frame.add(panel);
        panel.setMaximumSize(back.getPreferredSize());
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        back.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(back)) {
            new Menu();
            frame.dispose();
        }
    }
}

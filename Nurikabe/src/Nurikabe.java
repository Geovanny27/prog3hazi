import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class Nurikabe implements ActionListener {
    private int x = 5, y = 5;
    private Map map = new Map();
    private int[][] solution = new int[x][y];
    JFrame frame = new JFrame("Nurikabe");
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Options");
    JMenuItem passItem = new JMenuItem("New puzzle");
    JMenuItem quitItem = new JMenuItem("Quit");
    JButton done = new JButton("Done");
    JButton pass = new JButton("New puzzle");
    JButton quit = new JButton("Quit");
    JButton back = new JButton("Back");
    JButton[][] buttons = new JButton[x][y];
    Container grid = new Container();
    JPanel bottom = new JPanel();
    JTextField stopwatch = new JTextField("00:00");
    JPanel top = new JPanel();
    JLabel mapid = new JLabel("MapID: " + map.getCurrentmap());
    Time time = new Time();
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            time.tick();
            stopwatch.setText(String.format("%02d", time.m) + ":" + String.format("%02d", time.s));
        }
    });

    public Nurikabe() throws Throwable {
        menu.add(passItem);
        menu.add(quitItem);
        passItem.addActionListener(this);
        quitItem.addActionListener(this);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.setSize(300, 350);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        grid.setLayout(new GridLayout(x, y));
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                if (map.getMap()[i][j] > 0) {
                    buttons[i][j].setText(Integer.toString(map.getMap()[i][j]));
                    solution[i][j] = map.getMap()[i][j];
                } else {
                    buttons[i][j].addActionListener(this);
                    solution[i][j] = 0;
                }
                grid.add(buttons[i][j]);
            }
        }
        bottom.add(done);
        bottom.add(pass);
        bottom.add(back);
        bottom.add(quit);
        top.add(stopwatch);
        top.add(mapid);
        frame.add(top);
        top.setMaximumSize(new Dimension(100, 100));
        frame.add(grid);
        frame.add(bottom);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        quit.addActionListener(this);
        pass.addActionListener(this);
        done.addActionListener(this);
        back.addActionListener(this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(done)) {
            if (Arrays.deepEquals(solution, map.getMap())) {
                timer.stop();
                int n = JOptionPane.showConfirmDialog(frame,
                        "You solved this puzzle!\n" +
                                "Would you like to be on the leaderboard?",
                        "Congratulations!",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    boolean success = false;
                    String username = "";
                    while (!success) {
                        username = JOptionPane.showInputDialog(
                                frame,
                                "Your username:",
                                "Leaderboard",
                                JOptionPane.PLAIN_MESSAGE);
                        if (username == null) {
                            return;
                        } else  {
                            success = true;
                        }
                    }
                    try {
                        Leaderboard.add(new Record(username, time, map.getCurrentmap()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                JOptionPane.showMessageDialog(frame,
                        "You made a mistake somewhere!",
                        "Oops",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
        if (actionEvent.getSource().equals(quit) || actionEvent.getSource().equals(quitItem)) {
            try {
                Leaderboard.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
        if (actionEvent.getSource().equals(pass) || actionEvent.getSource().equals(passItem)) {
            frame.dispose();
            try {
                new Nurikabe();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource().equals(back)) {
            try {
                Leaderboard.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
            frame.dispose();
            new Menu();
        }
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                if (actionEvent.getSource().equals(buttons[i][j])) {
                    if (buttons[i][j].getBackground() == Color.BLACK) {
                        buttons[i][j].setBackground(Color.WHITE);
                        solution[i][j] = 0;
                    } else {
                        buttons[i][j].setBackground(Color.BLACK);
                        solution[i][j] = -1;
                    }
                }
            }
        }
    }
}

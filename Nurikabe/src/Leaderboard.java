import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    private static ArrayList<Record> leaderboard = new ArrayList<>();
    private static File f = new File("leaderboard.txt");


    public static void read() throws IOException, ClassNotFoundException {
        boolean eof = false;
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(f));
            while (!eof) {
                try {
                    Record line = (Record) reader.readObject();
                    leaderboard.add(line);
                } catch (EOFException e) {
                    eof = true;
                }
            }
            reader.close();
        } catch (EOFException ignore) {
        }
    }

    public static void sort() {
        for (int i = 0; i < leaderboard.size(); i++) {
            for (int j = i + 1; j < leaderboard.size(); j++) {
                if (leaderboard.get(j).getTime().getTimeSec() < leaderboard.get(i).getTime().getTimeSec()) {
                    Record r = leaderboard.get(j);
                    leaderboard.set(j, leaderboard.get(i));
                    leaderboard.set(i, r);
                }
            }
        }
    }

    public static void add(Record r) throws IOException {
        leaderboard.add(r);
    }

    public static String[][] getLeaderboard(int mapid) throws IOException {
        String[][] l = new String[leaderboard.size()][3];
        for (int i = 0, j = 0; i < leaderboard.size(); i++) {
            if (leaderboard.get(i).getMapID() == mapid) {
                l[j][0] = Integer.toString(j + 1);
                l[j][1] = leaderboard.get(i).getName();
                l[j][2] = String.format("%02d", leaderboard.get(i).getTime().getM()) + ":" + String.format("%02d", leaderboard.get(i).getTime().getS());
                j++;
            }
        }
        return l;
    }
    public static void write() throws IOException {
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
        for(int i=0;i<leaderboard.size();i++){
            writer.writeObject(leaderboard.get(i));
        }
        writer.close();
    }

}

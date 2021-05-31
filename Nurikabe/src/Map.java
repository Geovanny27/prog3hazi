import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Map {
    private int[][] map = new int[5][5];
    private static int mapcount = 4;
    private static int previousMap;
    private int currentmap;

    public Map() throws IOException {
        File f = new File("maps.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        Random r = new Random();
        do {
            currentmap = r.nextInt(mapcount);
        } while (currentmap == previousMap);
        while (!reader.readLine().equals(Integer.toString(currentmap))) ;
        for (int i = 0; i < 5; i++) {
            String line = reader.readLine();
            String[] lines = line.split(" ");
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(lines[j]);
            }
        }
        previousMap = currentmap;
    }

    public int[][] getMap() {
        return map;
    }

    public int getCurrentmap() {
        return currentmap;
    }

    public static int getMapcount() {
        return mapcount;
    }


}


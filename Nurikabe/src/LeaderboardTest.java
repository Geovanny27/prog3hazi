import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LeaderboardTest {
    Record r,s,t;
    String[][] expected=new String[3][3];
    @Before
    public void before() throws IOException {
        r=new Record("első",new Time(0,1),0);
        s=new Record("második",new Time(0,32),0);
        t=new Record("harmadik",new Time(2,21),0);
        Leaderboard.add(s);
        Leaderboard.add(t);
        Leaderboard.add(r);
        expected[0][0]="1";
        expected[0][1]="első";
        expected[0][2]="00:01";
        expected[1][0]="2";
        expected[1][1]="második";
        expected[1][2]="00:32";
        expected[2][0]="3";
        expected[2][1]="harmadik";
        expected[2][2]="02:21";
        Leaderboard.sort();

    }
    @Test
    public void sort() throws IOException {
        assertArrayEquals(expected,Leaderboard.getLeaderboard(0));
    }
}
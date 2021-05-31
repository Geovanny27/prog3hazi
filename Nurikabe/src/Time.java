import java.io.Serializable;

public class Time implements Serializable {
    int m = 0, s = 0;

    public void tick() {
        s++;
        if (s > 60) {
            s = 0;
            m++;
        }
    }

    public Time() {
    }

    public int getTimeSec() {
        return m * 60 + s;
    }

    public Time(int min, int sec) {
        m = min;
        s = sec;
    }

    public int getM() {
        return m;
    }

    public int getS() {
        return s;
    }

    @Override
    public String toString() {
        return m+" "+s;
    }
}

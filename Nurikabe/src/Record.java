import java.io.Serializable;

public class Record implements Serializable {
    private String name;
    private Time time;
    private int mapID;

    public Record(String n, Time t, int m) {
        name = n;
        time = t;
        mapID = m;
    }

    public String getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }

    public int getMapID() {
        return mapID;
    }

    @Override
    public String toString() {
        return name+time+mapID;
    }
}

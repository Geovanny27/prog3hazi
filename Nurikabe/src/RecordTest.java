import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecordTest {
    private String name;
    private Time time;
    private int mapID;
    private Record r;
    @Before
    public void before(){
        name="név";
        time=new Time(10,20);
        mapID=12;
        r=new Record(name,time,mapID);
    }
    @Test
    public void testToString() {
        Assert.assertEquals("név10 2012",r.toString());
    }
}
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {
    Time time;
    int a,b;
    @Before
    public void before(){
        a=10;
        b=20;
        time=new Time(a,b);
    }
    @Test
    public void testToString() {
        assertEquals("10 20",time.toString());
    }
}
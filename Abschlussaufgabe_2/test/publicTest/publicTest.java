package publicTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.ioProcessing.RuntimeManager;

public class publicTest {

    RuntimeManager manager;

    @Test
    public void test() {
        /*String[] initialParameters = {"5,5,A,+,L,+,D,+,A0,*,D0,+,L,*,d,*,L,+,C0,d,B0,+,C,+,L,+,B"};
        manager = new RuntimeManager(initialParameters);
        assertEquals("x,+,x,+,x\n+,x,*,x,+\nx,*,x,*,x\n+,x,x,x,+\nx,+,x,+,x", manager.processIO("show-board"));
        assertEquals("d,A0", manager.processIO("show-field 1,1"));
        assertEquals("w,2", manager.processIO("extinguish A0,0,1"));
        assertEquals("1", manager.processIO("refill A0"));
        assertEquals("B", manager.processIO("turn "));
        assertEquals("OK", manager.processIO("move B0,2,2"));
        assertEquals("C", manager.processIO("turn"));
        assertEquals("+,2", manager.processIO("extinguish C0,2,1"));
        assertEquals("x,x,x,+,x\n+,x,*,x,+\nx,+,x,*,x\n+,x,x,x,+\nx,+,x,+,x", manager.processIO("show-board"));
        assertEquals("D", manager.processIO("turn"));
        assertEquals("D,0\nD0,3,3,1,3", manager.processIO("show-player"));
        assertEquals("B", manager.processIO("turn"));
        assertEquals("OK", manager.processIO("fire-to-roll 4"));
        assertEquals("x,x,x,*,x\n*,x,*,x,*\nx,*,+,*,x\n*,x,x,+,*\nx,*,x,*,x", manager.processIO("show-board"));*/
    }

}

//>show-board 
//x,+,x,+,x
//+,x,*,x,+
//x,*,x,*,x
//+,x,x,x,+
//x,+,x,+,x
//>show-field 1,1
//d,A0
//>extinguish A0,0,1
//w,2
//>refill A0
//1
//>turn 
//B
//>move B0,2,2 
//OK
//>turn
//C
//>extinguish C0,2,1
//+,2
//>show-board
//x,x,x,+,x
//+,x,*,x,+
//x,+,x,*,x
//+,x,x,x,+
//x,+,x,+,x
//>turn
//D
//>show-player
//D,0
//D0,3,3,1,3
//>turn
//B
//>fire-to-roll 5
//OK
//>show-board 
//x,x,x,*,x
//*,x,*,x,*
//x,*,+,*,x
//*,x,x,+,*
//x,*,x,*,x
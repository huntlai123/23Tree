import static org.junit.Assert.*;

import org.junit.Test;

public class TwoThreeTreeTest
{
    @Test
    public void test()
    {
       TwoThreeTree t = new TwoThreeTree();
       t.insert(1);
       t.insert(9);
       t.insert(15);
       t.insert(13);
       t.insert(20);
       t.insert(7);
       t.insert(4);
       t.insert(23);
       t.insert(0);
       t.insert(4);
       t.insert(8);
       t.insert(13);
       t.insert(19);
       t.insert(15);
       t.insert(11);
       t.insert(215);
       t.insert(-3);
       t.insert(2);
       t.insert(10);
       t.insert(16);
       t.insert(32);
       t.insert(5);

       String expected = "9";
       assertEquals(expected, t.search(9));
       expected = "4";
       assertEquals(expected, t.search(4));
       expected = "15";
       assertEquals(expected, t.search(15));

       expected = "13";
       assertEquals(expected, t.search(12));
       assertEquals(expected, t.search(13));
       assertEquals(expected, t.search(14));
       
       expected = "16 19";
       assertEquals(expected, t.search(19));
       
       expected = "20 32";
       assertEquals(expected, t.search(20));
       
       expected = "23";
       assertEquals(expected, t.search(21));

       expected = "1 2";
       assertEquals(expected, t.search(1));
       
       expected = "0";
       assertEquals(expected, t.search(0));

       expected = "5";
       assertEquals(expected, t.search(6));
       
       expected = "7";
       assertEquals(expected, t.search(7));
       
       expected = "8";
       assertEquals(expected, t.search(8));

    }

}

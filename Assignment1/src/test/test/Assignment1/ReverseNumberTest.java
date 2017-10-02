package Assignment1;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class ReverseNumberTest {

    private static ReverseNumber rn = null;

    @BeforeClass
    public static void setupBeforeTests() throws Exception {
        rn = new ReverseNumber();
    }

    /**
     * Tests that ReverseNumber throws an NumberFormatException
     * for a input number whose reversed number value is out of range [-2^31, 2^31-1].
     */
    @Test(expected=NumberFormatException.class)
    public void expectedNumberFormatException() {
        rn.getReverseNumber(-2147483647);
        rn.getReverseNumber(2147483647);
    }

    /**
     * Tests that ReverseNumber throws no NumberFormatException
     * for a input number whose reversed number value is in range [-2^31, 2^31-1].
     */
    @Test
    public void testThrowsNumberFormatException() {

        // test 1111111112
        try {
            rn.getReverseNumber(1111111112);
        } catch (NumberFormatException ex) {
            fail("Threw NumberFormatException for 1111111112 but 1111111112's reversed number 2111111111 is in range [-2^31, 2^31-1]. "
                    + ex);
        }
        // test -1111111112
        try {
            rn.getReverseNumber(-1111111112);
        } catch (NumberFormatException ex) {
            fail("Threw NumberFormatException for -1111111112 but -1111111112's reversed number -2111111111 is in range [-2^31, 2^31-1]. "
                    + ex);
        }
    }

    /** Tests to see that ReverseNumber returns the correct value for negative integer, n=-1 and n=-1234 */
    @Test
    public void testNegativeInteger() {
        assertEquals("getReverseNumber(-1)", -1, rn.getReverseNumber(-1));
        assertEquals("getReverseNumber(-1234567)", -7654321, rn.getReverseNumber(-1234567));
        assertEquals("getReverseNumber(-7654321)", -1234567, rn.getReverseNumber(-7654321));
    }

    /** Tests to see that ReverseNumber returns the correct value for negative integer, n=-1 and n=-1234 */
    @Test
    public void testPositiveInteger() {
        assertEquals("getReverseNumber(-1)", 1, rn.getReverseNumber(1));
        assertEquals("getReverseNumber(1234567)", 7654321, rn.getReverseNumber(1234567));
        assertEquals("getReverseNumber(7654321)", 1234567, rn.getReverseNumber(7654321));
    }

    /** Tests to see that ReverseNumber returns the correct value for n=0 */
    @Test
    public void testZero() {
        assertEquals("getReverseNumber(-1)", 0, rn.getReverseNumber(0));
    }
}

package Assignment1;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class VehicleTest {
    private static Vehicle vehicle = null;

    /**
     * Tests that Vehicle constructor throws an IllegalArgumentException
     * for a direction argument that is neither 1 nor 2.
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedIllegalArgumentException() {
        vehicle = new Vehicle(0, 0);
    }

    /**
     * Tests that Vehicle constructor throws no IllegalArgumentException
     * for a direction argument that is either 1 or 2.
     */
    @Test
    public void testThrowsIllegalArgumentException() {
        //test direction argument for value 1
        try {
            vehicle = new Vehicle(0, 1);
        } catch (IllegalArgumentException ex) {
            fail("Threw IllegalArgumentException for 1 but 1 is legal: "
                    + ex);
        }
        //test direction argument for value 2
        try {
            vehicle = new Vehicle(0, 2);
        } catch (IllegalArgumentException ex) {
            fail("Threw IllegalArgumentException for 2 but 2 is legal: "
                    + ex);
        }
    }

}

package Assignment1;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class VehicleTest {
    private static Vehicle vehicle = null;

    /**
     * Tests that Vehicle constructor throws an IllegalArgumentException for
     * no inputs are provided for the constructor
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedIllegalArgumentExceptionForNoArgument() {
        vehicle = new Vehicle();
    }

    /**
     * Tests that Vehicle constructor throws an IllegalArgumentException for
     * number of inputs provided to the constructor is unexpected.
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedIllegalArgumentExceptionForMissingArgument() {
        vehicle = new Vehicle(10);
    }

    /**
     * Tests that Vehicle constructor throws an IllegalArgumentException for
     * number of inputs provided to the constructor is unexpected.
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedIllegalArgumentExceptionForIncorrectFormat() {
        vehicle = new Vehicle("0", "1");
    }

    /**
     * Tests that Vehicle constructor throws an IllegalArgumentException for
     * inputs provided to the constructor have an incorrect value.
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedIllegalArgumentExceptionForIncorrectValue() {
        vehicle = new Vehicle(0, 0);
    }

    /**
     * Tests that Vehicle constructor throws no IllegalArgumentException
     * for a direction argument that is either 1 or 2.
     */
    @Test
    public void unexpectedIllegalArgumentException() {
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

package Assignment1;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import java.util.*;

public class ScenicRoadTest {
    private static ScenicRoad scenicRoad = null;
    private static Vehicle vehicle_empty = null;
    private static Vehicle vehicleE_1 = null;
    private static Vehicle vehicleE_2 = null;
    private static Vehicle vehicleE_3 = null;
    private static Vehicle vehicleW_1 = null;
    private static Vehicle vehicleW_2 = null;
    private static Vehicle vehicleW_3 = null;

    @BeforeClass
    public static void setupBeforeTests() throws Exception {
        vehicleE_1 = new Vehicle(-7, 1);
        vehicleE_2 = new Vehicle(3, 1);
        vehicleE_3 = new Vehicle(7, 1);
        vehicleW_1 = new Vehicle(-7, 2);
        vehicleW_2 = new Vehicle(3, 2);
        vehicleW_3 = new Vehicle(7, 2);
    }

    @Test
    public void testNoTrafficJam() {
        scenicRoad = new ScenicRoad(5);
        scenicRoad.add(vehicleE_2);
        scenicRoad.add(vehicleE_3);
        scenicRoad.add(vehicleW_2);
        scenicRoad.add(vehicleW_3);
        Iterator<Vehicle> it = scenicRoad.iterator();
        while(it.hasNext()) {
            Vehicle curVehicle = it.next();
            if(curVehicle == vehicleE_3 || curVehicle == vehicleW_3) {
                assertTrue("getContents().isEmpty()", curVehicle.getVelocity() == 7);
            }
        }
    }

    @Test
    public void testHasTrafficJam() {
        scenicRoad = new ScenicRoad(5);
        scenicRoad.add(vehicleE_1);
        scenicRoad.add(vehicleE_2);
        scenicRoad.add(vehicleE_3);
        scenicRoad.add(vehicleW_1);
        scenicRoad.add(vehicleW_2);
        scenicRoad.add(vehicleW_3);
        Iterator<Vehicle> it = scenicRoad.iterator();
        while(it.hasNext()) {
            Vehicle curVehicle = it.next();
            assertTrue("getContents().isEmpty()", curVehicle.getVelocity() <= 5);
        }
    }
}

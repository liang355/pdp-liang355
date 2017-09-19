package Assignment1;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class HighwayTest {
    private static Highway highway = null;
    private static Vehicle vehicle_empty = null;
    private static Vehicle vehicleE_1 = null;
    private static Vehicle vehicleE_2 = null;
    private static Vehicle vehicleE_3 = null;
    private static Vehicle vehicleW_1 = null;
    private static Vehicle vehicleW_2 = null;
    private static Vehicle vehicleW_3 = null;

    @BeforeClass
    public static void setupBeforeTests() throws Exception {
        vehicleE_1 = new Vehicle(-1, 1);
        vehicleE_2 = new Vehicle(0, 1);
        vehicleE_3 = new Vehicle(1, 1);
        vehicleW_1 = new Vehicle(-1, 2);
        vehicleW_2 = new Vehicle(0, 2);
        vehicleW_3 = new Vehicle(1, 2);
    }

    //-----tests for IllegalArgumentException:-----

    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentException_contains() {
        highway = new Highway();
        highway.contains(vehicle_empty);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentException_add() {
        highway = new Highway();
        highway.add(vehicle_empty);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentException_remove() {
        highway = new Highway();
        highway.remove(vehicle_empty);
    }

    //-----Tests for state with no vehicle:-----

    @Test
    public void testEmptyState_getContents() {
        highway = new Highway();
        assertEquals("getContents().isEmpty()", true, highway.getContents().isEmpty());
    }

    @Test(expected=IllegalStateException.class)
    public void testEmptyState_getVelocityEastBound() {
        highway = new Highway();
        highway.getVelocityEastbound();
    }

    @Test(expected=IllegalStateException.class)
    public void testEmptyState_getVelocityWestBound() {
        highway = new Highway();
        highway.getVelocityWestbound();
    }

    @Test
    public void testEmptyState_numberVehiclesEastbound() {
        highway = new Highway();
        assertEquals("numberVehiclesEastbound()", 0, highway.numberVehiclesEastbound());
    }

    @Test
    public void testEmptyState_numberVehiclesWestbound() {
        highway = new Highway();
        assertEquals("numberVehiclesWestbound()", 0, highway.numberVehiclesWestbound());
    }

    //-----Tests for state after adding a new vehicle:-----

    @Test
    public void testAddNewVehicle_getContents() {
        highway = new Highway();
        highway.add(vehicleW_1);
        assertEquals("getContents().isEmpty()", false, highway.getContents().isEmpty());
    }

    @Test(expected=IllegalStateException.class)
    public void testAddNewVehicle_getVelocityEastBound() {
        highway = new Highway();
        highway.getVelocityEastbound();
    }

    @Test
    public void testAddNewVehicle_getVelocityWestBound() {
        highway = new Highway();
        highway.add(vehicleW_1);
        assertEquals("getVelocityWestbound()", -1, highway.getVelocityWestbound(), 0);
    }

    @Test
    public void testAddNewVehicle_numberVehiclesEastbound() {
        highway = new Highway();
        highway.add(vehicleW_1);
        assertEquals("numberVehiclesEastbound()", 0, highway.numberVehiclesEastbound());
    }

    @Test
    public void testAddNewVehicle_numberVehiclesWestbound() {
        highway = new Highway();
        highway.add(vehicleW_1);
        assertEquals("numberVehiclesWestbound()", 1, highway.numberVehiclesWestbound());
    }

    //-----tests after multiple add() and remove():-----

    @Test
    public void testAddAllVehiclesThenRemoveSome_getContents() {
        highway = new Highway();
        highway.add(vehicleE_1);
        highway.add(vehicleE_2);
        highway.add(vehicleE_3);
        highway.add(vehicleW_1);
        highway.add(vehicleW_2);
        highway.add(vehicleW_3);
        assertEquals("getContents().isEmpty()", 6, highway.getContents().size());
        highway.remove(vehicleE_1);
        highway.remove(vehicleW_1);
        assertEquals("getContents().isEmpty()", 4, highway.getContents().size());
    }

    @Test
    public void testAddAllVehicles_getVelocityEastBound() {
        highway = new Highway();
        highway.add(vehicleE_1);
        highway.add(vehicleE_2);
        highway.add(vehicleE_3);
        highway.add(vehicleW_1);
        highway.add(vehicleW_2);
        highway.add(vehicleW_3);
        assertEquals("getVelocityWestbound()", -1, highway.getVelocityEastbound(), 0);
        highway.remove(vehicleE_1);
        highway.remove(vehicleW_1);
        assertEquals("getVelocityWestbound()", 0, highway.getVelocityEastbound(), 0);
    }

    @Test
    public void testAddAllVehicles_getVelocityWestBound() {
        highway = new Highway();
        highway.add(vehicleE_1);
        highway.add(vehicleE_2);
        highway.add(vehicleE_3);
        highway.add(vehicleW_1);
        highway.add(vehicleW_2);
        highway.add(vehicleW_3);
        assertEquals("getVelocityWestbound()", -1, highway.getVelocityWestbound(), 0);
        highway.remove(vehicleE_1);
        highway.remove(vehicleW_1);
        assertEquals("getVelocityWestbound()", 0, highway.getVelocityWestbound(), 0);
    }

    @Test
    public void testAddAllVehicles_numberVehiclesEastbound() {
        highway = new Highway();
        highway.add(vehicleE_1);
        highway.add(vehicleE_2);
        highway.add(vehicleE_3);
        highway.add(vehicleW_1);
        highway.add(vehicleW_2);
        highway.add(vehicleW_3);
        assertEquals("numberVehiclesEastbound()", 3, highway.numberVehiclesEastbound());
        highway.remove(vehicleE_1);
        highway.remove(vehicleW_1);
        assertEquals("numberVehiclesEastbound()", 2, highway.numberVehiclesEastbound());
    }

    @Test
    public void testAddAllVehicles_numberVehiclesWestbound() {
        highway = new Highway();
        highway.add(vehicleE_1);
        highway.add(vehicleE_2);
        highway.add(vehicleE_3);
        highway.add(vehicleW_1);
        highway.add(vehicleW_2);
        highway.add(vehicleW_3);
        assertEquals("numberVehiclesWestbound()", 3, highway.numberVehiclesWestbound());
        highway.remove(vehicleE_1);
        highway.remove(vehicleW_1);
        assertEquals("numberVehiclesWestbound()", 2, highway.numberVehiclesWestbound());
    }

    //-----other corner tests:-----

    @Test
    public void testAddDuplicateVehicle() {
        highway = new Highway();
        highway.add(vehicleE_1);
        assertEquals("add(vehicleE_1)", false, highway.add(vehicleE_1));
    }
    @Test
    public void testRemoveNonExistingVehicle() {
        highway = new Highway();
        highway.add(vehicleE_1);
        assertEquals("remove(vehicleE_2)", false, highway.remove(vehicleE_2));
    }


//    @Test
//    public void testStateForAddNewVehicle() {
//        highway = new Highway();
//        assertEquals("add(vehicleE_1)", true, highway.add(vehicleE_1));
//        assertEquals("add(vehicleE_1)", true, highway.add(vehicleE_1));
//        assertEquals("add(vehicleE_1)", false, highway.add(vehicleE_1));
//    }
//
//    @Test
//    public void testStateForAddDuplicateVehicle() {
//        highway = new Highway();
//        assertEquals("add(vehicleE_1)", true, highway.add(vehicleE_1));
//        assertEquals("add(vehicleE_1)", true, highway.add(vehicleE_1));
//        assertEquals("add(vehicleE_1)", false, highway.add(vehicleE_1));
//    }
}

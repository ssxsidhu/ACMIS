package comp3350.acmis.objects;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LocationTest extends TestCase {

    private Location myLoc;
    private Location loc2;
    private Location loc3;

    @Before
    private void setup() {
        myLoc = new Location("Toronto", "Canada", "YYZ");
        loc2 = new Location("Winnipeg", "Canada", "YWG");
        loc3 = new Location("Vancouver", "Canada", "YVR");
    }

    @After
    private void teardown() {
        myLoc = null;
        loc2 = null;
        loc3 = null;
    }

    @Test
    public void testValidCity() {
        System.out.println("Starting testLocation: valid city");

        Location loc;

        //null city
        try {
            loc = new Location(null, "Canada", "YWG");
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        //empty city
        try {
            loc = new Location("", "Canada", "YWG");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        //check only spaces
        try {
            loc = new Location("      ", "Canada", "YWG");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        loc = new Location("Winnipeg", "Canada", "YWG");
        assertEquals("Winnipeg", loc.getCity());

        loc = new Location(" Winnipeg   ", "Canada", "YWG");
        assertEquals("Winnipeg", loc.getCity());

        System.out.println("Finished testLocation: valid city");
    }

    @Test
    public void testValidCountry() {
        System.out.println("Starting testLocation: valid country");

        Location loc;

        //null country
        try {
            loc = new Location("Winnipeg", null, "YWG");
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        //empty country
        try {
            loc = new Location("Winnipeg", "", "YWG");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        //check only spaces
        try {
            loc = new Location("Winnipeg", "    ", "YWG");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        loc = new Location("Winnipeg", "Canada", "YWG");
        assertEquals("Canada", loc.getCountry());

        loc = new Location(" Winnipeg   ", "    Canada  ", "YWG");
        assertEquals("Canada", loc.getCountry());

        System.out.println("Finished testLocation: valid country");
    }

    @Test
    public void testValidAirport() {
        System.out.println("Starting testLocation: valid airport");

        Location loc;

        //null airport
        try {
            loc = new Location("Winnipeg", "Canada", null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        //empty airport
        try {
            loc = new Location("Winnipeg", "Canada", "");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        //check only spaces
        try {
            loc = new Location("Winnipeg", "Canada", "     ");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        loc = new Location("Winnipeg", "Canada", "YWG");
        assertEquals("YWG", loc.getAirport());

        loc = new Location(" Winnipeg   ", "Canada", "   YWG       ");
        assertEquals("YWG", loc.getAirport());

        System.out.println("Finished testLocation: valid airport");

    }

    @Test
    public void testIncomingLocs() {
        System.out.println("Starting testLocation: incoming flights");

        setup();

        //should be 0 at start
        assertEquals(0, myLoc.getLocsIncomingFlights().size());

        //add some locations
        myLoc.addLocationIncoming(loc2);
        myLoc.addLocationIncoming(loc3);

        assertEquals(2, myLoc.getLocsIncomingFlights().size());

        assertTrue(myLoc.getLocsIncomingFlights().contains(loc2));
        assertTrue(myLoc.getLocsIncomingFlights().contains(loc3));

        try {
            myLoc.addLocationIncoming(null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused){}

        System.out.println("Finished testLocation: incoming flights");

    }

    @Test
    public void testOutgoingLocs() {
        System.out.println("Starting testLocation: outgoing flights");

        setup();

//        Location myLoc = new Location("Toronto", "Canada", "YYZ");
//        Location outgoing1 = new Location("Winnipeg", "Canada", "YWG");
//        Location outgoing2 = new Location("Vancouver", "Canada", "YVR");

        //should be 0 at start
        assertEquals(0, myLoc.getLocsOutgoingFLights().size());

        //add some locations
        myLoc.addLocationOutgoing(loc2);
        myLoc.addLocationOutgoing(loc3);

        assertEquals(2, myLoc.getLocsOutgoingFLights().size());

        assertTrue(myLoc.getLocsOutgoingFLights().contains(loc2));
        assertTrue(myLoc.getLocsOutgoingFLights().contains(loc3));

        try {
            myLoc.addLocationOutgoing(null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused){}

        System.out.println("Finished testLocation: outgoing flights");
    }
}

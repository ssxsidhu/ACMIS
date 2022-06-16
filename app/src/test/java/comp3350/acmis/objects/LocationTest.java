package comp3350.acmis.objects;

import junit.framework.TestCase;
import org.junit.Test;

public class LocationTest extends TestCase {

    @Test
    public void testCity() {
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
    }

    @Test
    public void testCountry() {
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
    }

    @Test
    public void testAirport() {
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
    }

    @Test
    public void testIncomingLocs() {
        Location myLoc = new Location("Toronto", "Canada", "YYZ");
        Location incoming1 = new Location("Winnipeg", "Canada", "YWG");
        Location incoming2 = new Location("Vancouver", "Canada", "YVR");

        //should be 0 at start
        assertEquals(0, myLoc.getLocsIncomingFlights().size());

        //add some locations
        myLoc.addLocationIncoming(incoming1);
        myLoc.addLocationIncoming(incoming2);

        assertEquals(2, myLoc.getLocsIncomingFlights().size());

        assertTrue(myLoc.getLocsIncomingFlights().contains(incoming1));
        assertTrue(myLoc.getLocsIncomingFlights().contains(incoming2));

        try {
            myLoc.addLocationIncoming(null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused){}
    }

    @Test
    public void testOutgoingLocs() {
        Location myLoc = new Location("Toronto", "Canada", "YYZ");
        Location outgoing1 = new Location("Winnipeg", "Canada", "YWG");
        Location outgoing2 = new Location("Vancouver", "Canada", "YVR");

        //should be 0 at start
        assertEquals(0, myLoc.getLocsOutgoingFLights().size());

        //add some locations
        myLoc.addLocationOutgoing(outgoing1);
        myLoc.addLocationOutgoing(outgoing2);

        assertEquals(2, myLoc.getLocsOutgoingFLights().size());

        assertTrue(myLoc.getLocsOutgoingFLights().contains(outgoing1));
        assertTrue(myLoc.getLocsOutgoingFLights().contains(outgoing2));

        try {
            myLoc.addLocationOutgoing(null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused){}
    }
}

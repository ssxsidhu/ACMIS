package comp3350.acmis.objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;

public class LocationTest {

    private Location myLoc;
    private Location loc2;
    private Location loc3;


    @Before
    public void setup() {

        myLoc = new Location("Toronto", ZoneId.of("America/Toronto"), "Canada", "YYZ");
        loc2 = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        loc3 = new Location("Vancouver", ZoneId.of("America/Vancouver"), "Canada", "YVR");
    }

    @After
    public void teardown() {
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
            loc = new Location(null, ZoneId.of("America/Winnipeg"), "Canada", "YWG");
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //empty city
        try {
            loc = new Location("", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //check only spaces
        try {
            loc = new Location("      ", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        loc = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        Assert.assertEquals("Winnipeg", loc.getCity());

        loc = new Location(" Winnipeg   ", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        Assert.assertEquals("Winnipeg", loc.getCity());

        System.out.println("Finished testLocation: valid city");
    }

    @Test
    public void testValidCountry() {
        System.out.println("Starting testLocation: valid country");

        Location loc;

        //null country
        try {
            loc = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), null, "YWG");
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //empty country
        try {
            loc = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "", "YWG");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //check only spaces
        try {
            loc = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "    ", "YWG");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        loc = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        Assert.assertEquals("Canada", loc.getCountry());

        loc = new Location(" Winnipeg   ", ZoneId.of("America/Winnipeg"), "    Canada  ", "YWG");
        Assert.assertEquals("Canada", loc.getCountry());

        System.out.println("Finished testLocation: valid country");
    }

    @Test
    public void testValidAirport() {

        System.out.println("Starting testLocation: valid airport");

        Location loc;

        //null airport
        try {
            loc = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //empty airport
        try {
            loc = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //check only spaces
        try {
            loc = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "     ");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        loc = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        Assert.assertEquals("YWG", loc.getAirport());

        loc = new Location(" Winnipeg   ", ZoneId.of("America/Winnipeg"), "Canada", "   YWG       ");
        Assert.assertEquals("YWG", loc.getAirport());

        System.out.println("Finished testLocation: valid airport");

    }


}

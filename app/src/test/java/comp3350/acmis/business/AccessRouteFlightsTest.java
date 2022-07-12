package comp3350.acmis.business;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import comp3350.acmis.application.Main;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class AccessRouteFlightsTest {
    AccessRouteFlights test;
    Location winnipeg, montreal, toronto;
    Flight torToWn, winToMn;
    Route testingR;

    @Before
    public void setUp() {
        Main.startUp();
        winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        montreal = new Location("Montreal", ZoneId.of("America/Montreal"), "Canada", "YUL");
        toronto = new Location("Toronto", ZoneId.of("America/Toronto"), "Canada", "YYZ");

        torToWn = new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 11, 15, 16, 0, 0, 0, toronto.getZoneName()), 250, 2.6, 750);
        winToMn = new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 11, 15, 20, 00, 0, 0, winnipeg.getZoneName()), 250, 2.5, 500);

        testingR = new Route();
        testingR.addToRoute(torToWn);
        testingR.addToRoute(winToMn);

        test = new AccessRouteFlights(testingR);
    }

    @After
    public void tearDown() {
        Main.shutDown();
    }

    @Test
    public void testNullRoute() {
        //null route passed to the access route flights
        try {
            test = new AccessRouteFlights(null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }
    }

    @Test
    public void testCalculateLayover() {
        //testing the time of half between 2 flights
        setUp();
        //test for null inputs
        try {
            test.calculateLayover(torToWn, null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }
        try {
            test.calculateLayover(null, null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }
        try {
            test.calculateLayover(null, winToMn);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }
        long millis = test.calculateLayover(torToWn, winToMn);
        //calculating valid time
        Assert.assertEquals("2h 24m", test.toStringDuration(millis));
        tearDown();
    }

    @Test
    public void testMillisToHrMin() {
        //testing wrong values
        try {
            test.toStringDuration(-1);
        } catch (IllegalArgumentException unused) {
        }
        try {
            test.toStringDuration(-100000);
        } catch (IllegalArgumentException unused) {
        }

        //valid arguments
        Assert.assertEquals("2h 30m", test.toStringDuration(9000000));
    }

    @Test
    public void testTotalCost() {
        setUp();
        //500+750
        Assert.assertEquals(1250, test.getRouteTotalCost());
        tearDown();
    }

    @Test
    public void testTotalDurationRoute() {
        setUp();
        //2h 30 + 2h 24 m (layover) + 2h 36m
        //flight 1 + layover + flight 2
        Assert.assertEquals("7h 30m", test.getRouteTotalDuration());
    }


}
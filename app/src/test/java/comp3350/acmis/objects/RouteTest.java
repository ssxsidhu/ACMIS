package comp3350.acmis.objects;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class RouteTest {
    Location winnipeg, toronto;
    Flight testFlight2, testFlight1, testFlight;
    Route testRoute;

    @Before
    public void setUp() {
        winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        toronto = new Location("Toronto", ZoneId.of("America/Winnipeg"), "Canada", "YYZ");
        testFlight = new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, winnipeg.getZoneName()), 250, 2.5, 500);
        testRoute = new Route(testFlight);
    }

    @After
    public void tearDown() {
        winnipeg = null;
        toronto = null;
        testFlight = null;
    }

    @Test
    public void testDirectFlight() {
        //null flight inserted in the route's list of flights
        try {
            testRoute = new Route(null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //valid flight added to the route
        setUp();
        Assert.assertFalse(testRoute.isEmpty());
        Assert.assertEquals(testFlight, testRoute.getRoute().get(0));

        tearDown();
    }


    @Test
    public void testAddingFlightsToRoute() {
        Route testRoute = new Route();
        //adding a null flight?
        try {
            testRoute.addToRoute(null);
            Assert.fail("Expected null pointer exception");
        } catch (NullPointerException unused) {
        }
        //empty route list
        Assert.assertTrue(testRoute.isEmpty());
        //if there is an actual flight added to the route
        testRoute.addToRoute(testFlight);
        //only one flight in the route list
        Assert.assertFalse(testRoute.isEmpty());
        //adding more flights
        testFlight1 = new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 3, 21, 13, 30, 0, 0, toronto.getZoneName()), 450, 2.5, 500);
        testFlight2 = new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 9, 1, 1, 30, 0, 0, winnipeg.getZoneName()), 750, 7.5, 789);
        testRoute.addToRoute(testFlight1);
        testRoute.addToRoute(testFlight2);

        //route list contains 3 connecting flights
        Assert.assertEquals(3, testRoute.getRoute().size());
    }

    @Test
    public void testFlightIdsInRoute() {
        Route testRoute = new Route();
        testFlight1 = new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 3, 21, 13, 30, 0, 0, toronto.getZoneName()), 450, 2.5, 500);
        testFlight2 = new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 9, 1, 1, 30, 0, 0, winnipeg.getZoneName()), 750, 7.5, 789);

        //route contains one flight , getting the Id
        Assert.assertEquals("", testRoute.getFlightsCSV());
        testRoute.addToRoute(testFlight1);
        testRoute.addToRoute(testFlight2);
        //flight sequence starts at 1
        Assert.assertEquals("2,3", testRoute.getFlightsCSV());
        tearDown();
    }


}
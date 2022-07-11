package comp3350.acmis.objects;


import org.junit.Assert;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class RouteTest {

    @Test
    public void testAddToRoute() {
        Location winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        Location toronto = new Location("Toronto", ZoneId.of("America/Winnipeg"), "Canada", "YYZ");
        Flight winToTor = new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, winnipeg.getZoneName()), 250, 2.5, 500);
        Route testRoute = new Route();

        try {
            testRoute.addToRoute(null);
            Assert.fail("Expected null pointer exception");
        } catch (NullPointerException unused) {
        }

        //if there is an actual flight added to the route
        Assert.assertNull(testRoute.addToRoute(winToTor));
    }

    @Test
    public void testRemoveFromRoute() {
        Location winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        Location toronto = new Location("Toronto", ZoneId.of("America/Winnipeg"), "Canada", "YYZ");
        Flight winToTor = new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, winnipeg.getZoneName()), 250, 2.5, 500);
        Route testRoute = new Route();

        try {
            testRoute.removeFromRoute(winToTor);
            Assert.fail("No flight to remove from the route");
        } catch (NullPointerException unused) {
        }

        testRoute.addToRoute(winToTor);
        Assert.assertNull(testRoute.removeFromRoute(winToTor));

    }

    @Test
    public void testIsEmpty() {
        Route testRoute = new Route();
        Assert.assertTrue(testRoute.isEmpty());
    }

    @Test
    public void testGetRoute() {
        Location winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        Location toronto = new Location("Toronto", ZoneId.of("America/Winnipeg"), "Canada", "YYZ");
        Flight winToTor = new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, winnipeg.getZoneName()), 250, 2.5, 500);
        Route testRoute = new Route();
        Assert.assertNull(testRoute.addToRoute(winToTor));

        Assert.assertNotNull(testRoute.getRoute());
    }


}
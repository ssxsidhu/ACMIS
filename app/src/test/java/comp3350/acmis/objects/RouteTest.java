package comp3350.acmis.objects;


import junit.framework.TestCase;

import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class RouteTest extends TestCase {

    @Test
    public void testAddToRoute() {
        Location winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada","YWG");
        Location toronto = new Location("Toronto", ZoneId.of("America/Winnipeg"), "Canada","YYZ");
        Flight winToTor = new Flight(winnipeg,toronto, ZonedDateTime.of(2022,6,11,7,30,0,0,winnipeg.getZoneName()), 250, 2.5, 500);
        Route testRoute = new Route();

        try{
            testRoute.addToRoute(null);
            fail("Expected null pointer exception");
        }catch(NullPointerException unused){}

        //if there is an actual flight added to the route
        assertNull(testRoute.addToRoute(winToTor));
    }

    @Test
    public void testRemoveFromRoute() {
        Location winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada","YWG");
        Location toronto = new Location("Toronto", ZoneId.of("America/Winnipeg"), "Canada","YYZ");
        Flight winToTor = new Flight(winnipeg,toronto, ZonedDateTime.of(2022,6,11,7,30,0,0,winnipeg.getZoneName()), 250, 2.5, 500);
        Route testRoute = new Route();

        try{
            testRoute.removeFromRoute(winToTor);
            fail("No flight to remove from the route");
        }catch(NullPointerException unused){}

        testRoute.addToRoute(winToTor);
        assertNull(testRoute.removeFromRoute(winToTor));

    }

    @Test
    public void testIsEmpty() {
        Route testRoute = new Route();
        assertTrue(testRoute.isEmpty());
    }

    @Test
    public void testGetRoute() {
        Location winnipeg = new Location("Winnipeg",ZoneId.of("America/Winnipeg") , "Canada","YWG");
        Location toronto = new Location("Toronto", ZoneId.of("America/Winnipeg"), "Canada","YYZ");
        Flight winToTor = new Flight(winnipeg,toronto, ZonedDateTime.of(2022,6,11,7,30,0,0,winnipeg.getZoneName()), 250, 2.5, 500);
        Route testRoute = new Route();
        assertNull(testRoute.addToRoute(winToTor));

        assertNotNull(testRoute.getRoute());
    }


}
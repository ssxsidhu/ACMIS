package comp3350.acmis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.acmis.business.AccessBookingsTest;
import comp3350.acmis.business.AccessLocationsTest;
import comp3350.acmis.business.AccessRouteFlightsTest;
import comp3350.acmis.business.BookingManagerTest;
import comp3350.acmis.objects.BookingTest;
import comp3350.acmis.objects.FlightTest;
import comp3350.acmis.objects.LocationTest;
import comp3350.acmis.objects.RouteTest;
import comp3350.acmis.objects.UserTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        UserTest.class,
        LocationTest.class,
        BookingTest.class,
        FlightTest.class,
        RouteTest.class,
        AccessBookingsTest.class,
        BookingManagerTest.class,
        AccessLocationsTest.class,
        AccessRouteFlightsTest.class
})

public class TestJunitSuite {
}

/**
 * Collection of all the tests in the business class.
 */

package comp3350.acmis.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)

@Suite.SuiteClasses({
        AccessBookingsTest.class,
        BookingManagerTest.class,
        AccessLocationsTest.class,
        UseRouteFlightsTest.class,
        RouteManagerTest.class,
        RouteSortTest.class,
        RouteManagerTest.class

})


public class BusinessTests {


}

/**
 * Collection of all the object class tests.
 * test suite for objects
 */

package comp3350.acmis.objects;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
        UserTest.class,
        LocationTest.class,
        BookingTest.class,
        FlightTest.class,
        RouteTest.class,
        AdjacencyListTest.class,
        GraphTest.class
})


public class ObjectTests {
}

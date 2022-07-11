package comp3350.acmis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
        RouteTest.class
})

public class TestJunitSuite {
}

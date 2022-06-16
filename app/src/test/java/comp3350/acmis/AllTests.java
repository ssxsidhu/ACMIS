package comp3350.acmis;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import comp3350.acmis.business.DateFormatterTest;
import comp3350.acmis.business.BookingManagerTest;
import comp3350.acmis.objects.FlightTest;
import comp3350.acmis.objects.RouteTest;
import comp3350.acmis.objects.UserTest;
import comp3350.acmis.objects.LocationTest;
import comp3350.acmis.objects.BookingTest;


public class AllTests extends TestCase {
    public static TestSuite suite;

    public static Test suite() {
        System.out.println("Launching Test Suite.");
        suite = new TestSuite("All tests");
        tObjects();
        tBusiness();
        return suite;
    }

    private static void tObjects() {
        suite.addTestSuite(UserTest.class);
        suite.addTestSuite(LocationTest.class);
        suite.addTestSuite(BookingTest.class);
        suite.addTestSuite(FlightTest.class);
        suite.addTestSuite(RouteTest.class);
    }

    private static void tBusiness() {
        suite.addTestSuite(DateFormatterTest.class);
        suite.addTestSuite(BookingManagerTest.class);
    }
}

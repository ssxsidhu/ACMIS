package comp3350.acmis;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.acmis.acceptance.CancelBookingTest;
import comp3350.acmis.acceptance.AddNumPassengerTest;
import comp3350.acmis.acceptance.CheckUpcomingFlightsTest;
import comp3350.acmis.acceptance.OneWayFlightTest;
import comp3350.acmis.acceptance.RoundTripFlightTest;
import comp3350.acmis.acceptance.SortSearchResultsTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddNumPassengerTest.class,
        OneWayFlightTest.class,
        RoundTripFlightTest.class,
        CheckUpcomingFlightsTest.class,
        SortSearchResultsTest.class,
        CancelBookingTest.class})

public class RunAcceptanceTests {
    public RunAcceptanceTests() {
        System.out.println("Acceptance tests");
    }
}

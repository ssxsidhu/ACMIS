package comp3350.acmis.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;

import comp3350.acmis.application.Main;
import comp3350.acmis.persistence.DataAccessStub;
import comp3350.acmis.objects.*;


public class BookingManagerTest extends TestCase {

    public static DataAccessStub data;
    public static BookingManager test;

    private static Location loc1;
    private static Location loc2;
    private static Location loc3;
    private static Location loc4;
    private static Location loc5;

    private static User user1;
    private static User user2;
    private static User user3;

    private static Flight flight1;
    private static Flight flight2;
    private static Flight flight3;

    private static ArrayList<Route> temp = new ArrayList<>();

    private final static String NO_FLIGHTS = "no_flights_found";

    @Before
    private void setup() {
        Main.startUp();
        System.out.println("Create Test Environment. Creating Test Data Base and Booking Manager...");

        test = new BookingManager("DataAccessStubTest.java");
        data = new DataAccessStub();
        data.open(Main.dbName);

        System.out.println("Open Data Base For Writing. We will add 3 users, 5 locations and 3 flights.");
        System.out.println("Every flight will go from one location to its immediate next location. The last location has no incoming flights and is inacessible");

        // Create a few objects for our Test, This will be a one time event.
        user1 = new User("1","ONE", User.Gender.MALE,"one1","bleh","1@gmail.com","1234567890");
        user2 = new User("2","TWO", User.Gender.FEMALE,"two2","bleh","2@gmail.com","1234567890");
        user3 = new User("3","THREE", User.Gender.FEMALE,"three3","bleh","3@gmail.com","1234567890");

        // Create 5 locations for out Test purpose. Add them to a list as well.
        loc1 = new Location("1", ZoneId.of("America/Toronto"), "1","1");
        loc2 = new Location("2", ZoneId.of("America/Toronto"), "2","2");
        loc3 = new Location("3", ZoneId.of("America/Toronto"), "3","3");
        loc4 = new Location("4", ZoneId.of("America/Toronto"), "4","4");
        loc5 = new Location("5", ZoneId.of("America/Toronto"), "5","5");

        // Create just 3 flights. Remember, we ONLY HAVE TO TEST. NOT BUILD THE APP. ADD THESE TO A LIST
        // Each flight will go to next number. THERE FOR LOCATION 5 IS NOT REACHABLE. LOCATION 5 HAS NO FLIGHTS.
        flight1 = new Flight(loc1,loc2, ZonedDateTime.of(2022,6,11,7,30,0,0,loc1.getZoneName()), 250, 2.5, 500);
        flight2 = new Flight(loc2,loc3, ZonedDateTime.of(2022,6,11,7,30,0,0,loc2.getZoneName()), 250, 2.5, 500);
        flight3 = new Flight(loc3,loc4,ZonedDateTime.of(2022,6,11,7,30,0,0,loc3.getZoneName()), 250, 2.5, 500);
        System.out.println("Finished Writing to Database. Executing Unit Tests..");

        temp.add(new Route(flight1));

        data.insertFlight(flight1);

    }

    @Test
    public void testSearchRoute() {
        setup();

        assertEquals(NO_FLIGHTS,test.searchRoute(loc1,loc2,temp));
        assertEquals(NO_FLIGHTS,test.searchRoute(loc2,loc3,temp));
        assertEquals(NO_FLIGHTS,test.searchRoute(loc3,loc4,temp));

        // Test for One Stop Routes
        System.out.println("Test for One Stop Routes");
        assertEquals(NO_FLIGHTS,test.searchRoute(loc1,loc3,temp));
        assertEquals(NO_FLIGHTS,test.searchRoute(loc2,loc4,temp));

        // Test for NON REACHABLE CITY OR INVALID ROUTE
        System.out.println("Test for Unreachable Route");
        assertEquals(NO_FLIGHTS,test.searchRoute(loc1,loc5,temp));
        assertEquals(NO_FLIGHTS,test.searchRoute(loc2,loc5,temp));
        assertEquals(NO_FLIGHTS,test.searchRoute(loc3,loc5,temp));
        assertEquals(NO_FLIGHTS,test.searchRoute(loc4,loc5,temp));
    }


    @Test
    public void testCreateBooking() {
        setup();

        // Valid User should Book
        System.out.println("Create a Booking from the List of Valid Routes and take the first route in the database. We dont care what it is since its valid");
        assertEquals("no object found", test.createBooking("one1",temp.get(0)));
        assertEquals("no object found", test.createBooking("nononon",temp.get(0)));

        // Valid User should NOT HAVE SAME EXISTING BOOKING
        System.out.println("Test if we are not already having that booking");
        assertEquals("no object found", test.createBooking("one1",temp.get(0)));
        assertEquals("no object found", test.createBooking("one1",temp.get(0)));

        System.out.println("All CreateBooking Tests Passed !");
    }
}

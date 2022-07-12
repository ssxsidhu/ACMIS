package comp3350.acmis.business;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;

import comp3350.acmis.application.Main;
import comp3350.acmis.persistence.DataAccessStub;
import comp3350.acmis.objects.*;


public class BookingManagerTest {

    public static BookingManager test;

    private static Location loc1;
    private static Location loc2;
    private static Location loc3;
    private static Location loc4;
    private static Location loc5;

    ArrayList<User> allUsers;
    ArrayList<Flight> allFlights;
    ArrayList<Location> allLocations ;
    ArrayList<Booking> allBookings;

    private static User user1,user2,user3;

    private static Flight flight1;
    private static Flight flight2;
    private static Flight flight3;

    private static ArrayList<Route> temp;

    private final static String NO_FLIGHTS = "no_flights_found";

    @Before
    public void setup() {
        Main.startUp();
        System.out.println("Create Test Environment. Creating Test Data Base and Booking Manager...");

        test = new BookingManager("DataAccessStubTest.java");

        allUsers = new ArrayList<>();
        allFlights = new ArrayList<>();
        allLocations = new ArrayList<>();
        allBookings = new ArrayList<>();


        System.out.println("Open Data Base For Writing. We will add 3 users, 5 locations and 3 flights.");
        System.out.println("Every flight will go from one location to its immediate next location. The last location has no incoming flights and is inacessible");

        // Create a few objects for our Test, This will be a one time event.
        user1 = new User("1","ONE", User.Gender.MALE,"one1","bleh","1@gmail.com","1234567890");
        user2 = new User("2","TWO", User.Gender.FEMALE,"two2","wooh","2@gmail.com","0987654321");
        user3 = new User("3","THREE", User.Gender.MALE,"three3","lala","3@gmail.com","9876514310");
        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);


        // Create 5 locations for out Test purpose. Add them to a list as well.
        loc1 = new Location("1", ZoneId.of("America/Toronto"), "1","1");
        loc2 = new Location("2", ZoneId.of("America/Toronto"), "2","2");
        loc3 = new Location("3", ZoneId.of("America/Toronto"), "3","3");
        loc4 = new Location("4", ZoneId.of("America/Toronto"), "4","4");
        loc5 = new Location("5", ZoneId.of("America/Toronto"), "5","5");
        allLocations.add(loc1);
        allLocations.add(loc2);
        allLocations.add(loc3);
        allLocations.add(loc4);
        allLocations.add(loc5);


        // Create just 3 flights. Remember, we ONLY HAVE TO TEST. NOT BUILD THE APP. ADD THESE TO A LIST
        // Each flight will go to next number. THERE FOR LOCATION 5 IS NOT REACHABLE. LOCATION 5 HAS NO FLIGHTS.
        flight1 = new Flight(loc1,loc2, ZonedDateTime.of(2022,6,11,7,30,0,0,loc1.getZoneName()), 250, 2.5, 500);
        flight2 = new Flight(loc2,loc3, ZonedDateTime.of(2022,6,11,7,30,0,0,loc2.getZoneName()), 250, 2.5, 500);
        flight3 = new Flight(loc3,loc4,ZonedDateTime.of(2022,6,11,7,30,0,0,loc3.getZoneName()), 250, 2.5, 500);

        allFlights.add(flight1);
        allFlights.add(flight2);
        allFlights.add(flight3);

        System.out.println("Finished Writing to Database. Executing Unit Tests..");



    }

    @After
    public void tearDown() {
        Main.shutDown();
    }

    @Test
    public void testNullValues(){
        //null username of a user.
        setup();
        try {
            test.createBooking(null,new Route(),new Route(flight1),10);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }
        //null route one way route
        try {
            test.createBooking("one1",null,new Route(flight1),10);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        tearDown();
    }

    @Test
    public void testValidCreateBooking(){
        setup();
        ArrayList<Booking> booked = new ArrayList<>();
        test.createBooking("one1",new Route(flight2), new Route(flight3),2);
        test.getData().getUserBookings(user1,booked);

        Assert.assertEquals(2,booked.size());
    }





}

package comp3350.acmis.objects;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.persistence.DataAccessStub;
import comp3350.acmis.persistence.DataAccessStubTest;
import comp3350.acmis.persistence.TestStubData;

public class BookingManagerTest {

    public static TestStubData data;
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


    @BeforeClass
    public static void beforeClass() throws Exception {

        // TEST THIS. BookingManager will Test its ops on the test Database below

        System.out.println("Create Test Environment. Creating Test Data Base and Booking Manager...");

        test = new BookingManager("DataAccessStubTest.java");
        data = new TestStubData();
        data.open();

        System.out.println("Open Data Base For Writing. We will add 3 users, 5 locations and 3 flights.");
        System.out.println("Every flight will go from one location to its immediate next location. The last location has no incoming flights and is inacessible");

        // Create a few objects for our Test, This will be a one time event.
        user1 = new User("1","ONE", User.Gender.MALE,"one1","bleh","1@gmail.com","0001");
        user2 = new User("2","TWO", User.Gender.FEMALE,"two2","bleh","2@gmail.com","0002");
        user3 = new User("3","THREE", User.Gender.FEMALE,"three3","bleh","3@gmail.com","0003");

        // Create 5 locations for out Test purpose. Add them to a list as well.
        loc1 = new Location("1","1","1");
        loc2 = new Location("2","2","2");
        loc3 = new Location("3","3","3");
        loc4 = new Location("4","4","4");
        loc5 = new Location("5","5","5");

        // Create just 3 flights. Remember, we ONLY HAVE TO TEST. NOT BUILD THE APP. ADD THESE TO A LIST
        // Each flight will go to next number. THERE FOR LOCATION 5 IS NOT REACHABLE. LOCATION 5 HAS NO FLIGHTS.
        flight1 = new Flight(loc1,loc2,"","","","");
        flight2 = new Flight(loc2,loc3,"","","","");
        flight3 = new Flight(loc3,loc4,"","","","");

        // Insert all the other objects in our data base. We DONT CARE how DATABASE STORES THEM.
        data.insertFlight(flight1);
        data.insertFlight(flight2);
        data.insertFlight(flight3);
        data.insertUser(user1);
        data.insertUser(user2);
        data.insertUser(user3);
        data.insertLocation(loc1);
        data.insertLocation(loc1);
        data.insertLocation(loc1);
        data.insertLocation(loc1);
        data.insertLocation(loc1);


        data.close();
        System.out.println("Finished Writing to Database. Executing Unit Tests..");
    }

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSearchRoute()throws Exception{

        System.out.println("Testing Search Route...");

        // Test For Travel to Same City
        System.out.println("Test for Travelling to Same City");
        assertEquals("CANNOT TRAVEL TO SAME CITY",test.searchRoute(loc1,loc1,temp));
        assertEquals("CANNOT TRAVEL TO SAME CITY",test.searchRoute(loc3,loc3,temp));

        // If null returned then route was found, Test for Direct Routes
        System.out.println("Test for Direct Routes...");
        assertEquals(null,test.searchRoute(loc1,loc2,temp));
        assertEquals(null,test.searchRoute(loc2,loc3,temp));
        assertEquals(null,test.searchRoute(loc3,loc4,temp));


        // Test for One Stop Routes
        System.out.println("Test for One Stop Routes");
        assertEquals(null,test.searchRoute(loc1,loc3,temp));
        assertEquals(null,test.searchRoute(loc2,loc4,temp));

        // Test for NON REACHABLE CITY OR INVALID ROUTE
        System.out.println("Test for Unreachable Route");
        assertNotEquals(null,test.searchRoute(loc1,loc5,temp));
        assertNotEquals(null,test.searchRoute(loc2,loc5,temp));
        assertNotEquals(null,test.searchRoute(loc3,loc5,temp));
        assertNotEquals(null,test.searchRoute(loc4,loc5,temp));

        System.out.println("All SearchRoute Tests Passed !");
    }


    @Test
    public void testCreateBooking()throws Exception{

        // Valid User should Book
        System.out.println("Create a Booking from the List of Valid Routes and take the first route in the database. We dont care what it is since its valid");
        assertEquals(null,test.createBooking("one1",temp.get(0)));
        assertNotEquals(null,test.createBooking("nononon",temp.get(0)));

        // Valid User should NOT HAVE SAME EXISTING BOOKING
        System.out.println("Test if we are not already having that booking");
        assertNotEquals(null,test.createBooking("one1",temp.get(0)));
        assertEquals("you have already booked this flight for your account",test.createBooking("one1",temp.get(0)));

        System.out.println("All CreateBooking Tests Passed !");
    }

    @Test
    public void testCancelBooking()throws Exception{

        // INvalid ID test
        ArrayList <Booking> tempUserBooking = new ArrayList();
        user1.getMyBookings(tempUserBooking);
        Booking removeThis = tempUserBooking.get(0);

        test.cancelBooking(removeThis.getBookingId());
        System.out.println("All CancelBooking Tests Passed !");

        // Valid ID test

    }

}

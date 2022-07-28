package comp3350.acmis.business;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;

public class AccessBookingsTest {

    private AccessBookings testConn;

    @Before
    public void setUp() {
        Main.startUp();
        testConn = new AccessBookings("braico");

        Services.getDataAccess();

    }

    @After
    public void tearDown() {
        testConn = null;
        Main.shutDown();
    }

    @Test
    public void testBookingAccessCreation() {
        //null string that will be used to find user
        try {
            testConn = new AccessBookings(null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        setUp();

        //valid access booking connection is created.
        Assert.assertTrue(testConn != null);

        tearDown();
    }

    @Test
    public void testGetMyBookingsNullValue() {
        //null arraylist passed to getMyBooking class
        setUp();
        try {
            testConn.getMyBookings(null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }
        tearDown();
    }

    @Test
    public void testUserFoundOrNotFound() {
        //if the user is not found in the database the not booking will be available on their name
        testConn = new AccessBookings("Tester");
        Assert.assertEquals("No user found", testConn.getMyBookings(new ArrayList<>()));

        testConn = new AccessBookings("Hooman");
        Assert.assertEquals("No user found", testConn.getMyBookings(new ArrayList<>()));

        //user found in the database
        testConn = new AccessBookings("braico");
        assertEquals("No user found", testConn.getMyBookings(new ArrayList<>()));
    }

    @Test
    public void testGetBookings() {
        testConn = new AccessBookings("braico");
        ArrayList<Booking> booked = new ArrayList<>();
        testConn.getMyBookings(booked);

        Assert.assertEquals(0, booked.size());

    }


}
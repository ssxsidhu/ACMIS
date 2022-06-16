package comp3350.acmis.objects;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookingTest extends TestCase {
    private User user;
    private User user2;
    private Route route;
    private Booking booking1;
    private Booking booking2;

    @Before
    private void setup() {
        user = new User("foo", "bar", User.Gender.FEMALE, "myUsername", "fbp", "fb@gmail.com", "1234567890");
        user2 = new User("John", "Braico", User.Gender.MALE, "username", "abc", "email@example.com", "2222222222");
        route = new Route();
        booking1 = new Booking(user, route);
        booking2 = new Booking(user, route);
    }

    @After
    private void teardown() {
        user = null;
        user2 = null;
        route = null;
        booking1 = null;
        booking2 = null;
    }


    @Test
    public void testBooking() {
        System.out.println("Starting testBooking: booking");

        setup();

        assertTrue( booking1.getBookingId() != booking2.getBookingId());

        assertEquals(user, booking1.getBooker());
        assertEquals(route, booking1.getRoute());

        booking1.setNewUser(user2);
        assertEquals(user2, booking1.getBooker());

        teardown();
        System.out.println("Finished testBooking: booking");
    }

    @Test
    public void testNullObjects() {
        System.out.println("Starting testBooking: null objects");

        setup();

        try {
            booking1 = new Booking(null, route);
            fail("Expected a NullPointerException");
        }catch (NullPointerException unused){}
        try {
            booking1 = new Booking(user, null);
            fail("Expected a NullPointerException");
        }catch (NullPointerException unused){}

        teardown();
        System.out.println("Finished testBooking: null objects");
    }

    @Test
    public void testIncrementPassenger() {
        System.out.println("Starting testBooking: increment passenger");
        setup();

        assertEquals(1, booking1.getNumPassengers());
        for (int i = 0; i < Integer.MAX_VALUE-1; i++) {
            assertTrue(booking1.incrementPassengers());
        }
        assertEquals(Integer.MAX_VALUE, booking1.getNumPassengers());

        //add one more passenger, too big for int
        assertFalse(booking1.incrementPassengers());
        assertEquals(Integer.MAX_VALUE, booking1.getNumPassengers());

        teardown();
        System.out.println("Finished testBooking: increment passenger");
    }
}

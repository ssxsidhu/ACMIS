package comp3350.acmis.objects;


import junit.framework.TestCase;
import org.junit.Test;

public class BookingTest extends TestCase {
    @Test
    public void testBooking() {
        User user = new User("foo", "bar", User.Gender.FEMALE, "myUsername", "fbp", "fb@gmail.com", "1234567890");
        User user2 = new User("John", "Braico", User.Gender.MALE, "username", "abc", "email@example.com", "2222222222");
        Route route = new Route();
        Booking booking = new Booking(user, route);
        Booking booking2 = new Booking(user, route);

        assertTrue( booking.getBookingId() != booking2.getBookingId());

        assertEquals(user, booking.getBooker());
        assertEquals(route, booking.getRoute());

        booking.setNewUser(user2);
        assertEquals(user2, booking.getBooker());


        try {
            booking = new Booking(null, route);
            fail("Expected a NullPointerException");
        }catch (NullPointerException unused){}
        try {
            booking = new Booking(user, null);
            fail("Expected a NullPointerException");
        }catch (NullPointerException unused){}
    }

    @Test
    public void testIncrementPassenger() {
        User user = new User("foo", "bar", User.Gender.FEMALE, "myUsername", "fbp", "fb@gmail.com", "1234567890");
        Route route = new Route();
        Booking booking = new Booking(user, route);

        assertEquals(1, booking.getNumPassengers());
        for (int i = 0; i < Integer.MAX_VALUE-1; i++) {
            assertTrue(booking.incrementPassengers());
        }
        assertEquals(Integer.MAX_VALUE, booking.getNumPassengers());

        //add one more passenger, too big for int
        assertFalse(booking.incrementPassengers());
        assertEquals(Integer.MAX_VALUE, booking.getNumPassengers());
    }
}

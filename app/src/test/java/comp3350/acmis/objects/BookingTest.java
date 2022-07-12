package comp3350.acmis.objects;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookingTest {
    private User user;
    private User user2;
    private Route route;
    private Booking booking1;
    private Booking booking2;

    @Before
    public void setup() {
        user = new User("foo", "bar", User.Gender.FEMALE, "myUsername", "fbp", "fb@gmail.com", "1234567890");
        user2 = new User("John", "Braico", User.Gender.MALE, "username", "abc", "email@example.com", "2222222222");
        route = new Route();
        booking1 = new Booking(user, route, 1);
        booking2 = new Booking(user2, route, route, 1);
    }

    @After
    public void teardown() {
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
        //booking id is unique
        Assert.assertTrue(booking1.getBookingId() != booking2.getBookingId());

        Assert.assertEquals(user, booking1.getBooker());
        Assert.assertEquals(route, booking1.getRouteDepart());

        //Booking 1 doesnt have a return route
        Assert.assertNull(booking1.getRouteReturn());

        booking1.setNewUser(user2);
        Assert.assertEquals(user2, booking1.getBooker());

        //Booking 2 that is a 2 way booking
        Assert.assertTrue(booking2 != null);
        Assert.assertEquals(user2, booking2.getBooker());
        //return route is available
        Assert.assertTrue(null != booking2.getRouteReturn());

        teardown();
        System.out.println("Finished testBooking: booking");
    }

    @Test
    public void testNullObjects() {
        System.out.println("Starting testBooking: null objects");

        setup();

        try {
            booking1 = new Booking(null, route, 1);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }
        try {
            booking1 = new Booking(user, null, 1);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        teardown();
        System.out.println("Finished testBooking: null objects");
    }

    @Test
    public void testIncrementPassenger() {
        System.out.println("Starting testBooking: increment passenger");
        setup();

        Assert.assertEquals(1, booking1.getNumPassengers());
        for (int i = 0; i < Integer.MAX_VALUE - 1; i++) {
            Assert.assertTrue(booking1.incrementPassengers());
        }
        Assert.assertEquals(Integer.MAX_VALUE, booking1.getNumPassengers());

        //add one more passenger, too big for int
        Assert.assertFalse(booking1.incrementPassengers());
        Assert.assertEquals(Integer.MAX_VALUE, booking1.getNumPassengers());

        teardown();
        System.out.println("Finished testBooking: increment passenger");
    }
}

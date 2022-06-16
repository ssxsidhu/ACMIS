package comp3350.acmis.objects;

import static org.junit.Assert.*;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

public class UserTest extends TestCase {
    @Test
    public void testFirstName() {
        User user = null;

        //null firstname
        try {
            user = new User(null, "bar", User.Gender.OTHER , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        //empty firstname
        try {
            user = new User("", "bar", User.Gender.OTHER , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        //check only spaces
        try {
            user = new User("      ", "bar", User.Gender.OTHER , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        user = new User("foo", "bar", User.Gender.OTHER , "myUsername", "fbp", "fb@gmail.com", "1111111111");
        assertEquals("foo", user.getFirstName());

        user = new User("    foo     ", "bar", User.Gender.OTHER , "myUsername", "fbp", "fb@gmail.com", "1111111111");
        assertEquals("foo", user.getFirstName());
    }

    @Test
    public void testLastName() {
        User user = null;

        //null lastname
        try {
            user = new User("foo", null, User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        //empty lastname
        try {
            user = new User("foo", "", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        //check only spaces
        try {
            user = new User("foo", "      ", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        user = new User("foo", "bar", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
        assertEquals("bar", user.getLastName());

        user = new User("    foo     ", "   bar     ", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
        assertEquals("bar", user.getLastName());
    }

    @Test
    public void testGender() {
        User user = null;

        //null gender
        try {
            user = new User("foo", "bar", null , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        user = new User("foo", "bar", User.Gender.FEMALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
        assertEquals(User.Gender.FEMALE, user.getGender());
    }

    @Test
    public void testUsername() {
        User user = null;

        //null username
        try {
            user = new User("foo", "bar", User.Gender.MALE, null, "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //empty username
        try {
            user = new User("foo", "bar", User.Gender.MALE, "", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //check only spaces
        try {
            user = new User("foo", "bar", User.Gender.MALE, "    ", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        user = new User("foo", "bar", User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", "1111111111");
        assertEquals("myUsername", user.getUsername());

        user = new User("foo", "   bar     ", User.Gender.MALE, "     myUsername ", "fbp", "fb@gmail.com", "1111111111");
        assertEquals("myUsername", user.getUsername());
    }

    @Test
    public void testPhoneNumber(){
        User user = null;

        //null phone number
        try {
            user = new User("foo", "bar", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        //check phone number length
        try {
            user = new User("foo", "bar", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "123");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}
        try {
            user = new User("foo", "bar", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "123456789123456789");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}
    }


    @Test
    public void testGetBookings() {
        //method is not have fully implemented yet, testing current version
        User user = new User("foo", "bar", User.Gender.OTHER, "myUsername", "fbp", "fb@gmail.com", "1234567890");
        ArrayList<Booking> bookings = new ArrayList<Booking>();

        assertNull(user.getMyBookings(bookings));

        try {
            user.getMyBookings(null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}
    }

    @Test
    public void testBooking() {
        User user = new User("foo", "bar", User.Gender.OTHER, "myUsername", "fbp", "fb@gmail.com", "1234567890");
        Booking booking1 = new Booking(user, new Route());
        Booking booking2 = new Booking(user, new Route());

        //test initial size and after 2 inserts size
        assertEquals(0, user.getBookingsTemporaryTest().size());
        user.addBooking(booking1);
        user.addBooking(booking2);
        assertEquals(2, user.getBookingsTemporaryTest().size());

        //test size after removing 1
        user.removeBooking(booking1.getBookingId());
        assertEquals(1, user.getBookingsTemporaryTest().size());

        //test the remaining bookingID matches
        assertEquals(booking2.getBookingId(), user.getBookingsTemporaryTest().get(0).getBookingId());

        try {
            user.addBooking(null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        try {
            user.removeBooking(-1);
            fail("Expected a NegativeArraySizeException");
        } catch (NegativeArraySizeException unused) {}

        //no booking should have this high of an ID
        assertFalse(user.removeBooking(Integer.MAX_VALUE));
    }
}
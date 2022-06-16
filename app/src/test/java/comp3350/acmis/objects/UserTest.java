package comp3350.acmis.objects;


import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

public class UserTest extends TestCase {
    @Test
    public void testFirstName() {
        System.out.println("Starting testUser: firstname");

        User user;

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

        System.out.println("Finished testUser: firstname");
    }

    @Test
    public void testLastName() {
        System.out.println("Starting testUser: lastname");

        User user;

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

        System.out.println("Finished testUser: lastname");
    }

    @Test
    public void testGender() {
        System.out.println("Starting testUser: gender");

        User user;

        //null gender
        try {
            user = new User("foo", "bar", null , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        user = new User("foo", "bar", User.Gender.FEMALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
        assertEquals(User.Gender.FEMALE, user.getGender());

        System.out.println("Finished testUser: gender");
    }

    @Test
    public void testUsername() {
        System.out.println("Starting testUser: username");

        User user;

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

        System.out.println("Finished testUser: username");
    }

    @Test
    public void testPhoneNumber(){
        System.out.println("Starting testUser: phone number");

        User user;

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

        System.out.println("Finished testUser: phone number");
    }


    @Test
    public void testGetBookings() {
        System.out.println("Starting testUser: get bookings");

        //method is not have fully implemented yet, testing current version
        User user = new User("foo", "bar", User.Gender.OTHER, "myUsername", "fbp", "fb@gmail.com", "1234567890");
        ArrayList<Booking> bookings = new ArrayList<Booking>();

        assertNull(user.getMyBookings(bookings));

        try {
            user.getMyBookings(null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        System.out.println("Finished testUser: get bookings");
    }

    @Test
    public void testAddRemoveBooking() {
        System.out.println("Starting testUser: add/remove booking");

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

        System.out.println("Finished testUser: add/remove booking");
    }
}
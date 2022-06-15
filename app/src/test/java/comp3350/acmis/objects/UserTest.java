package comp3350.acmis.objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
    @Test
    public void testFirstName() {
        User user = null;

        //null firstname
        try {
            user = new User(null, "bar", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        //empty firstname
        try {
            user = new User("", "bar", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        //check only spaces
        try {
            user = new User("      ", "bar", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        user = new User("foo", "bar", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
        assertEquals("foo", user.getFirstName());

        user = new User("    foo     ", "bar", User.Gender.MALE , "myUsername", "fbp", "fb@gmail.com", "1111111111");
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



//    @Test
//    public void testBookings() {
//        User user = new User("foo", "bar", User.Gender.FEMALE , "myUsername", "fbp", "fb@gmail.com", "1234567890");
//    }
}
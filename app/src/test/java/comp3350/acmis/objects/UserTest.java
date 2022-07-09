package comp3350.acmis.objects;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    @Test
    public void testFirstName() {
        System.out.println("Starting testUser: firstname");

        User user;

        //null firstname
        try {
            user = new User(null, "bar", User.Gender.OTHER, "myUsername", "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //empty firstname
        try {
            user = new User("", "bar", User.Gender.OTHER, "myUsername", "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //check only spaces
        try {
            user = new User("      ", "bar", User.Gender.OTHER, "myUsername", "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        user = new User("foo", "bar", User.Gender.OTHER, "myUsername", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals("foo", user.getFirstName());

        user = new User("    foo     ", "bar", User.Gender.OTHER, "myUsername", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals("foo", user.getFirstName());

        user = new User("MUSK", "bar", User.Gender.OTHER, "myUsername", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals("MUSK", user.getFirstName());

        System.out.println("Finished testUser: firstname");
    }

    @Test
    public void testLastName() {
        System.out.println("Starting testUser: lastname");

        User user;

        //null lastname
        try {
            user = new User("foo", null, User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //empty lastname
        try {
            user = new User("foo", "", User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //check only spaces
        try {
            user = new User("foo", "      ", User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        user = new User("foo", "bar", User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals("bar", user.getLastName());

        user = new User("    foo     ", "   bar     ", User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals("bar", user.getLastName());

        System.out.println("Finished testUser: lastname");
    }

    @Test
    public void testGender() {
        System.out.println("Starting testUser: gender");

        User user;

        //null gender
        try {
            user = new User("foo", "bar", null, "myUsername", "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        user = new User("foo", "bar", User.Gender.FEMALE, "myUsername", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals(User.Gender.FEMALE, user.getGender());

        System.out.println("Finished testUser: gender");
    }

    @Test
    public void testUsername() {
        System.out.println("Starting testUser: username");

        User user;

        //null username
        try {
            user = new User("foo", "bar", User.Gender.MALE, null, "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //empty username
        try {
            user = new User("foo", "bar", User.Gender.MALE, "", "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //check only spaces
        try {
            user = new User("foo", "bar", User.Gender.MALE, "    ", "fbp", "fb@gmail.com", "1111111111");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        user = new User("foo", "bar", User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals("myUsername", user.getUsername());

        user = new User("foo", "   bar     ", User.Gender.MALE, "     myUsername ", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals("myUsername", user.getUsername());

        user = new User("foo", "   bar     ", User.Gender.MALE, "superDuperMonkey123", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals("superDuperMonkey123", user.getUsername());
        System.out.println("Finished testUser: username");
    }

    @Test
    public void testPhoneNumber() {
        System.out.println("Starting testUser: phone number");

        User user;

        //null phone number
        try {
            user = new User("foo", "bar", User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //check phone number length
        try {
            user = new User("foo", "bar", User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", "123");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        try {
            user = new User("foo", "bar", User.Gender.MALE, "myUsername", "fbp", "fb@gmail.com", "123456789123456789");
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //valid phone number
        user = new User("foo", "bar", User.Gender.MALE, "superDuperMonkey123", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals(1111111111, user.getPhoneNumber());

        System.out.println("Finished testUser: phone number");
    }

    @Test
    public void testEmail() {
        System.out.println("Starting testUser:  email");
        User user;

        //null email
        try {
            user = new User("foo", "bar", User.Gender.MALE, "myUsername", "fbp", null, "1111111111");
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //empty email
        try {
            user = new User("foo", "bar", User.Gender.MALE, "myUsername", "fbp", "", null);
            Assert.fail("Expected a NullPointerException");
        } catch (IllegalArgumentException unused) {
        }

        //empty email, only spaces found in the email
        try {
            user = new User("foo", "bar", User.Gender.MALE, "      ", "fbp", "     ", null);
            Assert.fail("Expected a NullPointerException");
        } catch (IllegalArgumentException unused) {
        }

        //the email is invalid, does not contain the @ symbol
        try {
            user = new User("foo", "bar", User.Gender.MALE, "      ", "fbp", "superDuperMonkey.com", null);
            Assert.fail("Expected a NullPointerException");
        } catch (IllegalArgumentException unused) {
        }

        //a valid way of storing the email
        user = new User("foo", "bar", User.Gender.MALE, "superDuperMonkey123", "fbp", "fb@gmail.com", "1111111111");
        Assert.assertEquals("fb@gmail.com", user.getEmail());

        System.out.println("Finished testUser: email");


    }


}
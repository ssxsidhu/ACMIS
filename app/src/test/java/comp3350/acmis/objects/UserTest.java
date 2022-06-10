package comp3350.acmis.objects;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    //this is basic test we will a lot more tests
    //Also I gotta check with the prof the use of @Rule.
    @Test
    public void testConstructor() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Last name cannot be null");

        User testUser1 = new User("foo", null, "Male", "fb", "fbp", "fb@gmail.com", "1111111111");
        assertEquals("foo",testUser1.getFirstName());
    }
}
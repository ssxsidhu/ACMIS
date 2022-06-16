package comp3350.acmis.business;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Location;

public class BookingManagerTest {

    @Test
    public void testSetup()
    {
        // Get Data Base here and then randomly create objects and perform tests below

    }

    @Test
    public void testEnd()
    {}
    @Test
    public void testConstructor() {

        BookingManager test = new BookingManager();     // Name of DB is intrinsically Supplied
        assertNotEquals(null,test);

    }

    @Test
    public void testCreateBooking()
    {

    }
    public void testSearchRoute(Location src, Location dest)
    {

    }
    public void testCancelBooking(int bookingID)
    {}

}

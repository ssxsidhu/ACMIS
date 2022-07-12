package comp3350.acmis.business;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZonedDateTime;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.persistence.DataAccess;

public class AccessBookingsTest {

    private AccessBookings testConn;
    private DataAccess dataAccess;

    @Before
    public void setUp() {
        testConn = new AccessBookings("Tester");
        dataAccess = Services.getDataAccess(Main.dbName);
        dataAccess.open("\"database/acmisHSQLDB\"");
    }

    @After
    public void tearDown() {
        testConn = null;
    }

    @Test
    public void testBookingAccess() {
        setUp();
        //null string
        try {
            testConn = new AccessBookings(null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

    }

}
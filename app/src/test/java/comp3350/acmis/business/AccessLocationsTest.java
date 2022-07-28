package comp3350.acmis.business;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Location;
import comp3350.acmis.persistence.DataAccess;
import comp3350.acmis.persistence.DataAccessStub;

public class AccessLocationsTest {
    private AccessLocations testConn;
    private ArrayList<Location> availableLocations;
    private DataAccess dataAccess;

    @Before
    public void setUp() {

        dataAccess = Services.createDataAccess(new DataAccessStub());
        Services.dataAccessOpen();
        testConn = new AccessLocations();


    }

    @After
    public void tearDown() {
        Services.closeDataAccess();
    }

    @Test
    public void testNullValues() {
        //null arraylist passed to accesslocation class
        setUp();
        try {
            testConn.getLocations(null);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }
        tearDown();
    }

    @Test
    public void testValidLocationList() {
        setUp();
        availableLocations = new ArrayList<>();
        testConn.getLocations(availableLocations);

        //all the 6 locations in the database.
        Assert.assertEquals(6, availableLocations.size());

        tearDown();
    }

    @Test
    public void testAllLocations() {
        setUp();
        availableLocations = new ArrayList<>();
        testConn.getLocations(availableLocations);

        Assert.assertEquals("Winnipeg", availableLocations.get(0).getCity());
        Assert.assertEquals("Vancouver", availableLocations.get(1).getCity());
        Assert.assertEquals("Toronto", availableLocations.get(2).getCity());
        Assert.assertEquals("Regina", availableLocations.get(3).getCity());
        Assert.assertEquals("Calgary", availableLocations.get(4).getCity());
        Assert.assertEquals("Montreal", availableLocations.get(5).getCity());

        tearDown();
    }


}
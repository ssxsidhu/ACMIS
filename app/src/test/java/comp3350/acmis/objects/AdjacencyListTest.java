package comp3350.acmis.objects;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;


public class AdjacencyListTest extends TestCase {

    Location testLoc1, testLoc2, testLoc3, testLoc4, testLoc5;
    Flight flight1, flight2, flight3, flight4, flight5;
    AdjacencyList testThis;

    @Before
    public void setup() {
        testLoc1 = new Location("Location 1", ZoneId.systemDefault(), "TEST Country", "TEST AIRPORT");
        testLoc2 = new Location("Location 2", ZoneId.systemDefault(), "TEST Country", "TEST AIRPORT");
        testLoc3 = new Location("Location 3", ZoneId.systemDefault(), "TEST Country", "TEST AIRPORT");
        testLoc4 = new Location("Location 4", ZoneId.systemDefault(), "TEST Country", "TEST AIRPORT");
        testLoc5 = new Location("NOT CONNECTED to 1", ZoneId.systemDefault(), "TEST Country", "TEST AIRPORT");

        flight1 = new Flight(testLoc1, testLoc2, ZonedDateTime.now(), 2, 3, 3);
        flight2 = new Flight(testLoc2, testLoc3, ZonedDateTime.now(), 2, 3, 3);
        flight3 = new Flight(testLoc3, testLoc4, ZonedDateTime.now(), 2, 3, 3);
        flight4 = new Flight(testLoc3, testLoc1, ZonedDateTime.now(), 2, 3, 3);
        flight5 = new Flight(testLoc3, testLoc5, ZonedDateTime.now(), 2, 3, 3);

        System.out.println("Create Adjacency List Object for Testing\n\n");
        System.out.println("Location 1 Will Act as Source Node...");
        testThis = new AdjacencyList(testLoc1);


    }

    @After
    public void tear() {

        testThis = null;
        testLoc2 = testLoc3 = testLoc4 = testLoc5 = null;
        flight1 = flight2 = flight3 = flight4 = flight5 = null;
    }

    @Test
    public void testSize() {

        setup();
        Assert.assertEquals(testThis.getSize(), 0);
        testThis.addNext(testLoc2, flight2);
        testThis.addNext(testLoc3, flight3);

        Assert.assertEquals(testThis.getSize(), 2);
        testThis.addNext(testLoc4, flight4);
        testThis.addNext(testLoc5, flight5);

        Assert.assertEquals(testThis.getSize(), 4);
        tear();
    }

    @Test
    public void testValidAdd() {

        setup();

        try {
            testThis.addNext(null, null);
            Assert.fail("Expected Null Pointer Exception");

        } catch (NullPointerException nullptr) {
        }

        System.out.println("Connect Location 2 to Location 1 with FLight 1");
        testThis.addNext(testLoc2, flight1);

        Assert.assertTrue(testThis.getSize() == 1);
        Assert.assertEquals(testThis.getSourceCity(), testLoc1.getCity());
        Assert.assertTrue(testThis.contains(testLoc2));

        System.out.println("All Valid Add Tests Passed !");
        tear();
    }

    @Test
    public void testContainsAdded() {
        setup();
        System.out.println("Testing for Contains Method");
        Assert.assertFalse(testThis.contains(testLoc2));
        Assert.assertTrue(testThis.contains(testLoc1));

        testThis.addNext(testLoc2, flight2);
        Assert.assertTrue(testThis.contains(testLoc2));
        Assert.assertFalse(testThis.contains(testLoc3));
        Assert.assertFalse(testThis.contains(testLoc4));

        testThis.addNext(testLoc3, flight3);
        testThis.addNext(testLoc4, flight4);

        Assert.assertTrue(testThis.contains(testLoc3));
        Assert.assertTrue(testThis.contains(testLoc4));

        System.out.println("All Contains Tests passed !");
        tear();
    }

    @Test
    public void testCopyList() {
        setup();

        System.out.println("Test for Copy Lists. Add 3 locations to list.");
        testThis.addNext(testLoc2, flight1);
        testThis.addNext(testLoc3, flight3);
        testThis.addNext(testLoc4, flight4);
        testThis.addNext(testLoc5, flight5);

        ArrayList<Location> getLocs = null;

        try {
            testThis.copyList(getLocs);
            Assert.fail("Expected A NULL POINTER EXCEPTION");
        } catch (NullPointerException nullptr) {
        }

        getLocs = new ArrayList<Location>();
        testThis.copyList(getLocs);

        Assert.assertEquals(testThis.getSize(), 4);
        Assert.assertEquals(testThis.contains(testLoc1), getLocs.contains(testLoc1));
        Assert.assertEquals(testThis.contains(testLoc2), getLocs.contains(testLoc2));
        Assert.assertEquals(testThis.contains(testLoc3), getLocs.contains(testLoc3));
        Assert.assertEquals(testThis.contains(testLoc4), getLocs.contains(testLoc4));

        System.out.println("All testCopyList Tests Passed !");
        tear();
    }


}
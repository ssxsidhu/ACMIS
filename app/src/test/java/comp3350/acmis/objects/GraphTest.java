package comp3350.acmis.objects;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GraphTest extends TestCase {

    Location testLoc1, testLoc2, testLoc3, testLoc4, testLoc5;
    Flight flight1, flight2, flight3, flight4, flight5;

    @Before
    public void setup()
    {
        testLoc1 = new Location("Location 1",null, "TEST Country", "TEST AIRPORT" );
        testLoc2 = new Location("Location 2",null, "TEST Country", "TEST AIRPORT" );
        testLoc3 = new Location("Location 3",null, "TEST Country", "TEST AIRPORT" );
        testLoc4 = new Location("Location 4",null, "TEST Country", "TEST AIRPORT" );
        testLoc5 = new Location("NOT CONNECTED to 1",null, "TEST Country", "TEST AIRPORT" );

        flight1 = new Flight(testLoc1,testLoc2,null,2,3,3);
        flight2 = new Flight(testLoc2,testLoc3,null,2,3,3);
        flight3 = new Flight(testLoc3,testLoc4,null,2,3,3);
        flight4 = new Flight(testLoc3,testLoc1,null,2,3,3);
        flight5 = new Flight(testLoc3,testLoc5,null,2,3,3);
    }

    @After
    public void tear()
    {}

    @Test
    public void testValidNodes()
    {}
    @Test
    public void testValidEdges()
    {}
    @Test
    public void testContainsCity()
    {}
    @Test
    public void testGetNeighbors()
    {}
}

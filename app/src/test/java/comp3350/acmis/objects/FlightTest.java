/*
This class is used to test the functionality of the flight class.

 */
package comp3350.acmis.objects;


import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.Duration;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class FlightTest extends TestCase{
    Location LocA,LocB;
    Flight testFlight;

    @Before
    public void setUp()  {
        LocA = new Location("Calgary", ZoneId.of("America/Edmonton"), "Canada","YYC");
        LocB = new Location("Regina", ZoneId.of("America/Regina"), "Canada","YQR");
        testFlight = new Flight(LocA,LocB, ZonedDateTime.of(2022,6,11,7,30,0,0,LocA.getZoneName()), 250, 2.5, 500);

    }

    @Test
    //Test the getSource method return the correct source city
    public void testgetSource() {
        setUp();
        //System.out.println(testFlight.get);
        Assert.assertEquals("Calgary", testFlight.getSource().getCity());
    }
    @Test
    //Test the getDestination method returns the correct destination city
    public void testgetDestination() {
        setUp();

        Assert.assertEquals("Regina", testFlight.getDestination().getCity());
    }
    @Test
    public void testgetSeats() {
        setUp();
        Assert.assertEquals(250, testFlight.getSeats());
    }


    @Test
    public void TestgetDuration() {
        setUp();
        Assert.assertEquals(Duration.ofHours(2).plusMinutes(30), testFlight.getDuration());
    }

    @Test
    public void TestgetCost() {
        setUp();
        Assert.assertEquals(500, testFlight.getCost());
    }

    @Test
    public void testenoughSeats(){
        setUp();
        assertEquals(true, testFlight.enoughSeats(5));
    }

    @Test
    public void testBookSeats(){
        setUp();
        assertEquals(true,testFlight.bookSeat(5));
    }



}
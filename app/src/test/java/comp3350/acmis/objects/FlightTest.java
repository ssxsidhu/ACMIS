/*
This class is used to test the functionality of the flight class.

 */
package comp3350.acmis.objects;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class FlightTest extends TestCase {
    Location LocA,LocB;
    Flight testFlight;

    @Override
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
        assertEquals("Toronto",testFlight.getSource().getCity());
    }

    @Test
    //Test the getDestination method returns the correct destination city
    public void testgetDestination() {
        setUp();

        assertEquals("Vancouver", testFlight.getDestination().getCity());
    }





    @Test
    public void getSeats() {

    }

    @Test
    public void setSeats() {
    }

    @Test
    public void getDuration() {
    }

    @Test
    public void setDuration() {
    }

    @Test
    public void getCost() {
    }

    @Test
    public void setCost() {
    }
}
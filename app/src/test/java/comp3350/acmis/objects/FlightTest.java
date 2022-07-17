/*
This class is used to test the functionality of the flight class.
 */
package comp3350.acmis.objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;


public class FlightTest {
    Location LocA, LocB;
    Flight testFlight;

    @Before
    public void setUp() {

        LocA = new Location("Calgary", ZoneId.of("America/Edmonton"), "Canada", "YYC");
        LocB = new Location("Regina", ZoneId.of("America/Regina"), "Canada", "YQR");
        testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), 250, 2.5, 500);
    }

    @After
    public void tearDown() {
        LocA = null;
        LocB = null;
        testFlight = null;
    }

    @Test
    public void testSourceLocation() {

        //null SourcelLocation
        try {
            testFlight = new Flight(null, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), 250, 2.5, 500);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //valid source location
        setUp();
        Assert.assertEquals(testFlight.getSource(), LocA);
        tearDown();
    }

    @Test
    public void testDestLocation() {

        //null DestlLocation
        try {
            testFlight = new Flight(LocA, null, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), 250, 2.5, 500);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //valid source location
        setUp();
        Assert.assertEquals(testFlight.getDestination(), LocB);
        tearDown();
    }

    @Test
    public void testDeptDateAndTime() {
        //null depDateAndTime
        try {
            testFlight = new Flight(LocA, LocB, null, 250, 2.5, 500);
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //valid date and time departure
        setUp();
        //time only
        Assert.assertTrue(testFlight.getDepartureDateTime() != null);
        tearDown();
    }

    @Test
    public void testArvlDateAndTime() {
        //null arvlDateAndTime
        try {
            testFlight = new Flight(LocA, LocB, null, 250, 2.5, 500);
            //departure date and time is null , so that means arrival date and time will also be null
            Assert.assertNull(testFlight.getArrivalDateTime());
            Assert.fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

        //valid date and time departure
        setUp();
        //time only
        Assert.assertTrue(testFlight.getArrivalDateTime() != null);

        tearDown();
    }

    @Test
    public void testValidDuration() {
        //0 duration
        try {
            testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), 250, 0, 500);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        //negative duration
        try {
            testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), 250, -10.2, 500);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        try {
            testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), 250, -5, 500);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        //valid durations
        setUp();
        Assert.assertTrue(testFlight.getDuration() != null);
        System.out.println(testFlight.getDuration());
        Assert.assertEquals(150, testFlight.getDuration().toMinutes());

        tearDown();
    }

    @Test
    public void testValidSeats() {
        //0seats
        try {
            testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), 0, 2, 500);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        //negative number of seats
        try {
            testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), -200, 2, 500);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        try {
            testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), -1500, 2, 500);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //valid seats
        setUp();
        Assert.assertEquals(250, testFlight.getSeats());
        tearDown();

    }

    @Test
    public void testValidCost() {
        //0 cost
        try {
            testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), 200, 2, 0);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        //negative number of seats
        try {
            testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), -200, 2, -500);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        try {
            testFlight = new Flight(LocA, LocB, ZonedDateTime.of(2022, 6, 11, 7, 30, 0, 0, LocA.getZoneName()), -1500, 2, -20500);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //valid seats
        setUp();
        Assert.assertEquals(500, testFlight.getCost());
        tearDown();
    }

    @Test
    public void testingBookingSeatCalculation() {
        setUp();
        try {
            testFlight.bookSeat(0);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        //negative seats booked
        try {
            testFlight.bookSeat(-10);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }
        try {
            testFlight.bookSeat(-200);
            Assert.fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {
        }

        //valid booking
        Assert.assertTrue(testFlight.bookSeat(10));
        Assert.assertTrue(testFlight.bookSeat(140));
        //total space = 250
        //booked seats = 150
        //remaining = 100
        //not enough space
        Assert.assertFalse(testFlight.bookSeat(300));

        Assert.assertTrue(testFlight.bookSeat(100));

        tearDown();
    }


}
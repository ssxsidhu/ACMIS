package comp3350.acmis.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;


public class StubDBTest {

    private DataAccess db;

    @Before
    public void setUp() {
        Main.startUp();
        Services.getDataAccess(Main.dbName);
        db = new DataAccessStub(Main.dbName);
        db.open(Main.dbName);
    }

    @After
    public void tearDown() {
        db.close();
        Main.shutDown();
    }

    @Test
    public void testGetAllFlights() {
        ArrayList<Flight> flights = new ArrayList<>();
        Location montreal = new Location("Montreal", ZoneId.of("America/Montreal"), "Canada", "YUL");
        Location calgary = new Location("Calgary", ZoneId.of("America/Edmonton"), "Canada", "YYC");

        db.getAllFlights(flights);
        Flight flight1 = new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 6, 5, 0, 0, 0, montreal.getZoneName()), 175, 4.75, 120);
        Flight flight2 = flights.get(0);
        Flight flight3 = new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 6, 5, 0, 5, 0, montreal.getZoneName()), 175, 4.75, 120);

        Assert.assertEquals(200, flights.size());
        Assert.assertTrue(flight1.equals(flight2));
        Assert.assertFalse(flight3.equals(flight2));
    }

    @Test
    public void testGetValidUser() {
        User user1 = new User("John","Braico", User.Gender.MALE,"braico","somePassword","jbraico@cs.umanitoba.ca","2041234567");
        User user2 = db.getUserObject("braico");

        Assert.assertTrue(user1.equals(user2));
    }

    @Test
    public void testGetInvalidUser() {
        User user = db.getUserObject("I_Don't_Exist");
        Assert.assertNull(user);
    }

    @Test
    public void testGetLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        Location location1 = null;

        db.getLocations(locations);
        location1 = locations.get(0);

        Assert.assertEquals(6, locations.size());
        Assert.assertEquals("Winnipeg", location1.getCity());
    }

    @Test
    public void testValidBookings() {
        Location montreal = new Location("Montreal", ZoneId.of("America/Montreal"), "Canada", "YUL");
        Location calgary = new Location("Calgary", ZoneId.of("America/Edmonton"), "Canada", "YYC");
        Flight flight1 = new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 6, 5, 0, 0, 0, montreal.getZoneName()), 175, 4.75, 120);
        Flight flight2 = new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 6, 5, 0, 5, 0, montreal.getZoneName()), 175, 4.75, 120);
        User user1 = new User("John","Braico", User.Gender.MALE,"braico","somePassword","jbraico@cs.umanitoba.ca","2041234567");

        ArrayList<Booking> allBookings = new ArrayList<>();

        Booking booking1 = new Booking(user1, new Route(flight1),1,true);
        Booking booking2 = new Booking(user1, new Route(flight2),10,true);

        db.addBooking(booking1);
        db.addBooking(booking2);
        db.getUserBookings(user1, allBookings);

        Assert.assertEquals(2, allBookings.size());
        Assert.assertTrue(booking1.equals(allBookings.get(0)));
    }

    @Test
    public void testNoBookings() {
        User user2 = new User("Braden","Bileski", User.Gender.MALE,"bradenbm","somePassword","bradenbm@cs.umanitoba.ca","2041234567");
        ArrayList<Booking> allBookings = new ArrayList<>();

        db.getUserBookings(user2, allBookings);
        Assert.assertEquals(0, allBookings.size());
    }
}

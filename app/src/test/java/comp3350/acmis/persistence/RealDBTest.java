package comp3350.acmis.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;

import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;

public class RealDBTest {

    private DataAccess db;

    private Location winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
    private Location calgary = new Location("Calgary", ZoneId.of("America/Edmonton"), "Canada", "YYC");
    private Location montreal = new Location("Montreal", ZoneId.of("America/Montreal"), "Canada", "YUL");

    private ArrayList<Flight> allFlights = new ArrayList<>();
    private ArrayList<Booking> allBookings = new ArrayList<>();

    @Before
    public void setUp() {
        Services.closeDataAccess();
        db = Services.createDataAccess();
        Services.dataAccessOpen();
    }

    @After
    public void tearDown() {
        Services.closeDataAccess();
    }

    @Test
    public void testGetAllFlights() {
        db.getAllFlights(allFlights);
        Flight flight1 = new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 8, 1, 16, 30, 0, 0, winnipeg.getZoneName()), 175, 2.6, 200);
        Flight flight2 = allFlights.get(0);
        Flight flight3 = new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 6, 5, 0, 5, 0, montreal.getZoneName()), 175, 4.75, 120);

        Assert.assertEquals(930, allFlights.size());
        Assert.assertTrue(flight1.equals(flight2));
        Assert.assertFalse(flight3.equals(flight2));
    }

    @Test
    public void testGetValidUser() {
        User user1 = new User("John", "Braico", User.Gender.MALE, "braico", "somePassword", "jbraico@cs.umanitoba.ca", "2041234567");
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
        db.getAllFlights(allFlights);

        User user2 = db.getUserObject("bileskib");
        db.getUserBookings(user2, allBookings);

        for (int i = 0; i < allBookings.size(); i++) {
            db.cancelBooking(allBookings.get(i).getBookingId());
        }
        db.getUserBookings(user2, allBookings);
        Assert.assertEquals(0, allBookings.size());

        Booking booking1 = new Booking(user2, new Route(allFlights.get(50)), 1, false);
        Booking booking2 = new Booking(user2, new Route(allFlights.get(100)), 10, false);

        db.addBooking(booking1);
        db.addBooking(booking2);
        db.getUserBookings(user2, allBookings);

        Assert.assertEquals(2, allBookings.size());
        Assert.assertTrue(booking1.equals(allBookings.get(0)));
    }

    @Test
    public void testNoBookings() {
        User user2 = new User("Prahalad", "Iyer", User.Gender.MALE, "iverp", "somePassword", "email@example.com", "2041234567");
        ArrayList<Booking> allBookings = new ArrayList<>();

        db.getUserBookings(user2, allBookings);
        Assert.assertEquals(0, allBookings.size());
    }

    @Test
    public void testCancelSpecific() {
        User user1 = db.getUserObject("bileskib");
        db.getAllFlights(allFlights);

        db.getUserBookings(user1, allBookings);
        for (int i = 0; i < allBookings.size(); i++) {
            db.cancelBooking(allBookings.get(i).getBookingId());
        }
        db.getUserBookings(user1, allBookings);
        Assert.assertEquals(0, allBookings.size());

        Booking booking1 = new Booking(user1, new Route(allFlights.get(50)), 1, true);
        Booking booking2 = new Booking(user1, new Route(allFlights.get(100)), 2, true);
        Booking booking3 = new Booking(user1, new Route(allFlights.get(150)), 3, true);
        db.addBooking(booking1);
        db.addBooking(booking2);
        db.addBooking(booking3);
        db.getUserBookings(user1, allBookings);

        Assert.assertEquals(3, allBookings.size());

        db.cancelBooking(allBookings.get(1).getBookingId());
        db.getUserBookings(user1, allBookings);

        Assert.assertEquals(2, allBookings.size());
        Assert.assertTrue(booking1.equals(allBookings.get(0)));
        Assert.assertTrue(booking3.equals(allBookings.get(1)));
    }

    @Test
    public void testGetSpecificFlight() {
        Flight flight1, flight2;
        db.getFlights(winnipeg, montreal, ZonedDateTime.of(2022, 8, 1, 16, 30, 0, 0, winnipeg.getZoneName()), allFlights);
        Assert.assertEquals(2, allFlights.size());

        flight1 = allFlights.get(0);
        flight2 = allFlights.get(1);

        Assert.assertTrue(winnipeg.equals(flight1.getSource()));
        Assert.assertTrue(montreal.equals(flight2.getDestination()));
        Assert.assertEquals(2022, flight1.getDepartureDateTime().getYear());
        Assert.assertEquals(8, flight2.getDepartureDateTime().getMonthValue());
        Assert.assertEquals(1, flight1.getDepartureDateTime().getDayOfMonth());
    }
}

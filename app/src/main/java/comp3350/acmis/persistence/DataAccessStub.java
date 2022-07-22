package comp3350.acmis.persistence;


import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.Objects;

import comp3350.acmis.application.Main;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.User;

public class DataAccessStub implements DataAccess {
    private final String dbName;
    private final String dbType = "stub";
    private ArrayList<User> allUsers;
    private ArrayList<Flight> allFlights;
    private ArrayList<Location> allLocations;//tp store city,country,airport and other rdata later
    private ArrayList<Booking> allBookings;
    private BookingManager bookingManager;


    public DataAccessStub(String dbName) {
        this.dbName = dbName;
        allUsers = new ArrayList<>();
        allLocations = new ArrayList<>();
        allFlights = new ArrayList<Flight>();
        allBookings = new ArrayList<>();
    }

    public DataAccessStub() {
        this(Main.dbName);
        allUsers = new ArrayList<>();
        allLocations = new ArrayList<>();
        allFlights = new ArrayList<Flight>();
        allBookings = new ArrayList<>();
    }

    public void open(String dbName) {
        User user, defaultUser;

        bookingManager = new BookingManager();

        defaultUser = new User("John", "Braico", User.Gender.MALE, "braico", "somePassword", "jbraico@cs.umanitoba.ca", "2041234567");
        allUsers.add(defaultUser);
        user = new User("Julie", "Smith", User.Gender.FEMALE, "jsmith", "j&smith$", "jmith@gmail.com", "2048889999");
        allUsers.add(user);

        Location winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada", "YWG");
        allLocations.add(winnipeg);
        Location vancouver = new Location("Vancouver", ZoneId.of("America/Vancouver"), "Canada", "YVR");
        allLocations.add(vancouver);
        Location toronto = new Location("Toronto", ZoneId.of("America/Toronto"), "Canada", "YYZ");
        allLocations.add(toronto);
        Location regina = new Location("Regina", ZoneId.of("America/Regina"), "Canada", "YQR");
        allLocations.add(regina);
        Location calgary = new Location("Calgary", ZoneId.of("America/Edmonton"), "Canada", "YYC");
        allLocations.add(calgary);
        Location montreal = new Location("Montreal", ZoneId.of("America/Montreal"), "Canada", "YUL");
        allLocations.add(montreal);


        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 6, 5, 0, 0, 0, montreal.getZoneName()), 175, 4.75, 120));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 24, 17, 30, 0, 0, montreal.getZoneName()), 125, 4.75, 300));
        allFlights.add(new Flight(toronto, calgary, ZonedDateTime.of(2022, 7, 1, 17, 30, 0, 0, toronto.getZoneName()), 200, 3.25, 100));
        allFlights.add(new Flight(vancouver, winnipeg, ZonedDateTime.of(2022, 8, 5, 5, 30, 0, 0, vancouver.getZoneName()), 150, 2.5, 220));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 8, 10, 13, 0, 0, 0, calgary.getZoneName()), 200, 1.5, 140));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 25, 20, 30, 0, 0, winnipeg.getZoneName()), 100, 2.3, 200));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 8, 22, 0, 0, 0, toronto.getZoneName()), 125, 2.3, 100));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 3, 9, 30, 0, 0, calgary.getZoneName()), 200, 2.0, 120));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 23, 15, 30, 0, 0, montreal.getZoneName()), 200, 4.0, 240));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 24, 18, 0, 0, 0, toronto.getZoneName()), 175, 4.5, 260));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 25, 15, 30, 0, 0, montreal.getZoneName()), 125, 4.75, 260));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 13, 11, 0, 0, 0, toronto.getZoneName()), 175, 2.3, 180));
        allFlights.add(new Flight(montreal, toronto, ZonedDateTime.of(2022, 7, 10, 0, 0, 0, 0, montreal.getZoneName()), 175, 1.25, 200));
        allFlights.add(new Flight(vancouver, toronto, ZonedDateTime.of(2022, 8, 5, 16, 30, 0, 0, vancouver.getZoneName()), 200, 4.5, 160));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 19, 16, 0, 0, 0, montreal.getZoneName()), 100, 4.0, 120));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 10, 23, 0, 0, 0, regina.getZoneName()), 175, 2.0, 200));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 18, 17, 0, 0, 0, calgary.getZoneName()), 100, 2.0, 260));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 27, 17, 0, 0, 0, montreal.getZoneName()), 125, 4.75, 300));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 8, 22, 6, 30, 0, 0, regina.getZoneName()), 100, 1.5, 240));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 29, 5, 30, 0, 0, toronto.getZoneName()), 175, 2.3, 220));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 8, 17, 18, 0, 0, 0, toronto.getZoneName()), 175, 4.5, 140));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 25, 21, 30, 0, 0, calgary.getZoneName()), 150, 1.25, 260));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 8, 8, 7, 0, 0, 0, calgary.getZoneName()), 175, 1.25, 160));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 8, 28, 3, 30, 0, 0, vancouver.getZoneName()), 125, 1.3, 100));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 13, 7, 0, 0, 0, vancouver.getZoneName()), 175, 2.0, 220));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 8, 17, 18, 30, 0, 0, regina.getZoneName()), 175, 4.2, 300));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 11, 13, 30, 0, 0, calgary.getZoneName()), 125, 1.25, 220));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 23, 4, 0, 0, 0, toronto.getZoneName()), 175, 1.25, 100));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 24, 4, 30, 0, 0, montreal.getZoneName()), 100, 4.0, 260));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 12, 20, 30, 0, 0, calgary.getZoneName()), 125, 3.25, 100));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 8, 29, 14, 0, 0, 0, regina.getZoneName()), 125, 1.3, 100));
        allFlights.add(new Flight(winnipeg, vancouver, ZonedDateTime.of(2022, 7, 6, 12, 30, 0, 0, winnipeg.getZoneName()), 125, 2.5, 140));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 8, 10, 21, 0, 0, 0, toronto.getZoneName()), 125, 2.3, 300));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 8, 15, 23, 0, 0, 0, winnipeg.getZoneName()), 175, 2.0, 220));
        allFlights.add(new Flight(vancouver, winnipeg, ZonedDateTime.of(2022, 7, 6, 4, 30, 0, 0, vancouver.getZoneName()), 100, 2.5, 260));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 8, 15, 23, 30, 0, 0, winnipeg.getZoneName()), 200, 2.0, 260));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 25, 4, 30, 0, 0, regina.getZoneName()), 100, 1.5, 160));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 18, 23, 30, 0, 0, vancouver.getZoneName()), 100, 4.75, 220));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 16, 18, 0, 0, 0, vancouver.getZoneName()), 150, 2.0, 220));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 7, 24, 0, 0, 0, 0, montreal.getZoneName()), 125, 4.75, 300));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 16, 4, 0, 0, 0, montreal.getZoneName()), 175, 4.0, 120));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 8, 29, 4, 30, 0, 0, winnipeg.getZoneName()), 200, 2.0, 100));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 9, 22, 0, 0, 0, toronto.getZoneName()), 150, 4.2, 160));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 8, 17, 18, 0, 0, 0, vancouver.getZoneName()), 100, 1.3, 160));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 8, 8, 18, 30, 0, 0, toronto.getZoneName()), 175, 4.5, 260));
        allFlights.add(new Flight(winnipeg, vancouver, ZonedDateTime.of(2022, 7, 8, 17, 0, 0, 0, winnipeg.getZoneName()), 150, 2.5, 180));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 27, 12, 30, 0, 0, toronto.getZoneName()), 125, 4.2, 280));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 8, 11, 20, 30, 0, 0, regina.getZoneName()), 150, 1.3, 280));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 30, 2, 30, 0, 0, calgary.getZoneName()), 200, 4.75, 260));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 11, 10, 0, 0, 0, winnipeg.getZoneName()), 150, 2.0, 160));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 29, 8, 30, 0, 0, winnipeg.getZoneName()), 150, 2.0, 100));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 7, 23, 13, 30, 0, 0, calgary.getZoneName()), 150, 1.5, 140));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 24, 10, 0, 0, 0, regina.getZoneName()), 125, 4.2, 240));
        allFlights.add(new Flight(vancouver, toronto, ZonedDateTime.of(2022, 8, 20, 3, 0, 0, 0, vancouver.getZoneName()), 125, 4.5, 100));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 8, 31, 3, 0, 0, 0, regina.getZoneName()), 150, 2.0, 300));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 15, 13, 0, 0, 0, toronto.getZoneName()), 150, 1.25, 100));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 8, 14, 22, 30, 0, 0, regina.getZoneName()), 100, 4.0, 180));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 31, 3, 30, 0, 0, toronto.getZoneName()), 150, 2.3, 220));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 8, 13, 5, 30, 0, 0, toronto.getZoneName()), 150, 1.25, 100));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 8, 11, 14, 30, 0, 0, regina.getZoneName()), 200, 4.0, 140));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 8, 25, 18, 30, 0, 0, toronto.getZoneName()), 125, 4.5, 100));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 8, 30, 23, 0, 0, 0, toronto.getZoneName()), 150, 4.2, 220));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 8, 16, 17, 30, 0, 0, winnipeg.getZoneName()), 125, 2.0, 140));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 10, 23, 30, 0, 0, calgary.getZoneName()), 150, 1.25, 200));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 4, 3, 0, 0, 0, montreal.getZoneName()), 200, 2.6, 100));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 24, 4, 30, 0, 0, vancouver.getZoneName()), 175, 4.75, 220));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 4, 14, 0, 0, 0, montreal.getZoneName()), 200, 2.6, 100));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 10, 9, 30, 0, 0, winnipeg.getZoneName()), 200, 2.0, 160));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 8, 3, 4, 30, 0, 0, toronto.getZoneName()), 200, 2.3, 260));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 3, 22, 0, 0, 0, regina.getZoneName()), 100, 4.0, 220));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 12, 22, 0, 0, 0, calgary.getZoneName()), 175, 1.25, 160));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 8, 15, 20, 30, 0, 0, montreal.getZoneName()), 200, 4.75, 300));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 8, 18, 11, 30, 0, 0, calgary.getZoneName()), 150, 2.0, 200));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 8, 17, 8, 30, 0, 0, calgary.getZoneName()), 175, 1.25, 260));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 8, 28, 16, 30, 0, 0, regina.getZoneName()), 150, 2.0, 180));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 8, 2, 21, 0, 0, 0, montreal.getZoneName()), 200, 4.75, 160));
        allFlights.add(new Flight(toronto, calgary, ZonedDateTime.of(2022, 8, 27, 18, 0, 0, 0, toronto.getZoneName()), 125, 3.25, 300));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 8, 24, 18, 0, 0, 0, winnipeg.getZoneName()), 125, 2.6, 300));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 26, 12, 0, 0, 0, montreal.getZoneName()), 175, 2.6, 280));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 8, 10, 1, 0, 0, 0, toronto.getZoneName()), 175, 1.25, 220));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 5, 12, 30, 0, 0, vancouver.getZoneName()), 125, 4.75, 220));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 8, 8, 0, 30, 0, 0, calgary.getZoneName()), 125, 2.0, 260));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 8, 21, 0, 0, 0, montreal.getZoneName()), 200, 4.75, 160));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 8, 17, 12, 30, 0, 0, montreal.getZoneName()), 125, 4.0, 100));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 8, 24, 5, 30, 0, 0, calgary.getZoneName()), 175, 2.0, 300));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 22, 0, 30, 0, 0, toronto.getZoneName()), 150, 4.2, 120));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 8, 16, 0, 30, 0, 0, vancouver.getZoneName()), 175, 1.3, 140));
        allFlights.add(new Flight(vancouver, winnipeg, ZonedDateTime.of(2022, 8, 30, 9, 30, 0, 0, vancouver.getZoneName()), 125, 2.5, 120));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 8, 16, 11, 0, 0, 0, montreal.getZoneName()), 175, 4.0, 160));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 22, 11, 30, 0, 0, calgary.getZoneName()), 100, 2.0, 200));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 8, 14, 17, 30, 0, 0, calgary.getZoneName()), 175, 1.5, 100));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 18, 12, 30, 0, 0, toronto.getZoneName()), 125, 4.2, 200));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 8, 6, 15, 30, 0, 0, calgary.getZoneName()), 200, 1.25, 240));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 8, 14, 23, 30, 0, 0, calgary.getZoneName()), 150, 1.5, 240));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 7, 28, 8, 30, 0, 0, regina.getZoneName()), 200, 1.3, 160));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 6, 14, 0, 0, 0, vancouver.getZoneName()), 100, 4.75, 120));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 18, 10, 0, 0, 0, montreal.getZoneName()), 125, 4.75, 260));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 8, 31, 20, 30, 0, 0, regina.getZoneName()), 200, 1.3, 300));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 7, 9, 30, 0, 0, toronto.getZoneName()), 100, 4.5, 300));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 8, 7, 13, 0, 0, 0, toronto.getZoneName()), 125, 4.2, 260));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 8, 7, 8, 30, 0, 0, calgary.getZoneName()), 200, 3.25, 160));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 8, 24, 3, 0, 0, 0, montreal.getZoneName()), 150, 4.75, 120));
        allFlights.add(new Flight(vancouver, toronto, ZonedDateTime.of(2022, 7, 29, 1, 30, 0, 0, vancouver.getZoneName()), 125, 4.5, 260));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 8, 13, 8, 0, 0, 0, winnipeg.getZoneName()), 125, 2.6, 160));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 8, 28, 15, 0, 0, 0, toronto.getZoneName()), 175, 4.5, 160));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 8, 20, 0, 30, 0, 0, calgary.getZoneName()), 150, 2.0, 100));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 8, 20, 3, 30, 0, 0, winnipeg.getZoneName()), 150, 2.3, 260));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 8, 12, 13, 0, 0, 0, vancouver.getZoneName()), 200, 2.0, 160));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 27, 10, 30, 0, 0, montreal.getZoneName()), 100, 4.75, 300));
        allFlights.add(new Flight(vancouver, toronto, ZonedDateTime.of(2022, 8, 25, 15, 30, 0, 0, vancouver.getZoneName()), 100, 4.5, 240));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 8, 6, 0, 0, 0, toronto.getZoneName()), 200, 4.5, 260));
        allFlights.add(new Flight(montreal, toronto, ZonedDateTime.of(2022, 7, 27, 6, 0, 0, 0, montreal.getZoneName()), 200, 1.25, 220));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 8, 12, 0, 30, 0, 0, toronto.getZoneName()), 100, 4.2, 100));
        allFlights.add(new Flight(vancouver, winnipeg, ZonedDateTime.of(2022, 7, 16, 0, 0, 0, 0, vancouver.getZoneName()), 125, 2.5, 240));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 30, 19, 30, 0, 0, regina.getZoneName()), 150, 2.0, 160));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 7, 11, 21, 0, 0, 0, vancouver.getZoneName()), 200, 1.3, 260));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 29, 7, 0, 0, 0, vancouver.getZoneName()), 125, 4.75, 240));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 28, 22, 30, 0, 0, vancouver.getZoneName()), 125, 2.0, 280));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 8, 20, 11, 30, 0, 0, toronto.getZoneName()), 200, 4.2, 100));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 2, 11, 0, 0, 0, calgary.getZoneName()), 150, 3.25, 300));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 8, 5, 20, 30, 0, 0, regina.getZoneName()), 200, 2.0, 140));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 18, 5, 0, 0, 0, winnipeg.getZoneName()), 100, 2.0, 240));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 14, 1, 30, 0, 0, winnipeg.getZoneName()), 200, 2.3, 200));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 8, 14, 4, 30, 0, 0, regina.getZoneName()), 125, 1.5, 280));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 24, 20, 30, 0, 0, vancouver.getZoneName()), 125, 2.0, 240));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 7, 27, 0, 30, 0, 0, winnipeg.getZoneName()), 150, 2.6, 180));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 10, 19, 0, 0, 0, vancouver.getZoneName()), 125, 4.75, 240));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 8, 8, 16, 30, 0, 0, toronto.getZoneName()), 175, 4.2, 260));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 8, 11, 1, 30, 0, 0, vancouver.getZoneName()), 150, 2.0, 280));
        allFlights.add(new Flight(montreal, toronto, ZonedDateTime.of(2022, 8, 5, 5, 30, 0, 0, montreal.getZoneName()), 175, 1.25, 300));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 8, 26, 12, 30, 0, 0, winnipeg.getZoneName()), 200, 2.3, 260));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 8, 26, 20, 30, 0, 0, calgary.getZoneName()), 200, 1.25, 160));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 29, 13, 30, 0, 0, montreal.getZoneName()), 100, 4.75, 200));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 8, 17, 12, 30, 0, 0, montreal.getZoneName()), 200, 2.6, 120));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 28, 14, 30, 0, 0, montreal.getZoneName()), 125, 2.6, 180));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 8, 25, 10, 0, 0, 0, vancouver.getZoneName()), 175, 1.3, 240));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 25, 17, 0, 0, 0, regina.getZoneName()), 150, 1.5, 260));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 16, 7, 0, 0, 0, calgary.getZoneName()), 125, 3.25, 120));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 9, 1, 30, 0, 0, regina.getZoneName()), 125, 4.0, 300));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 31, 21, 30, 0, 0, montreal.getZoneName()), 150, 2.6, 160));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 8, 14, 15, 0, 0, 0, calgary.getZoneName()), 175, 1.25, 160));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 8, 17, 16, 30, 0, 0, regina.getZoneName()), 200, 4.0, 160));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 8, 20, 15, 30, 0, 0, regina.getZoneName()), 125, 4.0, 260));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 8, 31, 18, 30, 0, 0, toronto.getZoneName()), 175, 2.3, 280));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 13, 21, 30, 0, 0, winnipeg.getZoneName()), 125, 2.0, 200));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 5, 6, 30, 0, 0, regina.getZoneName()), 125, 4.0, 220));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 8, 7, 2, 0, 0, 0, regina.getZoneName()), 100, 1.3, 260));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 8, 22, 16, 0, 0, 0, regina.getZoneName()), 100, 1.5, 120));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 30, 5, 0, 0, 0, toronto.getZoneName()), 150, 1.25, 120));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 8, 13, 19, 30, 0, 0, toronto.getZoneName()), 200, 1.25, 140));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 8, 29, 4, 30, 0, 0, calgary.getZoneName()), 100, 4.75, 300));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 8, 6, 15, 0, 0, 0, calgary.getZoneName()), 150, 1.5, 300));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 8, 26, 15, 30, 0, 0, toronto.getZoneName()), 125, 4.2, 200));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 26, 13, 30, 0, 0, regina.getZoneName()), 200, 4.2, 240));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 3, 20, 30, 0, 0, toronto.getZoneName()), 125, 4.2, 240));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 8, 19, 9, 0, 0, 0, montreal.getZoneName()), 175, 4.0, 280));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 7, 21, 20, 30, 0, 0, regina.getZoneName()), 150, 1.3, 220));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 8, 4, 2, 0, 0, 0, regina.getZoneName()), 125, 1.3, 200));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 8, 14, 4, 0, 0, 0, montreal.getZoneName()), 200, 4.75, 280));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 13, 10, 0, 0, 0, montreal.getZoneName()), 150, 4.75, 200));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 26, 23, 0, 0, 0, vancouver.getZoneName()), 100, 4.75, 220));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 8, 30, 18, 30, 0, 0, calgary.getZoneName()), 150, 1.25, 220));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 8, 23, 6, 30, 0, 0, vancouver.getZoneName()), 100, 1.3, 240));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 8, 30, 4, 0, 0, 0, calgary.getZoneName()), 100, 1.5, 220));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 18, 11, 30, 0, 0, montreal.getZoneName()), 200, 2.6, 140));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 7, 26, 23, 30, 0, 0, vancouver.getZoneName()), 175, 1.3, 140));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 8, 28, 15, 0, 0, 0, calgary.getZoneName()), 125, 4.75, 140));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 8, 31, 21, 0, 0, 0, regina.getZoneName()), 200, 2.0, 220));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 7, 24, 5, 0, 0, 0, regina.getZoneName()), 150, 1.3, 120));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 7, 30, 5, 0, 0, 0, vancouver.getZoneName()), 150, 1.3, 120));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 8, 24, 5, 30, 0, 0, montreal.getZoneName()), 175, 4.75, 200));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 8, 30, 6, 0, 0, 0, winnipeg.getZoneName()), 200, 2.6, 140));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 8, 18, 15, 30, 0, 0, calgary.getZoneName()), 200, 3.25, 120));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 10, 9, 0, 0, 0, montreal.getZoneName()), 150, 4.75, 140));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 8, 9, 12, 0, 0, 0, winnipeg.getZoneName()), 175, 1.25, 200));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 8, 16, 7, 30, 0, 0, regina.getZoneName()), 200, 1.5, 160));
        allFlights.add(new Flight(winnipeg, vancouver, ZonedDateTime.of(2022, 8, 28, 16, 0, 0, 0, winnipeg.getZoneName()), 175, 2.5, 120));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 8, 4, 17, 0, 0, 0, regina.getZoneName()), 175, 1.3, 100));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 26, 22, 0, 0, 0, regina.getZoneName()), 150, 4.2, 100));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 26, 12, 0, 0, 0, regina.getZoneName()), 150, 2.0, 180));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 8, 30, 19, 0, 0, 0, calgary.getZoneName()), 175, 1.25, 300));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 11, 21, 30, 0, 0, toronto.getZoneName()), 100, 2.3, 160));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 28, 12, 0, 0, 0, toronto.getZoneName()), 150, 4.5, 100));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 8, 9, 7, 30, 0, 0, calgary.getZoneName()), 125, 1.25, 180));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 7, 8, 13, 30, 0, 0, regina.getZoneName()), 200, 1.3, 280));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 8, 17, 23, 0, 0, 0, toronto.getZoneName()), 125, 4.5, 140));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 30, 10, 30, 0, 0, vancouver.getZoneName()), 100, 2.0, 200));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 8, 8, 2, 30, 0, 0, toronto.getZoneName()), 175, 1.25, 100));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 16, 0, 30, 0, 0, montreal.getZoneName()), 175, 4.0, 240));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 1, 13, 0, 0, 0, winnipeg.getZoneName()), 200, 2.3, 280));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 17, 18, 0, 0, 0, toronto.getZoneName()), 200, 4.5, 200));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 8, 20, 23, 0, 0, 0, regina.getZoneName()), 125, 4.0, 180));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 21, 0, 30, 0, 0, calgary.getZoneName()), 200, 4.75, 280));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 8, 20, 23, 30, 0, 0, montreal.getZoneName()), 200, 2.6, 140));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 16, 0, 0, 0, 0, calgary.getZoneName()), 125, 2.0, 160));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 14, 2, 0, 0, 0, winnipeg.getZoneName()), 125, 2.0, 100));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 27, 4, 30, 0, 0, montreal.getZoneName()), 175, 2.6, 220));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 27, 17, 0, 0, 0, regina.getZoneName()), 125, 2.0, 180));
        allFlights.add(new Flight(winnipeg, vancouver, ZonedDateTime.of(2022, 8, 22, 6, 0, 0, 0, winnipeg.getZoneName()), 150, 2.5, 240));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 15, 17, 0, 0, 0, regina.getZoneName()), 200, 4.2, 300));

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public String getAllFlights(ArrayList<Flight> resultList) {
        resultList.addAll(allFlights);
        return null;
    }

    //returns the locations
    public String getLocations(ArrayList<Location> resultList) {
        resultList.addAll(allLocations);
        return null;
    }

    public String addBooking(Booking newBooking) {
        allBookings.add(newBooking);
        return null;
    }

    //to get object of User from username
    public User getUserObject(String username) {
        User result = null;
        for (int i = 0; i < allUsers.size(); i++) {
            if (Objects.equals(allUsers.get(i).getUsername(), username)) {
                return result = allUsers.get(i);
            }
        }
        return null;
    }

    public String getUserBookings(User user, ArrayList<Booking> userBookings) {
        for (int i = 0; i < allBookings.size(); i++) {
            if (allBookings.get(i).getBooker() == user) {
                userBookings.add(allBookings.get(i));
            }
        }

        if (userBookings.isEmpty())
            return "no bookings found";
        else
            return null;
    }

    public String cancelBooking(int bookingId) {
        boolean removed = false;

        for (int i = 0; i < allBookings.size() && !removed; i++) {
            if (allBookings.get(i).getBookingId() == bookingId) {
                allBookings.remove(i);
                removed = true;
            }
        }

        return null;
    }

    public String getFlights(Location source, Location dest, ZonedDateTime departureDate, ArrayList<Flight> resultList) {
        Flight currentFlight;
        int currentDay;
        int currentMonth;
        int currentYear;

        for (int i = 0; i < allFlights.size(); i++) {
            currentFlight = allFlights.get(i);
            currentDay = currentFlight.getDepartureDateTime().getDayOfMonth();
            currentMonth = currentFlight.getDepartureDateTime().getMonthValue();
            currentYear = currentFlight.getDepartureDateTime().getYear();

            if (currentFlight.getSource().equals(source)      &&
                currentFlight.getDestination().equals(dest)   &&
                currentDay == departureDate.getDayOfMonth()   &&
                currentMonth == departureDate.getMonthValue() &&
                currentYear == departureDate.getYear()) {
                    resultList.add(currentFlight);
            }
        }

        return null;
    }
}

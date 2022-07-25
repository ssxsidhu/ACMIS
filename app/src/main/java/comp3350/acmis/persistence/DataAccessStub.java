package comp3350.acmis.persistence;


import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        defaultUser = new User("John", "Braico", User.Gender.MALE, "braico", "somePassword", "jbraico@cs.umanitoba.ca", "2041234567");
        allUsers.add(defaultUser);

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

        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 23, 8, 30, 0, 0, winnipeg.getZoneName()), 125, 1.25, 180));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 23, 2, 30, 0, 0, winnipeg.getZoneName()), 200, 2.0, 300));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 23, 19, 30, 0, 0, winnipeg.getZoneName()), 175, 2.0, 120));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 23, 3, 0, 0, 0, winnipeg.getZoneName()), 175, 2.0, 160));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 23, 10, 30, 0, 0, winnipeg.getZoneName()), 200, 2.0, 160));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 7, 23, 21, 0, 0, 0, vancouver.getZoneName()), 125, 1.3, 100));
        allFlights.add(new Flight(vancouver, winnipeg, ZonedDateTime.of(2022, 7, 23, 15, 0, 0, 0, vancouver.getZoneName()), 100, 2.5, 240));
        allFlights.add(new Flight(vancouver, winnipeg, ZonedDateTime.of(2022, 7, 23, 17, 0, 0, 0, vancouver.getZoneName()), 150, 2.5, 120));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 23, 8, 30, 0, 0, vancouver.getZoneName()), 125, 4.75, 100));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 23, 9, 0, 0, 0, vancouver.getZoneName()), 150, 2.0, 180));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 23, 14, 0, 0, 0, toronto.getZoneName()), 125, 1.25, 300));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 23, 7, 0, 0, 0, toronto.getZoneName()), 125, 4.5, 300));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 23, 15, 30, 0, 0, toronto.getZoneName()), 125, 1.25, 220));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 23, 2, 30, 0, 0, toronto.getZoneName()), 100, 1.25, 280));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 23, 14, 30, 0, 0, toronto.getZoneName()), 125, 2.3, 200));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 23, 10, 30, 0, 0, calgary.getZoneName()), 125, 1.25, 300));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 23, 8, 0, 0, 0, calgary.getZoneName()), 200, 3.25, 100));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 23, 5, 0, 0, 0, calgary.getZoneName()), 175, 4.75, 220));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 7, 23, 15, 30, 0, 0, calgary.getZoneName()), 175, 1.5, 200));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 23, 1, 0, 0, 0, calgary.getZoneName()), 175, 1.25, 220));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 23, 18, 30, 0, 0, regina.getZoneName()), 125, 4.2, 180));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 23, 6, 0, 0, 0, regina.getZoneName()), 125, 4.2, 180));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 23, 9, 0, 0, 0, regina.getZoneName()), 125, 1.5, 120));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 23, 14, 0, 0, 0, regina.getZoneName()), 125, 2.0, 260));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 23, 8, 0, 0, 0, regina.getZoneName()), 100, 4.2, 180));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 23, 1, 30, 0, 0, montreal.getZoneName()), 175, 4.75, 300));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 23, 8, 30, 0, 0, montreal.getZoneName()), 125, 4.0, 240));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 23, 4, 30, 0, 0, montreal.getZoneName()), 125, 4.75, 240));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 23, 13, 0, 0, 0, montreal.getZoneName()), 150, 2.6, 180));
        allFlights.add(new Flight(montreal, toronto, ZonedDateTime.of(2022, 7, 23, 23, 0, 0, 0, montreal.getZoneName()), 125, 1.25, 100));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 24, 7, 0, 0, 0, winnipeg.getZoneName()), 175, 2.0, 260));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 24, 10, 0, 0, 0, winnipeg.getZoneName()), 200, 2.3, 180));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 24, 6, 0, 0, 0, winnipeg.getZoneName()), 150, 1.25, 100));
        allFlights.add(new Flight(winnipeg, vancouver, ZonedDateTime.of(2022, 7, 24, 9, 30, 0, 0, winnipeg.getZoneName()), 200, 2.5, 280));
        allFlights.add(new Flight(winnipeg, vancouver, ZonedDateTime.of(2022, 7, 24, 11, 30, 0, 0, winnipeg.getZoneName()), 125, 2.5, 240));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 24, 13, 30, 0, 0, vancouver.getZoneName()), 200, 4.75, 240));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 24, 2, 0, 0, 0, vancouver.getZoneName()), 200, 4.75, 260));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 7, 24, 23, 0, 0, 0, vancouver.getZoneName()), 100, 1.3, 120));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 24, 9, 0, 0, 0, vancouver.getZoneName()), 100, 2.0, 260));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 24, 1, 30, 0, 0, vancouver.getZoneName()), 175, 2.0, 240));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 24, 9, 30, 0, 0, toronto.getZoneName()), 175, 4.2, 180));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 24, 11, 30, 0, 0, toronto.getZoneName()), 125, 4.5, 140));
        allFlights.add(new Flight(toronto, calgary, ZonedDateTime.of(2022, 7, 24, 20, 30, 0, 0, toronto.getZoneName()), 150, 3.25, 100));
        allFlights.add(new Flight(toronto, calgary, ZonedDateTime.of(2022, 7, 24, 1, 30, 0, 0, toronto.getZoneName()), 100, 3.25, 240));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 24, 4, 30, 0, 0, toronto.getZoneName()), 125, 2.3, 160));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 7, 24, 2, 0, 0, 0, calgary.getZoneName()), 175, 1.5, 280));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 7, 24, 23, 0, 0, 0, calgary.getZoneName()), 175, 1.5, 240));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 24, 3, 30, 0, 0, calgary.getZoneName()), 100, 3.25, 200));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 24, 12, 0, 0, 0, calgary.getZoneName()), 200, 4.75, 180));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 24, 1, 30, 0, 0, calgary.getZoneName()), 150, 2.0, 300));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 24, 17, 0, 0, 0, regina.getZoneName()), 100, 1.5, 260));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 24, 19, 0, 0, 0, regina.getZoneName()), 175, 4.0, 120));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 7, 24, 1, 0, 0, 0, regina.getZoneName()), 100, 1.3, 100));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 24, 1, 0, 0, 0, regina.getZoneName()), 125, 2.0, 200));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 24, 14, 0, 0, 0, regina.getZoneName()), 100, 1.5, 280));
        allFlights.add(new Flight(montreal, toronto, ZonedDateTime.of(2022, 7, 24, 22, 0, 0, 0, montreal.getZoneName()), 150, 1.25, 140));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 24, 21, 30, 0, 0, montreal.getZoneName()), 125, 4.0, 300));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 7, 24, 7, 0, 0, 0, montreal.getZoneName()), 200, 4.75, 200));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 24, 20, 0, 0, 0, montreal.getZoneName()), 125, 4.75, 160));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 24, 15, 0, 0, 0, montreal.getZoneName()), 125, 4.0, 220));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 25, 20, 0, 0, 0, winnipeg.getZoneName()), 175, 1.25, 300));
        allFlights.add(new Flight(winnipeg, vancouver, ZonedDateTime.of(2022, 7, 25, 1, 30, 0, 0, winnipeg.getZoneName()), 125, 2.5, 160));
        allFlights.add(new Flight(winnipeg, vancouver, ZonedDateTime.of(2022, 7, 25, 1, 0, 0, 0, winnipeg.getZoneName()), 200, 2.5, 200));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 7, 25, 11, 30, 0, 0, winnipeg.getZoneName()), 175, 2.6, 120));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 7, 25, 1, 30, 0, 0, winnipeg.getZoneName()), 175, 2.6, 120));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 25, 7, 30, 0, 0, vancouver.getZoneName()), 125, 2.0, 300));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 25, 3, 30, 0, 0, vancouver.getZoneName()), 100, 2.0, 140));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 25, 10, 30, 0, 0, vancouver.getZoneName()), 175, 4.75, 120));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 25, 18, 0, 0, 0, vancouver.getZoneName()), 150, 2.0, 300));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 7, 25, 15, 30, 0, 0, vancouver.getZoneName()), 200, 1.3, 240));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 25, 15, 30, 0, 0, toronto.getZoneName()), 125, 1.25, 100));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 25, 2, 30, 0, 0, toronto.getZoneName()), 125, 1.25, 200));
        allFlights.add(new Flight(toronto, calgary, ZonedDateTime.of(2022, 7, 25, 16, 0, 0, 0, toronto.getZoneName()), 125, 3.25, 200));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 25, 6, 0, 0, 0, toronto.getZoneName()), 100, 4.5, 260));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 25, 20, 30, 0, 0, toronto.getZoneName()), 100, 1.25, 160));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 25, 6, 0, 0, 0, calgary.getZoneName()), 175, 2.0, 180));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 25, 8, 30, 0, 0, calgary.getZoneName()), 150, 1.25, 200));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 25, 15, 30, 0, 0, calgary.getZoneName()), 175, 1.25, 260));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 25, 22, 0, 0, 0, calgary.getZoneName()), 200, 2.0, 280));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 25, 9, 30, 0, 0, calgary.getZoneName()), 100, 2.0, 100));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 25, 19, 30, 0, 0, regina.getZoneName()), 200, 4.0, 200));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 25, 2, 0, 0, 0, regina.getZoneName()), 175, 4.0, 240));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 25, 20, 0, 0, 0, regina.getZoneName()), 150, 2.0, 120));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 25, 12, 30, 0, 0, regina.getZoneName()), 175, 1.5, 300));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 25, 4, 0, 0, 0, regina.getZoneName()), 175, 4.2, 280));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 25, 5, 30, 0, 0, montreal.getZoneName()), 175, 4.0, 240));
        allFlights.add(new Flight(montreal, toronto, ZonedDateTime.of(2022, 7, 25, 4, 30, 0, 0, montreal.getZoneName()), 125, 1.25, 300));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 25, 20, 0, 0, 0, montreal.getZoneName()), 100, 4.75, 100));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 25, 5, 30, 0, 0, montreal.getZoneName()), 200, 4.0, 200));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 25, 1, 30, 0, 0, montreal.getZoneName()), 175, 4.0, 220));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 26, 10, 0, 0, 0, winnipeg.getZoneName()), 150, 1.25, 140));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 26, 10, 30, 0, 0, winnipeg.getZoneName()), 175, 2.3, 100));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 26, 6, 0, 0, 0, winnipeg.getZoneName()), 125, 2.3, 300));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 26, 8, 30, 0, 0, winnipeg.getZoneName()), 200, 1.25, 100));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 26, 7, 30, 0, 0, winnipeg.getZoneName()), 100, 2.0, 260));
        allFlights.add(new Flight(vancouver, winnipeg, ZonedDateTime.of(2022, 7, 26, 18, 0, 0, 0, vancouver.getZoneName()), 125, 2.5, 240));
        allFlights.add(new Flight(vancouver, toronto, ZonedDateTime.of(2022, 7, 26, 10, 0, 0, 0, vancouver.getZoneName()), 200, 4.5, 100));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 26, 20, 0, 0, 0, vancouver.getZoneName()), 125, 2.0, 180));
        allFlights.add(new Flight(vancouver, toronto, ZonedDateTime.of(2022, 7, 26, 7, 30, 0, 0, vancouver.getZoneName()), 200, 4.5, 140));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 26, 11, 0, 0, 0, vancouver.getZoneName()), 200, 2.0, 180));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 26, 16, 30, 0, 0, toronto.getZoneName()), 100, 2.3, 180));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 26, 11, 30, 0, 0, toronto.getZoneName()), 100, 2.3, 240));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 26, 1, 0, 0, 0, toronto.getZoneName()), 150, 2.3, 120));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 26, 15, 30, 0, 0, toronto.getZoneName()), 125, 2.3, 140));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 26, 3, 30, 0, 0, toronto.getZoneName()), 100, 4.2, 300));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 26, 11, 0, 0, 0, calgary.getZoneName()), 100, 2.0, 220));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 26, 16, 0, 0, 0, calgary.getZoneName()), 200, 2.0, 220));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 26, 20, 30, 0, 0, calgary.getZoneName()), 150, 2.0, 280));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 26, 6, 30, 0, 0, calgary.getZoneName()), 200, 4.75, 300));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 26, 22, 0, 0, 0, calgary.getZoneName()), 125, 1.25, 280));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 26, 3, 30, 0, 0, regina.getZoneName()), 125, 4.2, 280));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 26, 13, 30, 0, 0, regina.getZoneName()), 150, 1.5, 240));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 26, 6, 0, 0, 0, regina.getZoneName()), 125, 4.2, 260));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 26, 13, 0, 0, 0, regina.getZoneName()), 150, 1.5, 180));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 26, 10, 0, 0, 0, regina.getZoneName()), 100, 2.0, 180));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 26, 7, 0, 0, 0, montreal.getZoneName()), 125, 4.75, 240));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 7, 26, 19, 30, 0, 0, montreal.getZoneName()), 125, 4.75, 200));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 26, 3, 30, 0, 0, montreal.getZoneName()), 175, 2.6, 300));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 26, 2, 30, 0, 0, montreal.getZoneName()), 175, 4.75, 240));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 26, 16, 0, 0, 0, montreal.getZoneName()), 125, 4.0, 300));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 27, 22, 0, 0, 0, winnipeg.getZoneName()), 150, 2.3, 180));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 7, 27, 3, 0, 0, 0, winnipeg.getZoneName()), 100, 2.6, 220));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 27, 15, 0, 0, 0, winnipeg.getZoneName()), 100, 1.25, 260));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 27, 15, 30, 0, 0, winnipeg.getZoneName()), 150, 1.25, 140));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 7, 27, 8, 0, 0, 0, winnipeg.getZoneName()), 125, 2.6, 140));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 27, 17, 0, 0, 0, vancouver.getZoneName()), 175, 2.0, 240));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 27, 16, 0, 0, 0, vancouver.getZoneName()), 175, 4.75, 160));
        allFlights.add(new Flight(vancouver, toronto, ZonedDateTime.of(2022, 7, 27, 3, 0, 0, 0, vancouver.getZoneName()), 100, 4.5, 100));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 7, 27, 16, 30, 0, 0, vancouver.getZoneName()), 200, 1.3, 260));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 27, 5, 30, 0, 0, vancouver.getZoneName()), 150, 4.75, 260));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 27, 8, 30, 0, 0, toronto.getZoneName()), 125, 4.2, 180));
        allFlights.add(new Flight(toronto, calgary, ZonedDateTime.of(2022, 7, 27, 22, 0, 0, 0, toronto.getZoneName()), 200, 3.25, 180));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 27, 14, 0, 0, 0, toronto.getZoneName()), 200, 1.25, 160));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 27, 4, 0, 0, 0, toronto.getZoneName()), 150, 1.25, 200));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 27, 14, 0, 0, 0, toronto.getZoneName()), 150, 4.2, 180));
        allFlights.add(new Flight(calgary, vancouver, ZonedDateTime.of(2022, 7, 27, 16, 0, 0, 0, calgary.getZoneName()), 100, 2.0, 240));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 27, 20, 0, 0, 0, calgary.getZoneName()), 200, 1.25, 240));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 27, 3, 0, 0, 0, calgary.getZoneName()), 100, 4.75, 100));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 27, 19, 0, 0, 0, calgary.getZoneName()), 150, 1.25, 180));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 27, 5, 30, 0, 0, calgary.getZoneName()), 150, 3.25, 100));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 27, 13, 0, 0, 0, regina.getZoneName()), 100, 2.0, 220));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 7, 27, 19, 0, 0, 0, regina.getZoneName()), 150, 1.3, 140));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 27, 13, 0, 0, 0, regina.getZoneName()), 175, 4.2, 120));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 7, 27, 3, 30, 0, 0, regina.getZoneName()), 150, 1.3, 300));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 27, 19, 0, 0, 0, regina.getZoneName()), 200, 4.2, 160));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 27, 9, 0, 0, 0, montreal.getZoneName()), 100, 4.75, 100));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 27, 15, 0, 0, 0, montreal.getZoneName()), 200, 4.0, 100));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 27, 5, 0, 0, 0, montreal.getZoneName()), 150, 4.0, 200));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 27, 6, 30, 0, 0, montreal.getZoneName()), 175, 4.0, 300));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 27, 16, 30, 0, 0, montreal.getZoneName()), 150, 2.6, 100));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 28, 15, 30, 0, 0, winnipeg.getZoneName()), 200, 2.3, 140));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 7, 28, 20, 30, 0, 0, winnipeg.getZoneName()), 200, 2.6, 260));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 28, 11, 0, 0, 0, winnipeg.getZoneName()), 175, 2.3, 100));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 28, 6, 0, 0, 0, winnipeg.getZoneName()), 100, 2.0, 100));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 28, 23, 30, 0, 0, winnipeg.getZoneName()), 200, 2.3, 260));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 28, 8, 30, 0, 0, vancouver.getZoneName()), 175, 4.75, 260));
        allFlights.add(new Flight(vancouver, winnipeg, ZonedDateTime.of(2022, 7, 28, 17, 0, 0, 0, vancouver.getZoneName()), 150, 2.5, 160));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 28, 20, 0, 0, 0, vancouver.getZoneName()), 100, 4.75, 260));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 28, 19, 30, 0, 0, vancouver.getZoneName()), 100, 4.75, 100));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 28, 19, 0, 0, 0, vancouver.getZoneName()), 125, 2.0, 300));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 28, 5, 0, 0, 0, toronto.getZoneName()), 150, 4.5, 200));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 28, 12, 30, 0, 0, toronto.getZoneName()), 200, 2.3, 180));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 28, 6, 0, 0, 0, toronto.getZoneName()), 125, 4.2, 200));
        allFlights.add(new Flight(toronto, calgary, ZonedDateTime.of(2022, 7, 28, 16, 0, 0, 0, toronto.getZoneName()), 150, 3.25, 220));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 28, 12, 30, 0, 0, toronto.getZoneName()), 150, 4.2, 120));
        allFlights.add(new Flight(calgary, regina, ZonedDateTime.of(2022, 7, 28, 16, 30, 0, 0, calgary.getZoneName()), 125, 1.5, 220));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 28, 1, 30, 0, 0, calgary.getZoneName()), 200, 3.25, 200));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 28, 21, 0, 0, 0, calgary.getZoneName()), 100, 1.25, 240));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 28, 13, 0, 0, 0, calgary.getZoneName()), 150, 1.25, 260));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 28, 6, 30, 0, 0, calgary.getZoneName()), 175, 4.75, 260));
        allFlights.add(new Flight(regina, winnipeg, ZonedDateTime.of(2022, 7, 28, 13, 30, 0, 0, regina.getZoneName()), 175, 2.0, 200));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 28, 13, 0, 0, 0, regina.getZoneName()), 200, 4.2, 280));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 7, 28, 0, 30, 0, 0, regina.getZoneName()), 125, 1.3, 160));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 28, 7, 0, 0, 0, regina.getZoneName()), 175, 4.0, 160));
        allFlights.add(new Flight(regina, vancouver, ZonedDateTime.of(2022, 7, 28, 9, 0, 0, 0, regina.getZoneName()), 200, 1.3, 180));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 28, 5, 30, 0, 0, montreal.getZoneName()), 150, 2.6, 160));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 28, 8, 30, 0, 0, montreal.getZoneName()), 175, 4.0, 280));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 28, 14, 30, 0, 0, montreal.getZoneName()), 200, 4.0, 300));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 28, 12, 30, 0, 0, montreal.getZoneName()), 100, 2.6, 200));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 28, 4, 30, 0, 0, montreal.getZoneName()), 100, 2.6, 220));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 29, 16, 30, 0, 0, winnipeg.getZoneName()), 125, 1.25, 260));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 29, 18, 0, 0, 0, winnipeg.getZoneName()), 125, 2.0, 240));
        allFlights.add(new Flight(winnipeg, vancouver, ZonedDateTime.of(2022, 7, 29, 18, 0, 0, 0, winnipeg.getZoneName()), 100, 2.5, 260));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 29, 5, 30, 0, 0, winnipeg.getZoneName()), 150, 1.25, 100));
        allFlights.add(new Flight(winnipeg, regina, ZonedDateTime.of(2022, 7, 29, 23, 30, 0, 0, winnipeg.getZoneName()), 100, 2.0, 120));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 7, 29, 12, 0, 0, 0, vancouver.getZoneName()), 100, 1.3, 100));
        allFlights.add(new Flight(vancouver, winnipeg, ZonedDateTime.of(2022, 7, 29, 20, 0, 0, 0, vancouver.getZoneName()), 100, 2.5, 280));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 29, 10, 30, 0, 0, vancouver.getZoneName()), 200, 2.0, 240));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 29, 22, 0, 0, 0, vancouver.getZoneName()), 100, 4.75, 260));
        allFlights.add(new Flight(vancouver, regina, ZonedDateTime.of(2022, 7, 29, 6, 30, 0, 0, vancouver.getZoneName()), 200, 1.3, 120));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 29, 22, 30, 0, 0, toronto.getZoneName()), 175, 4.2, 200));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 29, 17, 0, 0, 0, toronto.getZoneName()), 125, 4.2, 240));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 29, 22, 30, 0, 0, toronto.getZoneName()), 175, 2.3, 280));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 29, 0, 0, 0, 0, toronto.getZoneName()), 150, 2.3, 260));
        allFlights.add(new Flight(toronto, calgary, ZonedDateTime.of(2022, 7, 29, 1, 0, 0, 0, toronto.getZoneName()), 100, 3.25, 220));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 29, 3, 30, 0, 0, calgary.getZoneName()), 200, 3.25, 160));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 29, 11, 30, 0, 0, calgary.getZoneName()), 150, 1.25, 140));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 29, 16, 30, 0, 0, calgary.getZoneName()), 200, 4.75, 120));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 29, 1, 0, 0, 0, calgary.getZoneName()), 100, 4.75, 260));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 29, 17, 30, 0, 0, calgary.getZoneName()), 125, 1.25, 140));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 29, 21, 30, 0, 0, regina.getZoneName()), 100, 4.0, 160));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 29, 9, 0, 0, 0, regina.getZoneName()), 175, 1.5, 240));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 29, 10, 0, 0, 0, regina.getZoneName()), 100, 4.2, 180));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 29, 21, 30, 0, 0, regina.getZoneName()), 125, 4.0, 140));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 29, 3, 0, 0, 0, regina.getZoneName()), 100, 1.5, 140));
        allFlights.add(new Flight(montreal, toronto, ZonedDateTime.of(2022, 7, 29, 1, 0, 0, 0, montreal.getZoneName()), 125, 1.25, 180));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 7, 29, 10, 30, 0, 0, montreal.getZoneName()), 125, 4.75, 140));
        allFlights.add(new Flight(montreal, regina, ZonedDateTime.of(2022, 7, 29, 7, 0, 0, 0, montreal.getZoneName()), 150, 4.0, 140));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 7, 29, 6, 0, 0, 0, montreal.getZoneName()), 150, 4.75, 180));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 7, 29, 5, 30, 0, 0, montreal.getZoneName()), 100, 4.75, 180));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 30, 3, 30, 0, 0, winnipeg.getZoneName()), 200, 1.25, 240));
        allFlights.add(new Flight(winnipeg, toronto, ZonedDateTime.of(2022, 7, 30, 3, 30, 0, 0, winnipeg.getZoneName()), 200, 2.3, 180));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 7, 30, 2, 30, 0, 0, winnipeg.getZoneName()), 200, 2.6, 100));
        allFlights.add(new Flight(winnipeg, montreal, ZonedDateTime.of(2022, 7, 30, 16, 30, 0, 0, winnipeg.getZoneName()), 100, 2.6, 240));
        allFlights.add(new Flight(winnipeg, calgary, ZonedDateTime.of(2022, 7, 30, 13, 0, 0, 0, winnipeg.getZoneName()), 100, 1.25, 260));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 30, 17, 30, 0, 0, vancouver.getZoneName()), 100, 2.0, 240));
        allFlights.add(new Flight(vancouver, toronto, ZonedDateTime.of(2022, 7, 30, 1, 30, 0, 0, vancouver.getZoneName()), 100, 4.5, 280));
        allFlights.add(new Flight(vancouver, montreal, ZonedDateTime.of(2022, 7, 30, 21, 0, 0, 0, vancouver.getZoneName()), 100, 4.75, 260));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 30, 5, 30, 0, 0, vancouver.getZoneName()), 175, 2.0, 100));
        allFlights.add(new Flight(vancouver, calgary, ZonedDateTime.of(2022, 7, 30, 5, 0, 0, 0, vancouver.getZoneName()), 125, 2.0, 160));
        allFlights.add(new Flight(toronto, vancouver, ZonedDateTime.of(2022, 7, 30, 7, 0, 0, 0, toronto.getZoneName()), 200, 4.5, 120));
        allFlights.add(new Flight(toronto, regina, ZonedDateTime.of(2022, 7, 30, 6, 30, 0, 0, toronto.getZoneName()), 100, 4.2, 260));
        allFlights.add(new Flight(toronto, calgary, ZonedDateTime.of(2022, 7, 30, 18, 0, 0, 0, toronto.getZoneName()), 150, 3.25, 120));
        allFlights.add(new Flight(toronto, winnipeg, ZonedDateTime.of(2022, 7, 30, 11, 30, 0, 0, toronto.getZoneName()), 150, 2.3, 140));
        allFlights.add(new Flight(toronto, montreal, ZonedDateTime.of(2022, 7, 30, 5, 0, 0, 0, toronto.getZoneName()), 200, 1.25, 160));
        allFlights.add(new Flight(calgary, winnipeg, ZonedDateTime.of(2022, 7, 30, 6, 30, 0, 0, calgary.getZoneName()), 125, 1.25, 300));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 30, 10, 0, 0, 0, calgary.getZoneName()), 175, 3.25, 260));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 30, 20, 30, 0, 0, calgary.getZoneName()), 125, 3.25, 140));
        allFlights.add(new Flight(calgary, montreal, ZonedDateTime.of(2022, 7, 30, 3, 0, 0, 0, calgary.getZoneName()), 150, 4.75, 280));
        allFlights.add(new Flight(calgary, toronto, ZonedDateTime.of(2022, 7, 30, 14, 0, 0, 0, calgary.getZoneName()), 150, 3.25, 300));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 30, 16, 30, 0, 0, regina.getZoneName()), 125, 1.5, 280));
        allFlights.add(new Flight(regina, toronto, ZonedDateTime.of(2022, 7, 30, 20, 30, 0, 0, regina.getZoneName()), 125, 4.2, 120));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 30, 16, 0, 0, 0, regina.getZoneName()), 100, 1.5, 140));
        allFlights.add(new Flight(regina, calgary, ZonedDateTime.of(2022, 7, 30, 11, 0, 0, 0, regina.getZoneName()), 200, 1.5, 220));
        allFlights.add(new Flight(regina, montreal, ZonedDateTime.of(2022, 7, 30, 17, 0, 0, 0, regina.getZoneName()), 125, 4.0, 180));
        allFlights.add(new Flight(montreal, toronto, ZonedDateTime.of(2022, 7, 30, 19, 0, 0, 0, montreal.getZoneName()), 175, 1.25, 240));
        allFlights.add(new Flight(montreal, calgary, ZonedDateTime.of(2022, 7, 30, 1, 30, 0, 0, montreal.getZoneName()), 200, 4.75, 160));
        allFlights.add(new Flight(montreal, winnipeg, ZonedDateTime.of(2022, 7, 30, 12, 0, 0, 0, montreal.getZoneName()), 175, 2.6, 120));
        allFlights.add(new Flight(montreal, toronto, ZonedDateTime.of(2022, 7, 30, 20, 0, 0, 0, montreal.getZoneName()), 150, 1.25, 200));
        allFlights.add(new Flight(montreal, vancouver, ZonedDateTime.of(2022, 7, 30, 3, 30, 0, 0, montreal.getZoneName()), 125, 4.75, 260));


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

        Collections.sort(resultList, new Comparator<Flight>() {
            @Override
            public int compare(Flight f1, Flight f2) {
                return (f1.getDepartureDateTime().isBefore(f2.getDepartureDateTime()) ? -1 : 1);
            }
        });

        return null;
    }
}

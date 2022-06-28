package comp3350.acmis.persistence;



import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import comp3350.acmis.application.Main;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;

public class DataAccessStub {
    private String dbName;
    private String dbType = "stub";
    private ArrayList<User> allUsers;
    private ArrayList<Flight> allFlights;
    private ArrayList<Location> allLocations;//tp store city,country,airport and other rdata later
    private ArrayList<Booking> allBookings;
    private BookingManager bookingManager;


    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
        allUsers = new ArrayList<>();
        allLocations = new ArrayList<>();
        allFlights =  new ArrayList<Flight>();
        allBookings = new ArrayList<>();
    }

    public DataAccessStub() {
        this(Main.dbName);
        allUsers = new ArrayList<>();
        allLocations = new ArrayList<>();
        allFlights =  new ArrayList<Flight>();
        allBookings = new ArrayList<>();
    }


    public void open(String dbName) {
        User user,defaultUser;

        bookingManager = new BookingManager();

        defaultUser = new User("Johnny","victor", User.Gender.MALE,"default","jOhnNNyVi12","johnny.victor@gmail.com","2045558999");
        allUsers.add(defaultUser);
        user = new User("Julie","smith", User.Gender.FEMALE,"jsmith","j&smith$","jmith@gmail.com","2048889999");
        allUsers.add(user);

        Location winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada","YWG");
        allLocations.add(winnipeg);
        Location montreal = new Location("Montreal", ZoneId.of("America/Montreal"), "Canada","YUL");
        allLocations.add(montreal);
        Location toronto = new Location("Toronto", ZoneId.of("America/Toronto"), "Canada","YYZ");
        allLocations.add(toronto);
        Location vancouver = new Location("Vancouver", ZoneId.of("America/Vancouver"), "Canada","YVR");
        allLocations.add(vancouver);
        Location calgary = new Location("Calgary", ZoneId.of("America/Edmonton"), "Canada","YYC");
        allLocations.add(calgary);
        Location regina = new Location("Regina", ZoneId.of("America/Regina"), "Canada","YQR");
        allLocations.add(regina);



        //Winnipeg to montreal
        Flight winToMn = new Flight(winnipeg,montreal, ZonedDateTime.of(2022,6,11,7,30,0,0,winnipeg.getZoneName()), 250, 2.5, 500);
        winnipeg.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(winnipeg);
        allFlights.add(winToMn);
        Flight winToMn2 = new Flight(winnipeg,montreal, ZonedDateTime.of(2022,6,19,7,30,0,0,winnipeg.getZoneName()), 250, 2.5, 500);
        winnipeg.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(winnipeg);
        allFlights.add(winToMn2);
        Flight winToMn3 = new Flight(winnipeg,montreal,  ZonedDateTime.of(2022,7,7,7,30,0,0,winnipeg.getZoneName()), 250, 2.5, 1500);
        winnipeg.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(winnipeg);
        allFlights.add(winToMn3);

        //winnipeg to toronto
        Flight winToTor = new Flight(winnipeg,toronto,  ZonedDateTime.of(2022,6,13,5,30,0,0,winnipeg.getZoneName()), 150, 2.6, 750);
        winnipeg.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(winnipeg);
        allFlights.add(winToTor);
        Flight winToTor2 = new Flight(winnipeg,toronto,  ZonedDateTime.of(2022,6,13,5,30,0,0,winnipeg.getZoneName()), 150, 2.6, 750);
        winnipeg.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(winnipeg);
        allFlights.add(winToTor2);

        //winnipeg to vancouver
        Flight winToVan = new Flight(winnipeg,vancouver,  ZonedDateTime.of(2022,8,1,11,30,0,0,winnipeg.getZoneName()), 250, 3.0, 1000);
        winnipeg.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(winnipeg);
        allFlights.add(winToVan);


        Flight winToReg = new Flight(winnipeg,regina, ZonedDateTime.of(2022,7,14,15,30,0,0,winnipeg.getZoneName()), 100, 3.0, 200);
        winnipeg.addLocationOutgoing(regina);
        regina.addLocationIncoming(winnipeg);
        allFlights.add(winToReg);
        Flight winToReg2 = new Flight(winnipeg,montreal, ZonedDateTime.of(2022,7,21,15,30,0,0,winnipeg.getZoneName()), 100, 3.0, 200);
        winnipeg.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(winnipeg);
        allFlights.add(winToReg2);

        //toronto to vancouver
        Flight torToVan = new Flight(toronto,vancouver, ZonedDateTime.of(2022,9,4,13,15,0,0,toronto.getZoneName()), 300, 5.0, 1200);
        toronto.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(toronto);
        allFlights.add(torToVan);
        Flight torToVan2 = new Flight(toronto,vancouver,ZonedDateTime.of(2022,9,25,17,15,0,0,toronto.getZoneName()), 300, 5.0, 1200);
        toronto.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(toronto);
        allFlights.add(torToVan2);
        Flight torToVan3 = new Flight(toronto,vancouver, ZonedDateTime.of(2022,10,4,13,15,0,0,toronto.getZoneName()), 300, 5.0, 1200);
        toronto.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(toronto);
        allFlights.add(torToVan3);
        Flight torToVan4 = new Flight(toronto,vancouver,ZonedDateTime.of(2022,10,25,17,15,0,0,toronto.getZoneName()), 300, 5.0, 1200);
        toronto.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(toronto);
        allFlights.add(torToVan4);


        //toronto to montreal
        Flight torToMn = new Flight(toronto,montreal, ZonedDateTime.of(2022,10,5,6,0,0,0,toronto.getZoneName()), 300, 1.3, 100);
        toronto.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(toronto);
        allFlights.add(torToMn);

        //toronto to winnipeg
        Flight torToWn = new Flight(toronto,winnipeg, ZonedDateTime.of(2022,11,15,16,0,0,0,toronto.getZoneName()), 250, 2.6, 750);
        toronto.addLocationOutgoing(winnipeg);
        winnipeg.addLocationIncoming(toronto);
        allFlights.add(torToWn);

        //toronto to calgary
        Flight torToCal = new Flight(toronto,calgary, ZonedDateTime.of(2022,12,25,19,30,0,0,toronto.getZoneName()), 250, 4.1, 750);
        toronto.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(toronto);
        allFlights.add(torToCal);

        //regina to winnipeg
        Flight regToWin = new Flight(regina,winnipeg, ZonedDateTime.of(2022,8,28,1,30,0,0,regina.getZoneName()), 200, 1.3, 120);
        regina.addLocationOutgoing(winnipeg);
        winnipeg.addLocationIncoming(regina);
        allFlights.add(regToWin);
        Flight regToWin2 = new Flight(regina,winnipeg, ZonedDateTime.of(2022,9,25,2,0,0,0,regina.getZoneName()), 200, 1.3, 120);
        regina.addLocationOutgoing(winnipeg);
        winnipeg.addLocationIncoming(regina);
        allFlights.add(regToWin2);
        Flight regToWin3 = new Flight(regina,winnipeg, ZonedDateTime.of(2022,10,25,3,0,0,0,regina.getZoneName()), 200, 1.3, 120);
        regina.addLocationOutgoing(winnipeg);
        winnipeg.addLocationIncoming(regina);
        allFlights.add(regToWin3);


        //regina to calgary
        Flight regToCal = new Flight(regina,calgary,ZonedDateTime.of(2022,7,30,12,50,0,0,regina.getZoneName()), 100, 1.5, 180);
        regina.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(regina);
        allFlights.add(regToCal);
        Flight regToCal2 = new Flight(regina,calgary, ZonedDateTime.of(2022,9,10,12,50,0,0,regina.getZoneName()), 100, 1.5, 180);
        regina.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(regina);
        allFlights.add(regToCal2);

        //vancouver to calgary
        Flight vanToCal = new Flight(vancouver,calgary, ZonedDateTime.of(2022,7,29,20,50,0,0,vancouver.getZoneName()), 200, 1.4, 220);
        vancouver.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(vancouver);
        allFlights.add(vanToCal);
        Flight vanToCal2 = new Flight(vancouver,calgary, ZonedDateTime.of(2022,8,17,10,50,0,0,vancouver.getZoneName()), 200, 1.4, 220);
        vancouver.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(vancouver);
        allFlights.add(vanToCal2);

        //calgary to vancouver
        Flight calToVan = new Flight(calgary,vancouver, ZonedDateTime.of(2022,10,2,2,0,0,0,calgary.getZoneName()), 200, 1.4, 220);
        calgary.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(calgary);
        allFlights.add(calToVan);
        Flight calToVan1 = new Flight(calgary,vancouver,ZonedDateTime.of(2022,10,12,7,0,0,0,calgary.getZoneName()), 200, 1.4, 220);
        calgary.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(calgary);
        allFlights.add(calToVan1);
        Flight calToVan2 = new Flight(calgary,vancouver, ZonedDateTime.of(2022,10,25,18,0,0,0,calgary.getZoneName()), 200, 1.4, 220);
        calgary.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(calgary);
        allFlights.add(calToVan2);

        //calgary to toronto
        Flight calToTor = new Flight(calgary,toronto,ZonedDateTime.of(2022,7,2,15,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor);

        Flight calToTor1 = new Flight(calgary,toronto, ZonedDateTime.of(2022,7,9,11,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor1);

        Flight calToTor2 = new Flight(calgary,toronto, ZonedDateTime.of(2022,7,16,5,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor2);

        Flight calToTor3 = new Flight(calgary,toronto, ZonedDateTime.of(2022,7,30,21,0,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor3);

        Flight calToTor4 = new Flight(calgary,toronto, ZonedDateTime.of(2022,8,2,10,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor4);

        Flight calToTor5 = new Flight(calgary,toronto, ZonedDateTime.of(2022,8,11,12,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor5);

        Flight calToTor6 = new Flight(calgary,toronto, ZonedDateTime.of(2022,8,21,18,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor6);

        Flight calToTor7 = new Flight(calgary,toronto, ZonedDateTime.of(2022,9,24,3,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor7);

        Flight calToTor8 = new Flight(calgary,toronto, ZonedDateTime.of(2022,9,27,5,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor8);

        Flight calToTor9 = new Flight(calgary,toronto, ZonedDateTime.of(2022,10,18,17,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor9);

        Flight calToTor10 = new Flight(calgary,toronto, ZonedDateTime.of(2022,10,22,22,30,0,0,calgary.getZoneName()), 200, 4.2 , 300);
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor10);

        Flight montoTor = new Flight(montreal,toronto, ZonedDateTime.of(2022,8,27,15,0,0,0,montreal.getZoneName()), 200, 1.3 , 200);
        montreal.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(montreal);
        allFlights.add(montoTor);



        bookingManager.createBooking("default",new Route(Collections.singletonList(winToMn)));
        bookingManager.createBooking("default",new Route(Collections.singletonList(torToVan)));



        System.out.println("Opened " +dbType +" database " +dbName);
    }

    public void close(){
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public String getAllUsers(ArrayList<User> resultList) {
        resultList.addAll(allUsers);
        return null;
    }

    public String insertUser(User newUser) {
        allUsers.add(newUser);
        return null;
    }

    public String getAllFlights(ArrayList<Flight> resultList) {
        resultList.addAll(allFlights);
        return null;
    }

    public String insertFlight(Flight newFlight) {
        Location dest = newFlight.getDestination();
        Location source = newFlight.getSource();

        dest.addLocationIncoming(source);
        source.addLocationOutgoing(dest);

        allFlights.add(newFlight);
        return null;
    }

    public String insertLocation(Location newLocation) {
        allLocations.add(newLocation);
        return  null;
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
            if(Objects.equals(allUsers.get(i).getUsername(), username)) {
                result = allUsers.get(i);
            }
        }
        return  result;
    }

    //to get object of Flight from flightNumber
    public Flight getFlightObject(int flightNumber) {
        Flight result = null;
        for (int i = 0; i < allFlights.size(); i++) {
            if(allFlights.get(i).getFlightID() == flightNumber) {
                result = allFlights.get(i);
            }
        }
        return result;
    }

    //method for getting a specific booking from the db using the booking id
    public Booking getBooking(int bookingId) {
        for(int i = 0; i < allBookings.size(); i++){
            if(allBookings.get(i).getBookingId() == bookingId) {
                return allBookings.get(i);
            }
        }
        return  null;
    }
}

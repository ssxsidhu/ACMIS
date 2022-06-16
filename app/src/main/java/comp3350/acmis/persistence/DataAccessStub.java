package comp3350.acmis.persistence;


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

        Location winnipeg = new Location("Winnipeg","Canada","YWG");
        allLocations.add(winnipeg);
        Location montreal = new Location("Montreal","Canada","YUL");
        allLocations.add(montreal);
        Location toronto = new Location("Toronto","Canada","YYZ");
        allLocations.add(toronto);
        Location vancouver = new Location("Vancouver","Canada","YVR");
        allLocations.add(vancouver);
        Location calgary = new Location("Calgary","Canada","YYC");
        allLocations.add(calgary);
        Location regina = new Location("Regina","Canada","YQR");
        allLocations.add(regina);




        Flight winToMn = new Flight(winnipeg,montreal,"2022-06-14","10:30","2022-06-15","4:30");
        winnipeg.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(winnipeg);
        allFlights.add(winToMn);
        Flight winToMn2 = new Flight(winnipeg,montreal,"2022-07-14","11:30","2022-07-15","15:30");
        winnipeg.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(winnipeg);
        allFlights.add(winToMn2);
        Flight winToMn3 = new Flight(winnipeg,montreal,"2022-12-04","10:30","2022-12-05","14:30");
        winnipeg.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(winnipeg);
        allFlights.add(winToMn3);

        Flight winToTor = new Flight(winnipeg,toronto,"2022-07-04","14:30","2022-07-04","17:30");
        winnipeg.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(winnipeg);
        allFlights.add(winToTor);
        Flight winToTor2 = new Flight(winnipeg,toronto,"2022-12-24","17:30","2022-12-24","20:30");
        winnipeg.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(winnipeg);
        allFlights.add(winToTor2);

        Flight winToVan = new Flight(winnipeg,vancouver,"2022-10-26","17:10","2022-10-27","2:30");
        winnipeg.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(winnipeg);
        allFlights.add(winToVan);

        Flight winToReg = new Flight(winnipeg,regina,"2022-11-24","10:30","2022-11-24","12:30");
        winnipeg.addLocationOutgoing(regina);
        regina.addLocationIncoming(winnipeg);
        allFlights.add(winToReg);
        Flight winToReg2 = new Flight(winnipeg,montreal,"2022-09-15","10:30","2022-09-15","12:30");
        winnipeg.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(winnipeg);
        allFlights.add(winToReg2);

        Flight torToVan = new Flight(toronto,vancouver,"2022-06-19","16:30","2022-06-20","1:30");
        toronto.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(toronto);
        allFlights.add(torToVan);
        Flight torToVan2 = new Flight(toronto,vancouver,"2022-06-29","17:30","2022-06-30","2:30");
        toronto.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(toronto);
        allFlights.add(torToVan2);
        Flight torToVan3 = new Flight(toronto,vancouver,"2022-05-14","13:30","2022-05-15","00:30");
        toronto.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(toronto);
        allFlights.add(torToVan3);
        Flight torToVan4 = new Flight(toronto,vancouver,"2022-06-09","16:30","2022-06-09","1:30");
        toronto.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(toronto);
        allFlights.add(torToVan4);

        Flight torToMn = new Flight(toronto,montreal,"2022-11-30","10:30","2022-30-15","15:30");
        toronto.addLocationOutgoing(montreal);
        montreal.addLocationIncoming(toronto);
        allFlights.add(torToMn);

        Flight torToWn = new Flight(toronto,winnipeg,"2022-06-27","20:50","2022-06-27","23:50");
        toronto.addLocationOutgoing(winnipeg);
        winnipeg.addLocationIncoming(toronto);
        allFlights.add(torToWn);

        Flight torToCal = new Flight(toronto,calgary,"2022-10-17","20:50","2022-10-28","01:50");
        toronto.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(toronto);
        allFlights.add(torToCal);


        Flight regToWin = new Flight(regina,winnipeg,"2022-08-13","17:30","2022-08-13","19:30");
        regina.addLocationOutgoing(winnipeg);
        winnipeg.addLocationIncoming(regina);
        allFlights.add(regToWin);
        Flight regToWin2 = new Flight(regina,winnipeg,"2022-08-16","17:30","2022-08-16","19:30");
        regina.addLocationOutgoing(winnipeg);
        winnipeg.addLocationIncoming(regina);
        allFlights.add(regToWin2);
        Flight regToWin3 = new Flight(regina,winnipeg,"2022-08-10","11:30","2022-08-10","15:30");
        regina.addLocationOutgoing(winnipeg);
        winnipeg.addLocationIncoming(regina);
        allFlights.add(regToWin3);

        Flight regToCal = new Flight(regina,calgary,"2022-08-11","07:30","2022-08-11","08:00");
        regina.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(regina);
        allFlights.add(regToCal);
        Flight regToCal2 = new Flight(regina,calgary,"2022-08-17","08:30","2022-08-17","09:00");
        regina.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(regina);
        allFlights.add(regToCal2);

        Flight vanToCal = new Flight(vancouver,calgary,"2022-11-11","03:30","2022-11-11","05:00");
        vancouver.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(vancouver);
        allFlights.add(vanToCal);
        Flight vanToCal2 = new Flight(vancouver,calgary,"2022-06-11","07:30","2022-06-21","09:00");
        vancouver.addLocationOutgoing(calgary);
        calgary.addLocationIncoming(vancouver);
        allFlights.add(vanToCal2);

        Flight calToVan = new Flight(calgary,vancouver,"2022-10-01","11:30","2022-10-01","19:45");
        calgary.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(calgary);
        allFlights.add(calToVan);
        Flight calToVan1 = new Flight(calgary,vancouver,"2022-11-01","11:30","2022-11-01","19:45");
        calgary.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(calgary);
        allFlights.add(calToVan1);
        Flight calToVan2 = new Flight(calgary,vancouver,"2022-12-01","11:30","2022-12-01","19:45");
        calgary.addLocationOutgoing(vancouver);
        vancouver.addLocationIncoming(calgary);
        allFlights.add(calToVan2);

        Flight calToTor = new Flight(calgary,toronto,"2022-12-31","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor);

        Flight calToTor1 = new Flight(calgary,toronto,"2022-01-31","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor1);

        Flight calToTor2 = new Flight(calgary,toronto,"2022-02-28","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor2);

        Flight calToTor3 = new Flight(calgary,toronto,"2022-03-31","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor3);

        Flight calToTor4 = new Flight(calgary,toronto,"2022-04-30","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor4);

        Flight calToTor5 = new Flight(calgary,toronto,"2022-05-31","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor5);

        Flight calToTor6 = new Flight(calgary,toronto,"2022-06-30","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor6);

        Flight calToTor7 = new Flight(calgary,toronto,"2022-07-31","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor7);

        Flight calToTor8 = new Flight(calgary,toronto,"2022-08-31","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor8);

        Flight calToTor9 = new Flight(calgary,toronto,"2022-09-30","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor9);

        Flight calToTor10 = new Flight(calgary,toronto,"2022-10-31","10:30","2022-12-31","18:45");
        calgary.addLocationOutgoing(toronto);
        toronto.addLocationIncoming(calgary);
        allFlights.add(calToTor10);

        Flight montoTor = new Flight(montreal,toronto,"2022-08-11","10:30","2022-11-31","15:45");
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

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

public class TestStubData {
    private String dbName;
    private String dbType = "stub TEST";
    private ArrayList<User> allUsers;
    private ArrayList<Flight> allFlights;
    private ArrayList<Location> allLocations;//tp store city,country,airport and other rdata later
    private ArrayList<Booking> allBookings;
    private BookingManager bookingManager;

    public TestStubData()
    {
        //this.dbName = dbName;
    }




    public void open(){
        User user,defaultUser;

        bookingManager = new BookingManager();

        allUsers = new ArrayList<>();
        allLocations = new ArrayList<>();
        allFlights = new ArrayList<>();
        allBookings = new ArrayList<>();


        System.out.println("Opened " +dbType +" database " +dbName);
    }

    public void close(){
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public String getAllUsers(ArrayList<User> resultList){
        resultList.addAll(allUsers);
        return null;
    }

    public String insertUser(User newUser){
        allUsers.add(newUser);
        return null;
    }

    public String getAllFlights(ArrayList<Flight> resultList){
        resultList.addAll(allFlights);
        return null;
    }

    public String insertFlight(Flight newFlight){
        Location dest = newFlight.getDestination();
        Location source = newFlight.getSource();

        dest.addLocationIncoming(source);
        source.addLocationOutgoing(dest);

        allFlights.add(newFlight);
        return null;
    }

    public String insertLocation(Location newLocation){
        allLocations.add(newLocation);
        return  null;
    }
    //returns the locations
    public String getLocations(ArrayList<Location> resultList){
        resultList.addAll(allLocations);
        return null;
    }

    public String addBooking(Booking newBooking){
        allBookings.add(newBooking);
        return null;
    }


    //to get object of User from username
    public User getUserObject(String username){
        User result = null;
        for(int i=0; i<allUsers.size(); i++){
            if(Objects.equals(allUsers.get(i).getUsername(), username)){
                result = allUsers.get(i);
            }
        }
        return  result;
    }

    //to get object of Flight from flightNumber
    public Flight getFlightObject(int flightNumber){
        Flight result = null;
        for(int i=0; i<allFlights.size(); i++){
            if(allFlights.get(i).getFlightID() == flightNumber){
                result = allFlights.get(i);
            }
        }
        return result;
    }

    //method for getting a specific booking from the db using the booking id
    public Booking getBooking(int bookingId){
        for(int i=0; i<allBookings.size(); i++){
            if(allBookings.get(i).getBookingId() == bookingId){
                return allBookings.get(i);
            }
        }
        return  null;
    }
}

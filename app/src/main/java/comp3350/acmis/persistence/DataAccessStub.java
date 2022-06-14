package comp3350.acmis.persistence;

import android.os.Build;

import androidx.annotation.RequiresApi;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import comp3350.acmis.application.Main;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.User;

public class DataAccessStub {
    private String dbName;
    private String dbType =  "stub";
    private ArrayList<User> allUsers;
    private ArrayList<Flight> allFlights;
    private ArrayList<Location> allLocations;//tp store city,country,airport and other rdata later
    private ArrayList<Booking> allBookings;
    private BookingManager bookingManager;

    //add all the functionality just as for the UserFlights but for booking instead.
    //can remove UserFlight class
    //need for bookings, master record for all the bookings created since the start of the application
    private ArrayList<Booking> bookingList;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.dbName);
    }


    public void open(String dbName){
        User user,defaultUser;
        Flight flight1,flight2;
        bookingManager = new BookingManager();

        allUsers = new ArrayList<User>();
        defaultUser = new User("Johnny","victor", User.Gender.MALE,"default","jOhnNNyVi12","johnny.victor@gmail.com","2045558999");
        allUsers.add(defaultUser);
        user = new User("Julie","smith", User.Gender.FEMALE,"jsmith","j&smith$","jmith@gmail.com","2048889999");
        allUsers.add(user);

        allLocations = new ArrayList<Location>();
        Location winnipeg = new Location("Winnipeg","Canada","YWG");
        allLocations.add(winnipeg);
        Location newYork = new Location("New York","USA","JFK");
        allLocations.add(newYork);
        Location toronto = new Location("Toronto","Canada","YYZ");
        allLocations.add(toronto);
        Location vancouver = new Location("Vancouver","Canada","YVR");
        allLocations.add(vancouver);

        allFlights =  new ArrayList<Flight>();
        flight1 = new Flight(winnipeg,newYork);
        allFlights.add(flight1);
        flight2 = new Flight(toronto,vancouver);
        allFlights.add(flight2);


        allBookings = new ArrayList<Booking>();

        bookingManager.createBooking(defaultUser,new ArrayList<Flight>(Collections.singletonList(flight1)));
        bookingManager.createBooking(defaultUser,new ArrayList<Flight>(Collections.singletonList(flight2)));


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

    //puts a new booking into booking database
    public String bookFlight(String username,int flightNumber ){
        User userObject = getUserObject(username);
        ArrayList<Flight> route = new ArrayList<Flight>();
        route.add(getFlightObject(flightNumber));

        if(userObject != null && flightNumber > 0) {
            bookingManager.createBooking(userObject,route);
        }
        else{
            System.out.println("no object found");
        }
        return  null;
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

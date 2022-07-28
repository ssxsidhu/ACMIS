package comp3350.acmis.integration;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

import comp3350.acmis.application.Services;
import comp3350.acmis.business.AccessBookings;
import comp3350.acmis.business.AccessLocations;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;
import comp3350.acmis.persistence.DataAccess;
import comp3350.acmis.persistence.DataAccessObject;


public class BusinessAccessHsqldb {
    private DataAccess dataAccess;
    private AccessBookings testAccessBookings;



    @Test
    public void testAccessBookings(){
        Services.closeDataAccess();
        ArrayList<Booking> myBookings = new ArrayList<>();
        ArrayList<Flight>allFlights = new ArrayList<>();
        //close the data access to reset persistence.

        System.out.println("Starting Integration test of AccessBooking to persistence");
        dataAccess = Services.createDataAccess();
        Services.dataAccessOpen();

        testAccessBookings = new AccessBookings("braico");

        dataAccess.getAllFlights(allFlights);
        User u = dataAccess.getUserObject("braico");
        //the default user is already created in the stud database.
        assertTrue(u != null);

        //before extracting the list of bookings from the database
        assertEquals(0,myBookings.size());
        testAccessBookings.getMyBookings(myBookings);

        for(int i=0; i<myBookings.size();i++){
            dataAccess.cancelBooking(myBookings.get(i).getBookingId());
        }
        myBookings.clear();

        testAccessBookings.getMyBookings(myBookings);
        assertEquals(0,myBookings.size());
        //user searches for a flight from one place to another, adds the booking
        dataAccess.addBooking(new Booking(u, new Route(allFlights.get(0)), 10 , true));

        //once the booking is added, the user will have one booking in his list.
        testAccessBookings.getMyBookings(myBookings);
        assertEquals(1,myBookings.size());

        //more bookings added by the user.
        dataAccess.addBooking(new Booking(u, new Route(allFlights.get(1)),1  , true));

        dataAccess.addBooking(new Booking(u, new Route(allFlights.get(2)), 5 , true));

        dataAccess.addBooking(new Booking(u, new Route(allFlights.get(3)), 3 , true));
        //there should be a total of 4 bookings that belong to the user in the database.
        myBookings.clear();
        testAccessBookings.getMyBookings(myBookings);
        assertEquals(4,myBookings.size());

        //suppose the user decides to get rid of some bookings...
        dataAccess.cancelBooking(myBookings.get(0).getBookingId());
        //user cancels the booking and
        myBookings.clear();
        testAccessBookings.getMyBookings(myBookings);
        assertEquals(3,myBookings.size());

        //getting rid of couple more...
        dataAccess.cancelBooking(myBookings.get(0).getBookingId());
        dataAccess.cancelBooking(myBookings.get(1).getBookingId());
        myBookings.clear();
        testAccessBookings.getMyBookings(myBookings);
        assertEquals(1,myBookings.size());


        //invalid cases.
        //suppose there are null/ invalid users trying to add booking
        User invalid = null;
        try{
            dataAccess.addBooking(new Booking(invalid,new Route(),10,true));
        }catch(NullPointerException e){
        }
        //now suppose there exist a user but is not in the database.
        invalid = new User("Donald", "Trump", User.Gender.MALE, "DTrumpp","TrumpIsBest123","trumpAhh@gmail.com","2045164339");
        try{
            dataAccess.getUserObject("DTrumpp");
        }catch(NullPointerException ee){
        }

        ArrayList<Booking>TrumpBookings = new ArrayList<>();
        testAccessBookings = new AccessBookings("DTrumpp");

        assertEquals("No user found",testAccessBookings.getMyBookings(TrumpBookings));
        //since the user isn not in the database, we cannot access any bookings.


        System.out.println("Ending integration test of accessBooking");
        Services.closeDataAccess();

    }

    @Test
    public void testAccessLocations(){

        dataAccess = Services.createDataAccess(new DataAccessObject());
        Services.dataAccessOpen();
        ArrayList<Location>dbLocations = new ArrayList<>();

        AccessLocations testAccessLocs = new AccessLocations();

        System.out.println("Starting Integration test of AccessLocations to persistence");

        //arraylist of locations is initially empty
        assertEquals(0,dbLocations.size());

        //get all the locations form the database
        testAccessLocs.getLocations(dbLocations);
        assertEquals(6,dbLocations.size());

        //using the stub database to get the locations.
        dbLocations.clear();
        dataAccess.getLocations(dbLocations);
        assertEquals(6,dbLocations.size());

        System.out.println("Ending integration test of AccessLocations");
        Services.closeDataAccess();

    }

    @Test
    public void testGetAllFlights(){
        dataAccess = Services.createDataAccess(new DataAccessObject());
        Services.dataAccessOpen();
        ArrayList<Flight>dbFlights = new ArrayList<>();

        System.out.println("Starting Integration test of getFlights to persistence");
        //get all the flights from the database.
        assertEquals(0,dbFlights.size());
        dataAccess.getAllFlights(dbFlights);
        assertEquals(930, dbFlights.size());

        System.out.println("Ending integration test of getFlights");
        Services.closeDataAccess();
    }



}

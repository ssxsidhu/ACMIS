package comp3350.acmis.business;

import androidx.annotation.ArrayRes;

import java.util.ArrayList;
import java.util.Date;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;
import comp3350.acmis.persistence.DataAccessStub;

public class BookingManager {

    // Which Data base to perform ops on ?
    private DataAccessStub data;

    // Constructor
    public BookingManager() {
        data = Services.getDataAccess(Main.dbName);
    }

    // Return List of Routes
    public Route searchRoute(Location srcCity, Location destCity){

        ArrayList<Route> validRoutes = new ArrayList<>();

        //find route using an algo
        ArrayList<Flight> allDBFlights = new ArrayList<>();
        data.getAllFlights(allDBFlights);
        ArrayList<Location> allDBLocations = new ArrayList<>();
        data.getLocations(allDBLocations);

        Route validFLights = new Route();

        // This is meant for ITERATION 1 ONLY. For Further Iterations a general case solution will be applied to route searching problems. For now 1 stopOver routes are being processed.
        ArrayList <Flight> tempSrc = new ArrayList<>();
        ArrayList <Flight> tempDest = new ArrayList<>();
        ArrayList <Flight> stopOver = new ArrayList<>();

        // Check for StopOvers and Direct Routes. But First Populate both lists.
        for(int i=0;i<allDBFlights.size();i++)
        {
            // Fill up both lists. At the moment tempSrc has all Flights that begin at srcCity and tempDest has all flights that end at destCity
            if(allDBFlights.get(i).getDestination().getCity().equals(destCity.getCity()))   {tempDest.add(allDBFlights.get(i));}
            if(allDBFlights.get(i).getSource().getCity().equals(srcCity.getCity()))   {tempSrc.add(allDBFlights.get(i));}
        }

        // Check for Direct Routes...
        for(int i=0;i<tempSrc.size();i++)
        {
            for(int j=0;j<tempDest.size();j++)
            {
                if(tempSrc.get(i).getSource().getCity().equals(srcCity.getCity())
                && tempDest.get(j).getDestination().getCity().equals(destCity.getCity()))
                {

                }
            }
        }


        //has to return an arraylist unless the route dsnt exist
        return validFLights;
    }

    //add a method that will select one of the valid flights according to the user's preference.


    //creating booking
    public void createBooking(String username, Route route){
        User bookerObject = data.getUserObject(username);

        if(bookerObject != null && route !=null) {
            Booking newBooking = new Booking(bookerObject, route);
            //adding to the users all the booking.
            bookerObject.addBooking(newBooking);

            //adding the booking to the master booking.
            data.addBooking(newBooking);
        }
        else{
            System.out.println("no object found");
        }


    }

    public void cancelBooking(int bookingId){
        //need this method in the stud database.
       Booking temp = data.getBooking(bookingId);
        User canceller = temp.getBooker();
        canceller.removeBooking(bookingId);
    }



}

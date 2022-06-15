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

    private DataAccessStub data;

    public BookingManager() {
        data = Services.getDataAccess(Main.dbName);
    }

    public Route searchRoute(Location srcCity, Location destCity){
        //find route using an algo
        ArrayList<Flight> allDBFlights = new ArrayList<>();
        data.getAllFlights(allDBFlights);
        ArrayList<Location> allDBLocations = new ArrayList<>();
        data.getLocations(allDBLocations);

        Route validFLights = new Route();


        //base case
        for(int i=0; i <allDBFlights.size();i++){
            if(allDBFlights.get(i).getSource().getCity().equals(srcCity.getCity())
                    &&allDBFlights.get(i).getDestination().getCity().equals(destCity.getCity())){
                validFLights.addToRoute(allDBFlights.get(i));
            }
        }

        //NEED TO DO THIS, MAIN THING
        if(validFLights.isEmpty()){
            //main case.
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

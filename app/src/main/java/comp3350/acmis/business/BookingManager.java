//This method is used to created a booking for a user.
//It uses the user's name and a route instance to create a booking
//the booking is then added to the database

package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.Objects;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;
import comp3350.acmis.persistence.DataAccess;

public class BookingManager {

    private final DataAccess data;

    // Constructor
    public BookingManager() {
        data = Services.getDataAccess(Main.dbName);
    }

    public BookingManager(String name) {
        data = Services.getDataAccess(name);
    }

    //creating booking
    public String createBooking(String username, Route departRoute,Route returnRoute, int numPassengers) {
        User bookerObject = data.getUserObject(Objects.requireNonNull(username));
        ArrayList<Booking> userBookings = new ArrayList<>();
        Booking newBooking;

        //get the user's all the bookings
        //check if the flight is already booked
        //add a booking only if the flight is not booked already
        if (bookerObject != null && departRoute != null) {
            data.getUserBookings(bookerObject,userBookings);
            for (int i = 0; i < userBookings.size(); i++) {
                if (departRoute.getRoute().get(0).getFlightId() == userBookings.get(i).getRouteDepart().getRoute().get(0).getFlightId()) {
                    return "You have already booked this flight for your account";
                }
            }
            if(returnRoute == null)
                newBooking = new Booking(bookerObject, departRoute,numPassengers);
            else
                newBooking = new Booking(bookerObject,departRoute,returnRoute,numPassengers);
            //adding the booking to the master booking.
            data.addBooking(newBooking);
        }
        else {
            throw new NullPointerException("object not found");
        }
        return null;
    }
    //for testing purposes
    public DataAccess getData() {
        return data;
    }

}

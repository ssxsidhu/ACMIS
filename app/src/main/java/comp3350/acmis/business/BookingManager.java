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
        data = Services.getDataAccess();
    }

    //creating booking
    public String createBooking(String username, Route departRoute, Route returnRoute, int numPassengers) {
        User bookerObject = data.getUserObject(Objects.requireNonNull(username));
        Booking newBooking;
        if (bookerObject != null && departRoute != null) {

            if (returnRoute == null)
                newBooking = new Booking(bookerObject, departRoute, numPassengers, true);
            else
                newBooking = new Booking(bookerObject, departRoute, returnRoute, numPassengers, true);
            //adding the booking to the master booking.
            data.addBooking(newBooking);
        } else {
            throw new NullPointerException("object not found");
        }
        return null;
    }

    public String cancelBooking(int bookingId) {
        return data.cancelBooking(bookingId);
    }

}


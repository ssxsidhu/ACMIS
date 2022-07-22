//This class is used to connect the database and the presentation
//Database is accessed to get all the bookings of a particular User.

package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.User;
import comp3350.acmis.persistence.DataAccess;

public class AccessBookings {
    private final DataAccess dataAccess;
    private final String username;

    // CONSTRUCTOR
    public AccessBookings(String user) {
        dataAccess = Services.getDataAccess(Main.dbName);
        username = Objects.requireNonNull(user);
    }

    public String getMyBookings(ArrayList<Booking>myBookings) {

        User user = dataAccess.getUserObject(username);                 // For easier Readability.
        String result;

        if (user != null) {
            if(myBookings!=null) {
                myBookings.clear();
                result = dataAccess.getUserBookings(user, myBookings);
            }else{
                throw new NullPointerException();
            }
        } else {
            return "No user found";
        }

        Collections.sort(myBookings, new Comparator<Booking>() {
            @Override
            public int compare(Booking booking, Booking t1) {
                return booking.getRouteDepart().getRoute().get(0).getDepartureDateTime().compareTo(t1.getRouteDepart().getRoute().get(0).getDepartureDateTime());
            }
        });
        return result;
    }
}

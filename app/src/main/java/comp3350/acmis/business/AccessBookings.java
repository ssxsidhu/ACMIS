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
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.User;
import comp3350.acmis.persistence.DataAccess;

public class AccessBookings {
    private final DataAccess dataAccess;
    private final String username;
    private ArrayList<Booking> userBookings;

    // CONSTRUCTOR
    public AccessBookings(String user) {
        userBookings = new ArrayList<>();
        dataAccess = Services.getDataAccess();
        username = Objects.requireNonNull(user);
    }

    public String getMyBookings(ArrayList<Booking>myBookings) {

        String result = getBookingsFromDB();
        Collections.sort(userBookings, new CompareBookings());
        myBookings.addAll(userBookings);
        return result;
    }

    private String getBookingsFromDB(){
        User user = dataAccess.getUserObject(username);                 // For easier Readability.
        if (user != null) {
            if(userBookings!=null) {
                userBookings.clear();
                return dataAccess.getUserBookings(user, userBookings);
            }else{
                throw new NullPointerException();
            }
        } else {
            return "No user found";
        }
    }

    private static class CompareBookings implements Comparator<Booking>{

        @Override
        public int compare(Booking b1, Booking b2) {
            return (new UseRouteFlights(b1.getRouteDepart())).getConnectDepartureTime().compareTo((new UseRouteFlights(b2.getRouteDepart())).getConnectDepartureTime());
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }
    }
}



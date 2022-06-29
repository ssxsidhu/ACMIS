package comp3350.acmis.business;


import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.User;
import comp3350.acmis.persistence.DataAccessStub;

public class AccessBookings {

    // INSTANCE VARIABLES
    private DataAccessStub dataAccess;
    private List<Booking> userBookings;
    private String username;

    // CONSTRUCTOR
    public AccessBookings(String user) {
        dataAccess=Services.getDataAccess(Main.dbName);
        username=user;
    }

    // GETTER
    public String getMyBookings(ArrayList<Booking> myBookings) {

        myBookings.clear();                                             // Clear the List we are going to return

        User user = dataAccess.getUserObject(username);
        String result;

        // GUARD CONDITION - DO SOMETHING ONLY IF USER EXISTS
        if(user != null) {
            result = dataAccess.getUserBookings(user, myBookings);
        }
        else{
            return "No user found";
        }

        Collections.sort(myBookings, new Comparator<Booking>() {            // Sort the List before we Return it

            @Override
            public int compare(Booking booking, Booking t1) {
                return booking.getRoute().getRoute().get(0).getDepartureDateTime().compareTo(t1.getRoute().getRoute().get(0).getDepartureDateTime());
            }
        });

        return result;
    }
}

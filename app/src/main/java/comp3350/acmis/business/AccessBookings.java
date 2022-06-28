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
    private DataAccessStub dataAccess;
    private List<Booking> userBookings;
    private String username;

    public AccessBookings(String user) {
        dataAccess=Services.getDataAccess(Main.dbName);
        username=user;
    }

    public String getMyBookings(ArrayList<Booking> myBookings) {
        myBookings.clear();
        ArrayList<User> allUsers = new ArrayList<User>();
        dataAccess.getAllUsers(allUsers);

        for (int i = 0; i < allUsers.size(); i++){
            if (allUsers.get(i).getUsername().equals(username)) {
                allUsers.get(i).getMyBookings(myBookings);

                //ascending order
                Collections.sort(myBookings, new Comparator<Booking>() {
                    @Override
                    public int compare(Booking booking, Booking t1) {
                        return booking.getRoute().getRoute().get(0).getDepartureDateTime().compareTo(t1.getRoute().getRoute().get(0).getDepartureDateTime());
                    }
                });



                return null;
            }
        }
        return null;
    }
}

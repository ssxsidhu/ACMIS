package comp3350.acmis.business;

import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;

import comp3350.acmis.business.RouteManager;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;


public class BookingManager2 {

    // INSTANCE VARIABLES


    // CONSTRUCTOR
    public BookingManager2() {

    }

    // SETTER
    public String bookTicket(Route thisRoute, User thisUser, int numPassengers) {

        if(thisUser!=null && thisRoute!=null) {

            // Check if User Already has Any Bookings
            ArrayList <Booking> userBookings = new ArrayList<>();
            thisUser.getBookings(userBookings);

            // Check if List is Empty. If Yes then No Booking Exist for this User.
            if(userBookings.isEmpty()) {
                thisUser.addBooking(new Booking(thisUser, thisRoute, numPassengers, thisRoute.getDepartTime(), thisRoute.getArrivalTime()));
            }
            else {
                // Retrieve All Bookings for This User. Check if Date and Time Conflict Occurs. If Yes IllegalStateException. If No Create Booking.
                boolean conflict = false;
                int index = 0;
                AccessRouteFlights accessRouteFlights = new AccessRouteFlights(thisRoute);
                ZonedDateTime depart = accessRouteFlights.

                while(index<userBookings.size()&&!conflict) {   // Check if Time Conflict Occurs. If Within an existing Range then Throw Illegal State Exception.


                        index++;
                    }

                if(!conflict) {

                }
                else {
                        throw new IllegalStateException("Time Conflict ! Departure Time of Requested booking conflicts with Departure and Arrival Time of Existing Booking");
                    }
                }

            }
            else if(thisUser==null) {
                throw new NullPointerException("User Cannot be NULL !");
            }
            else if(thisRoute==null) {
                throw new NullPointerException("Route Cannot be NULL !");
            }
        return null;
        }

    public String cancelTicket(int bookingID) {

        return null;
    }

    public String changeBooking(int bookingID) {

        return null;
    }
}
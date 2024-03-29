//Booking class consists of user who is booking a ticket from one place to another
//Booking also has a route that is the flight(s) to destination from the source.
//Booing id and # of passengers booked.

package comp3350.acmis.objects;

import java.util.Objects;

public class Booking {

    // Static VARIABLE
    private static int bookingSeq = 1;             // Assign Unique Booking ID for every Booking

    // INSTANCE VARIABLES
    private User booker;                           // Person Booking the flight
    private final Route routeDepart;
    private Route routeReturn = null;              // The Route being taken for reaching from A->B
    private int bookingId;
    private int numPassengers;

    // CONSTRUCTOR
    public Booking(User booker, Route routeDepart, int numP, boolean realBooking) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.routeDepart = Objects.requireNonNull(routeDepart, "Route cannot be null");
        numPassengers = numP;
        if (realBooking) {
            bookingId = bookingSeq;
            bookingSeq++;
        }
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    // CONSTRUCTOR for Bookings with a return Route.
    public Booking(User booker, Route routeDepart, Route routeReturn, int numP, boolean realBooking) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.routeDepart = Objects.requireNonNull(routeDepart, "Route cannot be null");
        this.routeReturn = routeReturn;
        numPassengers = numP;
        if (realBooking) {
            bookingId = bookingSeq;
            bookingSeq++;
        }
    }

    // SETTER
    public boolean incrementPassengers() {
        double checkPassengers = (double) numPassengers + 1;
        boolean ret = false;

        if (checkPassengers <= Integer.MAX_VALUE) {
            numPassengers++;
            ret = true;
        }
        return ret;
    }

    // GETTERS
    public User getBooker() {
        return booker;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Route getRouteDepart() {
        return routeDepart;
    }

    public Route getRouteReturn() {
        return routeReturn;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public boolean checkForReturn() {
        return routeReturn != null;
    }

    //Setter
    public void setNewUser(User u) {
        booker = u;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        boolean ret = numPassengers == booking.numPassengers &&
                      booker.getUsername().equals(booking.booker.getUsername()) &&
                      routeDepart.getFlightsCSV().equals(booking.routeDepart.getFlightsCSV());

        if (routeReturn != null && booking.routeReturn != null) {
            ret = ret && routeReturn.getFlightsCSV().equals(booking.routeReturn.getFlightsCSV());
        }
        return ret;
    }

    @Override
    public int hashCode() {
        return Objects.hash(booker, routeDepart, routeReturn, numPassengers);
    }
}

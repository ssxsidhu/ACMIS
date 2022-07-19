//Booking class consists of user who is booking a ticket from one place to another
//Booking also has a route that is the flight(s) to destination from the source.
//Booing id and # of passengers booked.

package comp3350.acmis.objects;

import org.threeten.bp.ZonedDateTime;

import java.util.Objects;

public class Booking {

    // Static VARIABLE
    private static int bookingSeq;                 // Assign Unique Booking ID for every Booking

    // INSTANCE VARIABLES
    private User booker;                           // Person Booking the flight
    private Route routeDepart, routeReturn = null; // The Route being taken for reaching from A->B
    private int bookingId;
    private int numPassengers;
    private ZonedDateTime departTime;
    private ZonedDateTime arrivalTime;

    // *******************************************************************************************************
    public Booking(User booker, Route routeDepart, int numP, ZonedDateTime newDepart) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.routeDepart = Objects.requireNonNull(routeDepart, "Route cannot be null");
        departTime = newDepart;
        numPassengers = numP;
        bookingId = bookingSeq;
        bookingSeq++;
    }
    public Booking(User booker, Route routeDepart, int numP, ZonedDateTime newDepart, ZonedDateTime newArrival) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.routeDepart = Objects.requireNonNull(routeDepart, "Route cannot be null");
        departTime = newDepart;
        numPassengers = numP;
        bookingId = bookingSeq;
        bookingSeq++;
    }
    public Booking(User booker, Route routeDepart, Route routeReturn, int numP, ZonedDateTime newDepart, ZonedDateTime newArrival) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.routeDepart = Objects.requireNonNull(routeDepart, "Route cannot be null");
        this.routeReturn = routeReturn;
        departTime = newDepart;
        arrivalTime = newArrival;
        numPassengers = numP;
        bookingId = bookingSeq;
        bookingSeq++;
    }
    // *******************************************************************************************************

    // CONSTRUCTOR
    public Booking(User booker, Route routeDepart, int numP) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.routeDepart = Objects.requireNonNull(routeDepart, "Route cannot be null");
        numPassengers = numP;
        bookingId = bookingSeq;
        bookingSeq++;
    }

    // CONSTRUCTOR for Bookings with a return Route.
    public Booking(User booker, Route routeDepart, Route routeReturn, int numP) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.routeDepart = Objects.requireNonNull(routeDepart, "Route cannot be null");
        this.routeReturn = routeReturn;
        numPassengers = numP;
        bookingId = bookingSeq;
        bookingSeq++;
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

    public ZonedDateTime getDepartTime() {return departTime;}

    public ZonedDateTime getArrivalTime() {return arrivalTime;}

    //Setter
    public void setNewUser(User u) {
        booker = u;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return numPassengers == booking.numPassengers && Objects.equals(booker, booking.booker) && Objects.equals(routeDepart, booking.routeDepart) && Objects.equals(routeReturn, booking.routeReturn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booker, routeDepart, routeReturn, numPassengers);
    }

}

//Booking class consists of user who is booking a ticket from one place to another
//Booking also has a route that is the flight(s) to destination from the source.
//Booing id and # of passengers booked.

package comp3350.acmis.objects;

import java.util.Objects;

public class Booking {

    // Static VARIABLE
    private static int bookingSeq;                 // Assign Unique Booking ID for every Booking

    // Instance Variable
    private User booker;                           // Person Booking the flight
    private Route routeDepart, routeReturn = null; // The Route being taken for reaching from A->B
    private int bookingId;
    private int numPassengers;

    public Booking(User booker, Route routeDepart, int numP) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.routeDepart = Objects.requireNonNull(routeDepart, "Route cannot be null");
        numPassengers = numP;
        bookingId = bookingSeq;
        bookingSeq++;
    }

    public Booking(User booker, Route routeDepart, Route routeReturn, int numP) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.routeDepart = Objects.requireNonNull(routeDepart, "Route cannot be null");
        this.routeReturn = routeReturn;
        numPassengers = numP;
        bookingId = bookingSeq;
        bookingSeq++;
    }

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
    public Route getRouteReturn(){
        return  routeReturn;
    }
    public int getNumPassengers() {
        return numPassengers;
    }
    public boolean checkForReturn(){
        return routeReturn!=null;
    }

    //Setter
    public void setNewUser(User u){
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

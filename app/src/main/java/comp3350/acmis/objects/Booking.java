package comp3350.acmis.objects;

import java.util.Objects;

public class Booking {

    // STATIC VARIABLE
    private static int bookingSeq;          // Assign Unique Booking ID for every Booking

    // INSTANCE VARIABLES
    private User booker;                    // Person Booking the flight
    private Route route;                    // The Route being taken for reaching from A->B
    private int bookingId;                  // Unique identifier for this booking
    private int numPassengers;              // Number of passengers in this booking

    // CONSTRUCTOR
    public Booking(User booker, Route route) {
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.route = Objects.requireNonNull(route, "Route cannot be null");
        numPassengers = 1;
        bookingId = bookingSeq;
        bookingSeq++;
    }

    // SETTERS
    public boolean incrementPassengers() {
        double checkPassengers = (double) numPassengers + 1;
        boolean ret = false;

        if (checkPassengers <= Integer.MAX_VALUE) {
            numPassengers++;
            ret = true;
        }
        return ret;
    }
    public void setNewUser(User newUser) {
        booker = newUser;
    }

    // GETTERS
    public User getBooker() {
        return booker;
    }
    public int getBookingId() {
        return bookingId;
    }
    public Route getRoute() {
        return route;
    }
    public int getNumPassengers() {
        return numPassengers;
    }
}
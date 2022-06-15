/*
This class is used to book flights as the user requests.
The booking object has a user who is booking the flight, the route (direct flight/ connecting flight),
booking id and the number of passengers.
 */
package comp3350.acmis.objects;

import java.util.ArrayList;
import java.util.Objects;

public class Booking {

    // STATIC VARIABLE
    private static int bookingSeq;          // Assign Unique Booking ID for every Booking

    // Instance Variable
    private User booker;                    // Person Booking the flight
    private Route route;                    // The Route being taken for reaching from A->B
    private int bookingId;
    private int numPassengers;

    // Constructor()
    public Booking(User booker, Route route) {
        this.booker = Objects.requireNonNull(booker,"User cannot be null");
        this.route = Objects.requireNonNull(route,"route cannot be null");
        bookingId = bookingSeq;
        bookingSeq++;
    }

    public void incrementPassengers(){
        numPassengers++;
    }

    // GETTERS
    public User getBooker() {
        return booker;
    }
    public int getBookingId() {return bookingId;}
    public Route getRoute() {
        return route;
    }
    public int getNumPassengers() {
        return numPassengers;
    }

    // SETTER
    public void setNewUser (User newUser){
        booker = newUser;
    }





}

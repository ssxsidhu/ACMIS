package comp3350.acmis.objects;

import java.util.ArrayList;


public class Booking {
    private static int BookingSeq;

    private User booker;
    private ArrayList<Flight> route;
    private int BookingId;

    public Booking(User booker, ArrayList<Flight> route) {
        this.booker = booker;
        this.route = route;
        BookingId = BookingSeq;
        BookingId++;
    }


    //getter and setters
    public User getBooker() {
        return booker;
    }

    public ArrayList<Flight> getRoute() {
        return route;
    }

    public void setNewUser (User newUser){
        booker = newUser;
    }


    public int getBookingId() {
        return BookingId;
    }


}

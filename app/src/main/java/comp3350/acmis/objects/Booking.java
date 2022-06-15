package comp3350.acmis.objects;

import java.util.ArrayList;


public class Booking {
    private static int bookingSeq;

    private User booker;
    private Route route;
    private int bookingId;

    public Booking(User booker, Route route) {
        this.booker = booker;
        this.route = route;
        bookingId = bookingSeq;
        bookingSeq++;
    }



    //getter and setters
    public User getBooker() {
        return booker;
    }

    public Route getRoute() {
        return route;
    }

    public void setNewUser (User newUser){
        booker = newUser;
    }


    public int getBookingId() {
        return bookingId;
    }


}

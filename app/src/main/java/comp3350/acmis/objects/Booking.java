package comp3350.acmis.objects;

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
        this.booker = Objects.requireNonNull(booker, "Booker cannot be null");
        this.route = Objects.requireNonNull(route, "Route cannot be null");
        numPassengers = 1;
        bookingId = bookingSeq;
        bookingSeq++;
    }


    public boolean incrementPassengers() {
        double checkPassengers = (double)numPassengers + 1;
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
    public void addBooking(Booking newBook) {
        if (newBook == null) {
            throw new NullPointerException();
        }
        bookings.add(newBook);
    }

    public boolean removeBooking(int bookingID) {
        boolean found = false;
        Booking removal = null;

        if (bookingID < 0) {
            throw new NegativeArraySizeException();
        }

        //find booking with matching ID
        for (int i = 0; i < bookings.size() && !found; i++) {
            removal = bookings.get(i);

            if (removal != null && removal.getBookingId() == bookingID) {
                bookings.remove(removal);
                found = true;
            }
        }
        return found;
    }
}

// THIS WHOLE CLASS ASSUMES THAT FLIGHTS RUN DAILY.
// WE NEED TO CONSIDER IF SOME FLIGHTS RUN ONLY ON CERTAIN DAYS OR TIME RANGES

package comp3350.acmis.objects;
import android.os.Build;
import java.util.*;
import androidx.annotation.RequiresApi;

import java.time.*;
import java.util.ArrayList;

public class Flight{

    // STATIC VARIABLE
    private static int flightSequence;

    // Instance Variables
    private int flightID;
    private Location source;
    private Location destination;
//    private LocalDateTime departure ;
//    private LocalDateTime arrival;

    private ArrayList<User> flightList;                 // Store all users on this flight in a list.

    // Constructor()//modified constructor to avoid LocalDateTime
    public Flight(Location newSrc, Location newDest) {
        flightID = flightSequence;
        source = newSrc;
//        departure = newDeparture;
//        arrival = newArrival;
        destination = newDest;

        flightList = new ArrayList<>();
        flightSequence++;
    }

    // Getters()
    public int getFlightID() {
        return flightID;
    }

    public ArrayList<User> getPassengerList() {
        return flightList;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", source=" + source +
                ", destination=" + destination +
//                ", departure=" + departure +
//                ", arrival=" + arrival +
                ", flightList=" + flightList +
                '}';
    }

    // Setters()
    public void addUser(User addThis) {
        flightList.add(addThis);
    }
    public boolean removeUser(User removeThisUser) {
        return(flightList.remove(removeThisUser));
    }
    public void newDepart(Location newDepart) {
        source = newDepart;
    }
    public void newArrival(Location newArrive) {
        destination = newArrive;
    }

    public static int getFlightSequence() {
        return flightSequence;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }
}
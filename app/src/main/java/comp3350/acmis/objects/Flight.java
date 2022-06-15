// THIS WHOLE CLASS ASSUMES THAT FLIGHTS RUN DAILY.
// WE NEED TO CONSIDER IF SOME FLIGHTS RUN ONLY ON CERTAIN DAYS OR TIME RANGES

package comp3350.acmis.objects;
import android.os.Build;

import java.sql.Date;
import java.util.*;
import androidx.annotation.RequiresApi;

import java.time.*;
import java.util.ArrayList;

import comp3350.acmis.business.DateFormatter;

public class Flight {

    // STATIC VARIABLE
    private static int flightSequence;

    // Instance Variables
    private int flightID;
    private Location source;
    private Location destination;

    //date format yyyy-mm-dd
    //time format hh-mm
    private String departureDate;
    private String departureTime;

    private String arrivalDate;
    private String arrivalTime;


    // Store all users on this flight in a list.
    private ArrayList<User> flightList;

    public Flight(Location source, Location destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime) {
        this.flightID = flightID;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.flightList = new ArrayList<>();
        flightSequence++;
    }


    // GETTERS
    public int getFlightID() {
        return flightID;
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
    public ArrayList<User> getPassengerList() {
        return flightList;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getArrivalDate() {
        DateFormatter date = new DateFormatter(departureDate);
        System.out.println(date.format());
        return date.format();
    }
    public String getDepartureDate() {
        DateFormatter date = new DateFormatter(departureDate);
        System.out.println(date.format());
        return date.format();
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", source=" + source +
                ", destination=" + destination +
                ", flightList=" + flightList +
                '}';
    }

    // SETTERS
    public void addUser(User addThis) {
        flightList.add(addThis);
    }
    public boolean removeUser(User removeThisUser) {
        return (flightList.remove(removeThisUser));
    }
    public void newDepart(Location newDepart) {
        source = newDepart;
    }
    public void newArrival(Location newArrive) {
        destination = newArrive;
    }

}
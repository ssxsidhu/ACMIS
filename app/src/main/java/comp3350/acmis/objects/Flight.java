/*
The flight class is used an an instance of an actual flight.
The class has the following information:
Source, Destination
FLightId
Departure and Arrival date and time
The Flight also has a list of passengers who booked the flight.
 */

package comp3350.acmis.objects;

import java.util.ArrayList;
import comp3350.acmis.business.DateFormatter;

public class Flight {

    // STATIC VARIABLE
    private static int flightSequence=0;

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

    //constructor
    public Flight(Location source, Location destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime) {
        assert(source != null && destination != null);

        this.flightID = flightSequence;
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
        DateFormatter date = new DateFormatter(arrivalDate);
        return date.format();
    }
    public String getDepartureDate() {
        DateFormatter date = new DateFormatter(departureDate);
        return date.format();
    }
    public String getRawDepartureDate(){
        return departureDate;
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

}
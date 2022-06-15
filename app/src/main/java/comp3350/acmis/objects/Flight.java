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
import java.util.Objects;

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

    //

    //constructor
    public Flight(Location source, Location destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime) {

        this.flightID = flightSequence;
        this.source = Objects.requireNonNull(source,"Source cannot be null");
        this.destination = Objects.requireNonNull(destination,"Destination cannot be null");
        this.departureDate = Objects.requireNonNull(departureDate,"Departure date cannot be null");
        this.departureTime = Objects.requireNonNull(departureTime,"Departure time cannot be null");;
        this.arrivalDate = Objects.requireNonNull(arrivalDate,"Arrival date cannot be null");;
        this.arrivalTime = Objects.requireNonNull(arrivalTime,"Arrival time cannot be null");;
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
                ", flightList="+
                '}';
    }

}
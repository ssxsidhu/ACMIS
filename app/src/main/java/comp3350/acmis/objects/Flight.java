/*
The flight class is used an an instance of an actual flight.
The class has the following information:
Source, Destination
FLightId
Departure and Arrival date and time
The Flight also has a list of passengers who booked the flight.
 */

package comp3350.acmis.objects;



import org.threeten.bp.Duration;
import org.threeten.bp.ZonedDateTime;

import java.util.Objects;

public class Flight {

    // STATIC VARIABLE
    private static int flightSequence=1;

    // Instance Variables
    private int flightID;
    private Location source;
    private Location destination;
    private ZonedDateTime departureDateandTime;
    private ZonedDateTime arrivalDateandTime;

    private int seats; // space in the flight
    private Duration duration; //weight of flight from one place to another
    private int cost; //cost of the flight - monetary value.


    //constructor
    public Flight(Location source, Location destination, ZonedDateTime departureDateandTime, int seats, double dur, int cost){
        this.departureDateandTime = Objects.requireNonNull(departureDateandTime);

        this.duration = calculateDuration(dur);
        this.arrivalDateandTime = departureDateandTime.plus(duration).withZoneSameInstant(destination.getZoneName());
        this.seats = seats;
        this.cost = cost;
        this.source = Objects.requireNonNull(source,"Source cannot be null");
        this.destination = Objects.requireNonNull(destination,"Destination cannot be null");

        this.flightID = flightSequence;
        flightSequence++;
    }

    public Flight(int flightID, Location source, Location destination, ZonedDateTime departureDateandTime, int seats, double dur, int cost){
        this.departureDateandTime = Objects.requireNonNull(departureDateandTime);

        this.duration = calculateDuration(dur);
        this.arrivalDateandTime = departureDateandTime.plus(duration).withZoneSameInstant(destination.getZoneName());
        this.seats = seats;
        this.cost = cost;
        this.source = Objects.requireNonNull(source,"Source cannot be null");
        this.destination = Objects.requireNonNull(destination,"Destination cannot be null");

        this.flightID = flightID;
        flightSequence++;
    }

    //This method is used to calculate the duration of the flight from source to destination
    //it returns Duration this that is added to the departure time to calculate the arrival time in another zone.
    private Duration calculateDuration(double duration){
        String [] separation = String.valueOf(duration).split("\\.");
        int hours = Integer.parseInt(separation[0]);
        int mins = ((Integer.parseInt(separation[1])/10)*60);

        return Duration.ofHours(hours).plusMinutes(mins);
    }

    //this method is used to check if the seats left are enough for the user.
    public boolean enoughSeats(int seatsTobeBooked){
        return seats >= seatsTobeBooked;
    }

    //when a user books this flight, he/she chooses the # of seats to be booked
    //those # of seats are to be reserved in the flight.
    public boolean bookSeat(int bookedSeats){
        if(enoughSeats(bookedSeats)) {
            seats = seats - bookedSeats;
            return true;
        }
        return false;
    }

    // GETTERS
    public static int getFlightSequence() {
        return flightSequence;
    }
    public int getFlightID() {
        return flightID;
    }
    public Location getSource() {
        return source;
    }
    public Location getDestination() {
        return destination;
    }
    public int getSeats() {
        return seats;
    }
    public int getCost() {
        return cost;
    }
    public Duration getDuration() {
        return duration;
    }
    public String getArrivalTime() {
        return arrivalDateandTime.getHour() + ":" +arrivalDateandTime.getMinute();
    }
    public String getDepartureTime() {
        return departureDateandTime.getHour() + ":" + departureDateandTime.getMinute();
    }

    public ZonedDateTime getDepartureDateTime(){
        return departureDateandTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", source=" + source.getCity() +
                ", destination=" + destination.getCity() +
                ", departureDateandTime=" + departureDateandTime.toString() +
                ", arrivalDateandTime=" + arrivalDateandTime.toString() +
                ", seats=" + seats +
                ", duration=" + duration +
                ", cost=" + cost +
                '}';
    }
}
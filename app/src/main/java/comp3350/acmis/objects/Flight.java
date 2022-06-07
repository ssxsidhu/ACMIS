package comp3350.acmis.objects;
import java.time.*;

public class Flight{

    // STATIC VARIABLE
    private static int flight_ID = 0;;

    // Instance Variables
    private int flightSerial;
    private String startDestination;
    private String endDestination;
    private double distance;                    // This needs to be pulled from data base.
    private double cost;                        // Airline will assign this value in constructor. Airline decides ticket prices.

    private Airplane assigned;                  // Assigned Airplane to this flight. Important for performing checks and what not.
    private LocalDateTime departureTime;        // Store the departure time of the flight in a DateTime Format.
    private LocalDateTime estimatedTime;        // Same as above except for arrivals

    // Constructor
    public Flight(String newStart, String newEnd, double newDistance, double newCost){

        flightSerial = flight_ID;
        startDestination = newStart;
        endDestination = newEnd;
        distance = newDistance;         // Value must be supplied from Data base. 
        cost = newCost;                 // Value supplied from Airline

        flight_ID+=2;                   // FLights going to are EVEN. Flights Coming from are ODD. If NO odd exists there is no return flight.
    }

    
    // Setters()
    private void setFlightID()          // Decrement so that new flight ID becomes ODD. This METHOD IS CALLED ONLY AND ONLY WHEN RETURNFLIGHT() IS CALLED
    {flightSerial--;}
    public void setPrice(double newPrice)   // Duh.
    {cost = newPrice;}

    // Assign an Aircraft for this flight. Perform Checks before accepting.
    public void assignPlane(Airplane newPlane){

        // Perform check on newPlane for range. Do something only if plane can travel that far.
        if(newPlane.getRange()>distance){

            assigned = newPlane;        
            estimatedTime = departureTime.plusHours((int)(distance/assigned.getSpeed()));           // Calculate Estimated Time based on plane attributes.
        }
        else
            System.out.println("ERROR. PLANE RANGE LESSER THAN FLIGHT DISTANCE.\n");        
    }

    // Change the depart time just because. We will also change arrivalTime
    public void setDepartTime(LocalDateTime newTime)
    {
        departureTime = newTime;
        estimatedTime = departureTime.plusHours((int)(distance/assigned.getSpeed()));       // Change arrival time accordingly based on distance and plane speed.
    }

    // RETURN FLIGHT. CREATE NEW OBJECT AND RETURN.
    public Flight createReturn(int returnAfter){                    // PARAMS. RETURN AFTER THESE MANY HOURS if returnAfter = 3, then plane departs 3 hours after its arrival time.

        Flight returnFlight = new Flight(endDestination, startDestination, distance, cost);
        returnFlight.assignPlane(assigned);

        returnFlight.setDepartTime(estimatedTime.plusHours(returnAfter));
        returnFlight.setFlightID();

        return returnFlight;
    }
    
    // LINK FLIGHT. IMPORTANT FOR MULTI ROUTES AND RETURN BOOKINGS OR EDITS. I FEEL THIS WILL BE NEEDED ANYWAY
    public void linkFlight(Flight linkThis, LocalDateTime linkThisDepartTime){

        if(this.endDestination.equals(linkThis.startDestination)){                         // Do Something only if arrival dest of flight 1 is same as depart dest of flight 2          

            if(linkThisDepartTime.isAfter(estimatedTime))                                   // Do Something only if Depart Time for flight 2 is AFTER arrival time of flight 1
            {
                        // NEED TO THINK.
            }
            else
            System.out.println("ERROR. Second Flight CANNOT leave BEFORE ARRIVAL of Current Flight !\n");
        }
        else
        {System.out.println("ERROR. CANNOT LINK FLIGHT. Destination of Current is NOT same AS DEPARTURE for Flight Entered in Params\n");}
    }
    
    public String getFlightDetails()            // Can also rename to toString()
    {
        return ("Flight ID: "+flightSerial+" \n"+startDestination+" ----> "+endDestination+"\n"+"Departure :"+departureTime+" ----> "+estimatedTime+" \n"+"Distance: "+distance+" km.");
    }

}

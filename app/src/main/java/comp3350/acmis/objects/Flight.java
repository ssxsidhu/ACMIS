// THIS WHOLE CLASS ASSUMES THAT FLIGHTS RUN DAILY. WE NEED TO CONSIDER IF SOME FLIGHTS RUN ONLY ON CERTAIN DAYS OR TIME RANGES

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
    private Location depart;
    private Location arrive;
    private LocalDateTime departure ;
    private LocalDateTime arrival;

    private ArrayList<User> flightList;                 // Store all users on this flight in a list.

    // Constructor()
    public Flight(Location newDepart, Location newArrive, LocalDateTime newDeparture, LocalDateTime newArrival) {
        flightID = flightSequence;
        depart = newDepart;
        departure = newDeparture;
        arrival = newArrival;
        arrive = newArrive;

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


    public String toString(){

        String temp = "Flight Details: Departure -------> Destination";
        temp+="\n"+depart+" \t "+arrive;
        temp+="\n"+flightID;

        return temp;
    }


    // Setters()
    public void addUser(User addThis)
    {flightList.add(addThis);}
    public boolean removeUser(User removeThisUser)
    {return(flightList.remove(removeThisUser));}
    public void newDepart(Location newDepart)
    {depart = newDepart;}
    public void newArrival(Location newArrive)
    {arrive = newArrive;}

}


package comp3350.acmis.objects;

import androidx.annotation.NonNull;

import org.threeten.bp.ZoneId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;



public class Location  implements Serializable {

    // INSTANCE VARIABLES
    private final String city;
    private final ZoneId zoneName;
    private final String country;
    private final String airport;
    private ArrayList<Location> locsIncomingFlights;
    private ArrayList<Location> locsOutgoingFLights;

    // CONSTRUCTOR
    public Location(String city, ZoneId zoneName, String country, String airport) {
        this.city = errorCheck(city, "City");
        this.zoneName = zoneName;
        this.country = errorCheck(country, "Country");
        this.airport = errorCheck(airport, "Airport");
        locsIncomingFlights = new ArrayList<>();
        locsOutgoingFLights = new ArrayList<>();
    }

    // SETTERS
    public void addLocationIncoming(Location incoming) {
        if (incoming == null) {
            throw new NullPointerException();
        }
        locsIncomingFlights.add(incoming);
    }
    public void addLocationOutgoing(Location outGoing) {
        if (outGoing == null) {
            throw new NullPointerException();
        }
        locsOutgoingFLights.add(outGoing);
    }

    //GETTERS
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getAirport() {
        return airport;
    }
    public ArrayList<Location> getLocsIncomingFlights() {
        return locsIncomingFlights;
    }
    public ArrayList<Location> getLocsOutgoingFLights() {
        return locsOutgoingFLights;
    }
    public ZoneId getZoneName() {
        return zoneName;
    }

    @Override
    @NonNull
    public String toString() {
        return city +", "+country;
    }

    // PRIVATE HELPER METHOD
    // Error checking in constructor

    @Override
    public boolean equals(Object compare)
    {

        if(compare.getClass() == getClass()) {                  // Check if object being passed is a Object of this Class.

            Location compareThis = (Location)compare;           // Type Cast explicitly to LOCATION type.

            return (city.equals(compareThis.getCity()) &&
                    zoneName.equals(compareThis.getZoneName()) &&
                    country.equals(compareThis.getCountry()) &&
                    airport.equals(compareThis.getAirport()));
        }

        return  false;
    }
    private String errorCheck(String value, String message) {
        if (value.trim().equals("")){
            throw new IllegalArgumentException(message + " can not be empty");
        }
        return Objects.requireNonNull(value, message + " cannot be null").trim();
    }
}
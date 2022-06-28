
package comp3350.acmis.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Location  implements Serializable {
    private final String city;
    private final String country;
    private final String airport;
    private ArrayList<Location> locsIncomingFlights;
    private ArrayList<Location> locsOutgoingFLights;

    public Location(String city, String country, String airport) {
        this.city = errorCheck(city, "City");
        this.country = errorCheck(country, "Country");
        this.airport = errorCheck(airport, "Airport");

        locsIncomingFlights = new ArrayList<>();
        locsOutgoingFLights = new ArrayList<>();
    }

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

    @Override
    public String toString() {
        return city +", "+country;
    }


    //Error checking in constructor
    private String errorCheck(String value, String message) {
        if (value.trim().equals("")){
            throw new IllegalArgumentException(message + " can not be empty");
        }
        return Objects.requireNonNull(value, message + " cannot be null").trim();
    }
}

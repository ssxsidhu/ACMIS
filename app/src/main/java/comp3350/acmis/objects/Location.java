
package comp3350.acmis.objects;

import androidx.annotation.NonNull;

import org.threeten.bp.ZoneId;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {

    // INSTANCE VARIABLES
    private final String city;
    private final ZoneId zoneName;
    private final String country;
    private final String airport;

    // CONSTRUCTOR
    public Location(String city, ZoneId zoneName, String country, String airport) {
        this.city = errorCheck(city, "City");
        this.zoneName = Objects.requireNonNull(zoneName);
        this.country = errorCheck(country, "Country");
        this.airport = errorCheck(airport, "Airport");
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

    public ZoneId getZoneName() {
        return zoneName;
    }

    @Override
    @NonNull
    public String toString() {
        return city + ", " + country;
    }

    @Override
    public boolean equals(Object compare) {
        if (compare.getClass() == getClass()) {                  // Check if object being passed is a Object of this Class.
            Location compareThis = (Location) compare;           // Type Cast explicitly to LOCATION type.
            return (city.equals(compareThis.getCity()) &&
                    zoneName.equals(compareThis.getZoneName()) &&
                    country.equals(compareThis.getCountry()) &&
                    airport.equals(compareThis.getAirport()));
        }
        return false;
    }

    // PRIVATE HELPER METHOD
    // Error checking in constructor
    private String errorCheck(String value, String message) {
        if (value.trim().equals("")) {
            throw new IllegalArgumentException(message + " can not be empty");
        }
        return Objects.requireNonNull(value, message + " cannot be null").trim();
    }
}
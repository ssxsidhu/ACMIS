
package comp3350.acmis.objects;

import org.threeten.bp.ZoneId;
import java.io.Serializable;
import java.util.Objects;

public class Location  implements Serializable {
    private final String city;
    private final ZoneId zoneName;
    private final String country;
    private final String airport;


    public Location(String city, ZoneId zoneName, String country, String airport) {
        this.city = errorCheck(city, "City");
        this.zoneName = zoneName;
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
    public String toString() {
        return city + ", " + country;
    }


    //Error checking in constructor
    private String errorCheck(String value, String message) {
        if (value.trim().equals("")) {
            throw new IllegalArgumentException(message + " can not be empty");
        }
        return Objects.requireNonNull(value, message + " cannot be null").trim();
    }

    //equals method for testing purposes

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(getCity(), location.getCity()) && Objects.equals(getZoneName(), location.getZoneName()) && Objects.equals(getCountry(), location.getCountry()) && Objects.equals(getAirport(), location.getAirport());
    }


}

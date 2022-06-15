
package comp3350.acmis.objects;

import java.util.ArrayList;

public class Location {

    // Instance Variables --> What makes up a location ?
    private String city;
    private String country;
    private String airport;
    private ArrayList<Location> locsIncomingFlights;
    private ArrayList<Location> locsOutgoingFLights;

    // Constructor
    public Location(String city, String country, String airport) {
        this.city = city;
        this.country = country;
        this.airport = airport;
        locsIncomingFlights = new ArrayList<>();
        locsOutgoingFLights = new ArrayList<>();
    }

    // SETTERS
    public void addLocationIncoming(Location incoming){
        locsIncomingFlights.add(incoming);
    }
    public void addLocationOutgoing(Location outGoing){
        locsOutgoingFLights.add(outGoing);
    }

    // GETTERS
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
        return city +", "+country+", "+airport;
    }



}

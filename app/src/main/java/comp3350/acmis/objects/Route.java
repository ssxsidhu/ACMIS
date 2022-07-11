//THis is the way/ list of flights that user needs to book to travel from one place to another
//the route can be a direct flight or multiple flights connecting the source to the destination

package comp3350.acmis.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable{

    // What makes up a route for now ? Either a direct flight or a one stop over flight
    private ArrayList<Flight> route;

    // DEFAULT CONSTRUCTOR
    public Route() {
        route = new ArrayList<>();
    }

    // Constructor 3 --> Single or Direct Flight
    public Route(Flight directFlight) {
        route = new ArrayList<>();
        route.add(directFlight);
    }

    // SETTERS
    public String addToRoute(Flight flight) {
        if(flight != null ) {
            route.add(flight);
        }else{
            throw new NullPointerException();
        }
        return null;
    }

    public String removeFromRoute(Flight flight) {
        if(!route.isEmpty()) {
            route.remove(flight);
        }else{
            throw new NullPointerException();
        }
        return null;
    }

    // GETTERS
    public boolean isEmpty(){
        return route.isEmpty();
    }
    public ArrayList<Flight> getRoute() {
        return route;
    }

    public String getFlightsCSV() {
        String ret = "";
        for (int i = 0; i < route.size(); i++) {
            ret += route.get(i).getFlightId() + "";

            if (i != route.size()-1) {
                ret += ",";
            }
        }

        return ret;
    }

}

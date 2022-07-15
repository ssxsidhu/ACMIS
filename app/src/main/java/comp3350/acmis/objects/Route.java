//THis is the way/list of flights that user needs to book to travel from one place to another
//the route can be a direct flight or multiple flights connecting the source to the destination

package comp3350.acmis.objects;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Route implements Serializable {

    // INSTANCE VARIABLE -  What makes up a route for now ? Either a direct flight or a one stop over flight
    private ArrayList<Flight> route;

    // DEFAULT CONSTRUCTOR
    public Route() {
        route = new ArrayList<>();
    }

    // Constructor 1 --> Single or Direct Flight
    public Route(Flight directFlight) {
        route = new ArrayList<>();
        route.add(Objects.requireNonNull(directFlight, "Flight cannot be null"));
    }

    // SETTERS
    public String addToRoute(Flight flight) {
        if (flight != null) {
            route.add(flight);
        } else {
            throw new NullPointerException();
        }
        return null;
    }

    // GETTERS
    public boolean isEmpty() {
        return route.isEmpty();
    }

    public ArrayList<Flight> getRoute() {
        return route;
    }

    public String getRoutes(ArrayList<Flight> copyHere) {

        if (copyHere != null) {         // GUARD CONDITION --> Do Something Only if list in params is not NULL
            copyHere.clear();
            copyHere.addAll(route);
            return null;
        } else {
            throw new NullPointerException("Array List to store Flights is NULL !");
        }
    }

    public String getFlightsCSV() {
        String ret = "";

        for (int i = 0; i < route.size(); i++) {
            ret += route.get(i).getFlightId() + "";

            if (i != route.size() - 1) {
                ret += ",";
            }
        }

        if (ret.equals("")) {
            ret = null;
        }
        return ret;
    }
}
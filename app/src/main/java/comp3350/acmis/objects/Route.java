//THis is the way/ list of flights that user needs to book to travel from one place to another
//the route can be a direct flight or multiple flights connecting the source to the destination

package comp3350.acmis.objects;

import java.util.ArrayList;
import java.util.List;

public class Route {

    // What makes up a route for now ? Either a direct flight or a one stop over flight
    private ArrayList<Flight> route;

    // DEFAULT CONSTRUCTOR
    public Route() {
        route = new ArrayList<>();
    }

    // CONSTRUCTOR 1 --> Hard Copy
    public Route(List<Flight> flights) {
        route = new ArrayList<>();
        route.addAll(flights);
    }

    // Constructor 2 --> Soft Copy
    public Route(ArrayList<Flight> flights) {
        route = flights;
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

}

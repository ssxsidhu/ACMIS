//THis is the way/ list of flights that user needs to book to travel from one place to another
//the route can be a direct flight or multiple flights connecting the source to the destination

package comp3350.acmis.objects;

import java.util.ArrayList;

public class Route {

    // INSTANCE VARIABLE -  What makes up a route for now ? Either a direct flight or a one stop over flight
    private ArrayList<Flight> route;

    // CONSTRUCTOR 1 --> Hard Copy. This is an empty List. The Route Manager will populate this list with 1 or more flights
    public Route()
    {route = new ArrayList<>();}

    // CONSTRUCTOR 2 --> Single or Direct Flight
    public Route(Flight directFlight) {
        route = new ArrayList<>();
        route.add(directFlight);
    }

    // SETTERS
    public String addToRoute(Flight flight) {               // Add a flight to this Route.
        if(flight != null ) {
            route.add(flight);
        }else{
            throw new NullPointerException();
        }
        return null;
    }
    public String removeFromRoute(Flight flight) {          // Remove a flight from this Route.
        if(!route.isEmpty()) {
            route.remove(flight);
        }else{
            throw new NullPointerException();
        }
        return null;
    }

    // GETTERS
    public ArrayList<Flight> getRoute(){
        return route;
    }
    public boolean isEmpty(){                       // To Check for Bookings. We Cannot Book a flight if Route is Empty because that means no Routes were Found
        return route.isEmpty();
    }
}

package comp3350.acmis.objects;

import java.util.ArrayList;
import java.util.List;

import comp3350.acmis.R;

public class Route {
    private ArrayList<Flight> route;
    public Route(){
        route = new ArrayList<>();
    }
    public Route(List<Flight> flights){
        route = new ArrayList<>();
        route.addAll(flights);
    }

    public Route(ArrayList<Flight> flights){
        route = flights;
    }


    public String addToRoute(Flight flight){
        route.add(flight);
        return  null;
    }

    public String removeFromRoute(Flight flight){
        route.remove(flight);
        return  null;
    }

    public boolean isEmpty(){
        return route.isEmpty();
    }

    public ArrayList<Flight> getRoute() {
        return route;
    }

}

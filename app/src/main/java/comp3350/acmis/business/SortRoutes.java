package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.acmis.objects.Route;

public class SortRoutes {

    public SortRoutes() {

    }

    public String lowestPrice(ArrayList<Route> sortThis) {

        Collections.sort(sortThis, new RouteCostCompare());
        return null;
    }

    public String leastStops(ArrayList<Route> sortThis) {

        Collections.sort(sortThis, new RouteStopCompare());
        return null;
    }

    public String earliestDepart(ArrayList<Route> sortThis) {

        Collections.sort(sortThis, new RouteDepartCompare());
        return null;
    }

    public String earliestArrival(ArrayList<Route> sortThis) {

        Collections.sort(sortThis, new RouteArriveCompare());
        return null;
    }
}

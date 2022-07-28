package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.acmis.objects.Route;

public class SortRoutes {

    public SortRoutes() {

    }

    public String lowestPrice(ArrayList<Route> sortThis) {

        if (sortThis != null)
            Collections.sort(sortThis, new RouteCostCompare());
        else if (sortThis == null)
            throw new NullPointerException("List passed to Sort is NULL !");
        else if (sortThis.isEmpty())
            throw new IllegalStateException("List Passed to Sort is EMPTY !");
        return null;
    }

    public String leastStops(ArrayList<Route> sortThis) {

        if (sortThis != null)
            Collections.sort(sortThis, new RouteStopCompare());
        else if (sortThis == null)
            throw new NullPointerException("List passed to Sort is NULL !");
        else if (sortThis.isEmpty())
            throw new IllegalStateException("List Passed to Sort is EMPTY !");
        return null;
    }

    public String earliestDepart(ArrayList<Route> sortThis) {

        if (sortThis != null)
            Collections.sort(sortThis, new RouteDepartCompare());
        else if (sortThis == null)
            throw new NullPointerException("List passed to Sort is NULL !");
        else if (sortThis.isEmpty())
            throw new IllegalStateException("List Passed to Sort is EMPTY !");
        return null;
    }

    public String earliestArrival(ArrayList<Route> sortThis) {

        if (sortThis != null)
            Collections.sort(sortThis, new RouteArriveCompare());
        else if (sortThis == null)
            throw new NullPointerException("List passed to Sort is NULL !");
        else if (sortThis.isEmpty())
            throw new IllegalStateException("List Passed to Sort is EMPTY !");
        return null;
    }
}

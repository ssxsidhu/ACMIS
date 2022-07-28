package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.acmis.objects.Route;

public class SortRoutes {

    public void lowestPrice(ArrayList<Route> sortThis) {
        Collections.sort(sortThis, new RouteCostCompare());
    }

    public void leastStops(ArrayList<Route> sortThis) {
        Collections.sort(sortThis, new RouteStopCompare());
    }

    public void earliestDepart(ArrayList<Route> sortThis) {
        Collections.sort(sortThis, new RouteDepartCompare());
    }

    public void lowestDuration(ArrayList<Route> sortThis) {
        Collections.sort(sortThis, new RouteDurationCompare());
    }


    private static class RouteCostCompare implements Comparator<Route> {

        @Override
        public int compare(Route r1, Route r2) {
            int r1Cost = new UseRouteFlights(r1).getRouteTotalCost();
            int r2Cost = new UseRouteFlights(r2).getRouteTotalCost();
            return Integer.compare(r1Cost, r2Cost);
        }
    }

    private static class RouteStopCompare implements Comparator<Route> {

        @Override
        public int compare(Route r1, Route r2) {
             return Integer.compare(new UseRouteFlights(r1).getNumStops(),new UseRouteFlights(r2).getNumStops());
        }
    }

    private static class RouteDepartCompare implements Comparator<Route> {
        @Override
        public int compare(Route r1, Route r2) {
            return new UseRouteFlights(r1).getConnectDepartureZdt().compareTo(new UseRouteFlights(r2).getConnectDepartureZdt());
        }
    }

    private static class RouteDurationCompare implements Comparator<Route> {
        @Override
        public int compare(Route r1, Route r2) {
            return Long.compare(new UseRouteFlights(r1).getRawTotalDuration(),new UseRouteFlights(r2).getRawTotalDuration());
        }
    }
}

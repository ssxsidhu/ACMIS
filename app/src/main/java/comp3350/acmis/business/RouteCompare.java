package comp3350.acmis.business;

import java.util.Comparator;

import comp3350.acmis.objects.Route;


class RouteCostCompare implements Comparator<Route> {

    public RouteCostCompare() {

    }

    @Override
    public int compare(Route r1, Route r2) {

        if(new UseRouteFlights(r1).getRouteTotalCost() < new UseRouteFlights(r2).getRouteTotalCost()) {
            return 1;
        }
        else  {
            return  0;
        }

    }
}

class RouteStopCompare implements Comparator<Route> {

    public RouteStopCompare() {

    }

    @Override
    public int compare(Route r1, Route r2) {

        if(new UseRouteFlights(r1).getNumStops() < new UseRouteFlights(r2).getNumStops()) {
            return 1;
        }
        else  {
            return  0;
        }

    }
}

class RouteDepartCompare implements Comparator<Route> {
    public RouteDepartCompare() {

    }

    @Override
    public int compare(Route r1, Route r2) {

        if(new UseRouteFlights(r1).getConnectDepartureZdt().isBefore(new UseRouteFlights(r2).getConnectDepartureZdt())) {
            return 1;
        }
        else  {
            return  0;
        }

    }
}

class RouteArriveCompare implements Comparator<Route> {
    public RouteArriveCompare() {

    }

    @Override
    public int compare(Route r1, Route r2) {
        if(new UseRouteFlights(r1).getConnectArrivalZdt().isBefore(new UseRouteFlights(r2).getConnectArrivalZdt())) {
            return 1;
        }
        else  {
            return  0;
        }

    }
}
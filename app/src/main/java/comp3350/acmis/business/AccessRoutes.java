package comp3350.acmis.business;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class AccessRoutes {
    private final RouteManager routeManager;
    private final Location depart;
    private final Location dest;

    public AccessRoutes(Location departure, Location destination){
        routeManager = new RouteManager();
        depart = departure;
        dest = destination;
    }

    public String getAvailableRoutes(ArrayList<Route> flightsAvailable, LocalDate dateFilter){
        routeManager.searchRoute(depart,dest,flightsAvailable,dateFilter.atStartOfDay(depart.getZoneName()));
        return null;
    }



}

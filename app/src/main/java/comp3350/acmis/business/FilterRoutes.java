package comp3350.acmis.business;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class FilterRoutes {
    private final ArrayList<Route> allPossibleRoutes;
    private final BookingManager bookingManager;
    private final RouteManager routeManager;
    private final Location depart;
    private final Location dest;

    public FilterRoutes(Location departure, Location destination){
        allPossibleRoutes = new ArrayList<>();
        bookingManager = new BookingManager();
        routeManager = new RouteManager();
        depart = departure;
        dest = destination;
    }

    public String getFilteredRoutes(ArrayList<Route> flightsAvailable, LocalDate dateFilter){
//        String result = bookingManager.searchRoute(depart,dest,allPossibleRoutes);
        String result = routeManager.searchRoute(depart,dest,allPossibleRoutes);
        AccessRouteFlights accessRouteFlights;
        if(result==null){
            for(int i=0;i<allPossibleRoutes.size();i++){
                accessRouteFlights = new AccessRouteFlights(allPossibleRoutes.get(i));
                //for easy testing commenting it out
//                if(accessRouteFlights.getConnectDepartureDate().equals(dateFilter))
                {
                    flightsAvailable.add(allPossibleRoutes.get(i));
                }
            }
        }

        if(!flightsAvailable.isEmpty())
            return null;
        else
            return "No flights found";
    }



}

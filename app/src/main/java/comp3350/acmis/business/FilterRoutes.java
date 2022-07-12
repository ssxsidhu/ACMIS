package comp3350.acmis.business;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class FilterRoutes {
    private ArrayList<Route> allPossibleRoutes;
    private BookingManager bookingManager;
    private Location depart,dest;

    public FilterRoutes(Location departure, Location destination){
        allPossibleRoutes = new ArrayList<>();
        bookingManager = new BookingManager();
        depart = departure;
        dest = destination;
    }

    public String getFilteredRoutes(ArrayList<Route> flightsAvailable, LocalDate dateFilter){
        String result = bookingManager.searchRoute(depart,dest,allPossibleRoutes);
        AccessRouteFlights accessRouteFlights;
        if(result==null){
            for(int i=0;i<allPossibleRoutes.size();i++){
                accessRouteFlights = new AccessRouteFlights(allPossibleRoutes.get(i));
                if(accessRouteFlights.getConnectDepartureDate().equals(dateFilter)){
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

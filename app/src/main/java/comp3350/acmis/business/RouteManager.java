package comp3350.acmis.business;


import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.MyGraph;
import comp3350.acmis.objects.Route;
import comp3350.acmis.persistence.DataAccess;

public class RouteManager {

    // INSTANCE VARIABLES
    private final MyGraph graph;                  // This is the Graph storing all the Connections..
    private final DataAccess dataAccess;
    private ArrayList<Flight> flightList;
    private ArrayList<Location> locationList;
    private ArrayList<ArrayList<Location>> allPossiblePaths = new ArrayList<ArrayList<Location>>();
    private ArrayList<Route> allConnectedRoutes = new ArrayList<>();

    // CONSTRUCTOR
    public RouteManager() {
        graph = new MyGraph();
        dataAccess = Services.getDataAccess();
        flightList = new ArrayList<>();
        locationList = new ArrayList<>();
        dataAccess.getAllFlights(flightList);
        dataAccess.getLocations(locationList);
        graph.populateSourceNodes(locationList);
        graph.populateEdges(flightList);
    }

    // Return a list of ALL POSSIBLE ROUTES because that's how it works.
    public String searchRoute(Location source, Location dest, ArrayList<Route> returnThis, ZonedDateTime departureDate) {

        allPossiblePaths.clear();
        allConnectedRoutes.clear();

        //  checkDirectRoute(source, dest, returnThis);
        checkConnectedRoutes(source, dest);
        if (!allPossiblePaths.isEmpty()) {
            buildRoute(source, departureDate);
            returnThis.addAll(allConnectedRoutes);                       // Direct and Connected Routes are added to param list now.
        }

        if (returnThis.isEmpty())
            return "No flights found";
        return null;
    }


    private String checkConnectedRoutes(Location source, Location dest) {
        ArrayList<Location> visited = new ArrayList<>();
        ArrayList<Location> path = new ArrayList<>();
        depthFirst(graph, source, dest, visited, path);
        return null;
    }


    // PRIVATE HELPER METHOD        //https://thealgorists.com/Algo/AllPathsBetweenTwoNodes
    private void depthFirst(MyGraph graph, Location source, Location dest, ArrayList<Location> visited, ArrayList<Location> path) {
        // BASE CASE
        if (source.equals(dest)) {
            allPossiblePaths.add((ArrayList<Location>) path.clone());
            return;
        }

        visited.add(source);                                        // Add this so we don't come back to source for checking every time and thus prevent dead loop.
        ArrayList<Location> neighbors = new ArrayList<>();          // These are the neighbors of the source Node. CHECK ALL NEIGHBORS FOR A PATH TO DEST
        graph.getNeighborCities(source, neighbors);                  // Initialise all neighbors of the source node into this list.


        for (int i = 0; i < neighbors.size(); i++) {                       // Iterate over each Neighbor and perform tasks...
            if (!visited.contains(neighbors.get(i))) {
                path.add(neighbors.get(i));
                depthFirst(graph, neighbors.get(i), dest, visited, path);
                path.remove(neighbors.get(i));
            }
        }
        visited.remove(source);
    }

    private void buildRoute(Location src, ZonedDateTime firstDepartDate) {

        for (int i = 0; i < allPossiblePaths.size(); i++) {                    // Iterate Over Every List Contained inside this List
            allPossiblePaths.get(i).add(0, src);
            Route addThis = new Route();           // Store Flights connecting Locations here
            ArrayList<Location> locList = allPossiblePaths.get(i);      // Store Location list in every index in Above List here. This list is what we will work on to Connect using flights
            ArrayList<Flight> flightList = new ArrayList<>();

            if (locList.size() <= 4 && locList.size() > 1) {
                boolean today = true;
                boolean checkedTwoDays = false;
                boolean noConnectedFlight = false;
                int originalRouteSize = locList.size();
                ZonedDateTime checkDateTime = firstDepartDate;
                do {
                    // Iterate until only one loc remains. Logic is that get flight from index 0 to index 1. Then delete index 0.
                    // Do this until list size is one. This should give us all connecting flights.
                    dataAccess.getFlights(locList.get(0), locList.get(1), checkDateTime, flightList);
                    if (!flightList.isEmpty() && !noConnectedFlight) {
                        boolean added = false;
                        for (int j = 0; j < flightList.size() && !added; j++) {
                            if (flightList.get(j).getDepartureDateTime().isAfter(checkDateTime)) {
                                if (originalRouteSize != 2) {
                                    addThis.addToRoute(flightList.get(j));
                                    checkDateTime = flightList.get(j).getArrivalDateTime();
                                    added = true;
                                } else
                                    allConnectedRoutes.add(new Route(flightList.get(j)));//for direct multiple flights
                                noConnectedFlight = false;
                            } else
                                noConnectedFlight = true;
                        }
                        locList.remove(0);
                        flightList.clear();
                    } else if (today) {
                        today = false;
                        checkDateTime = checkDateTime.plusDays(1);
                    } else {
                        checkedTwoDays = true;
                    }
                } while (locList.size() != 1 && !addThis.isEmpty() && !checkedTwoDays);

                if (!checkedTwoDays && !addThis.isEmpty() && !noConnectedFlight)
                    allConnectedRoutes.add(addThis);
            }
        }
    }
}

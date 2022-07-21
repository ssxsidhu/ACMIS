package comp3350.acmis.business;


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
    private ArrayList<Flight> flightList = new ArrayList<>();
    private ArrayList<Location> locationList = new ArrayList<>();
    
    private ArrayList<ArrayList<Location>> allPossiblePaths = new ArrayList<ArrayList<Location>>();
    private ArrayList<Route> allConnectedRoutes = new ArrayList<>();

    // CONSTRUCTOR
    public RouteManager() {
        graph = new MyGraph();
        dataAccess = Services.getDataAccess(Main.dbName);
        flightList = new ArrayList<>();
        locationList = new ArrayList<>();
        dataAccess.getAllFlights(flightList);
        dataAccess.getLocations(locationList);
        graph.populateSourceNodes(locationList);
        graph.populateEdges(flightList);
    }

    // Return a list of ALL POSSIBLE ROUTES because that's how it works.
    public String searchRoute(Location source, Location dest, ArrayList<Route> returnThis) {

        allPossiblePaths.clear();
        allConnectedRoutes.clear();

      //  checkDirectRoute(source, dest, returnThis);
        checkConnectedRoutes(source, dest);
        if(!allPossiblePaths.isEmpty()) {
            buildRoute();
            returnThis.addAll(allConnectedRoutes);                       // Direct and Connected Routes are added to param list now.
        }

        if (returnThis.isEmpty())
            return "No flights found";
        return null;
    }

    // PRIVATE HELPER METHOD
    // This method checks for any direct routes between the source and destination. If not
    private String checkDirectRoute(Location source, Location dest, ArrayList<Route> returnThis) {

        ArrayList<Location> tempCities = new ArrayList<>();
        graph.getNeighborCities(source, tempCities);
        Flight temp = null;

        for (int i = 0; i < tempCities.size(); i++) {           // Iterate through the list of all cities. If destination city exists there is possibly a direct route.
            if (tempCities.get(i).equals(dest)) {
                temp = getFlight(source, dest);
                returnThis.add(new Route(temp));
            }
        }

        if (temp == null)                                       // Check if there was a direct route we could find.
            return "NO DIRECT ROUTES FOUND";
        else {
            returnThis.add(new Route(temp));
            return null;
        }
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

    //     PRIVATE HELPER METHOD
//     This method iterates through the entire list of all flights in the data base.
//     It extracts the particular flight that links the requested source and destination.
//     The source and destination may be the true source and true destination or a stopover
//     source or a stop over destination. The essential job of this method is to just return
//     the particular flight linking 2 cities to the methods in the Route Manager Class.
    private Flight getFlight(Location source, Location destination) {
        Flight returnThis = null;
        int index = 0;                                                   // Index variable for our Flight List

        while (returnThis == null && index < flightList.size()) {            // Iterate until we find the particular flight linking the cities or until the list runs out.

            Flight temp = flightList.get(index);                        // This is the Current Flight the Iteration is checking for.
            if (temp.getSource().equals(source) &&
                    temp.getDestination().equals(destination)) {            // If found simply make returnThis NON-NULL and Loop Breaks.
                returnThis = temp;
            }
            index++;
        }

        return returnThis;
    }

    private String buildRoute()
    {
        for(int i=0;i<allPossiblePaths.size();i++) {                    // Iterate Over Every List Contained inside this List

            Route addThis = new Route();           // Store Flights connecting Locations here
            ArrayList<Location> locList = allPossiblePaths.get(i);      // Store Location list in every index in Above List here. This list is what we will work on to Connect using flights

            while (locList.size()!=1) {                                 // Iterate until only one loc remains. Logic is that get flight from index 0 to index 1. Then delete index 0.
                                                                        // Do this until list size is one. This should give us all connecting flights.
                Flight connectLocs = getFlight(locList.get(0),locList.get(1));
                addThis.addToRoute(connectLocs);
                locList.remove(0);
            }
            allConnectedRoutes.add(addThis);
        }

        return null;
    }
}

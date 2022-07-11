package comp3350.acmis.business;


import java.util.ArrayList;
import java.util.Collections;

import comp3350.acmis.R;
import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.MyGraph;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.Node;

import comp3350.acmis.persistence.DataAccessStub;

public class RouteManager {

    // INSTANCE VARIABLES
    private MyGraph graph;                  // This is the Graph storing all the Connections..
    private DataAccessStub dataAccess;
    private ArrayList<Flight> flightList;
    private ArrayList<Location> locationList;

    // CONSTRUCTOR
    public RouteManager()
    {
        graph = new MyGraph();
        dataAccess = Services.getDataAccess(Main.dbName);

        dataAccess.getAllFlights(flightList);
        dataAccess.getLocations(locationList);
    }

    // Return a list of ALL POSSIBLE ROUTES because that's how it works.
    public String searchRoute(Location source, Location dest, ArrayList<Route> returnThis)
    {
        ArrayList<Route> directRoutes = new ArrayList<>();
        ArrayList<Route> connectedRoutes = new ArrayList<>();

        checkDirectRoute(source,dest,returnThis);


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

            }
        }

        if (temp == null)                                       // Check if there was a direct route we could find.
            return "NO DIRECT ROUTES FOUND";
        else {
            returnThis.add(new Route(temp));
            return null;
        }
    }
    private String checkConnectedRoutes(Location source, Location dest, ArrayList<Route> returnThis) {

        return null;
    }

//     PRIVATE HELPER METHOD
//     This method iterates throught the entire list of all flights in the data base.
//     It extracts the particular flight that links the requested source and destination.
//     The source and destination may be the true source and true destination or a stopover
//     source or a stop over destination. The essential job of this method is to just return
//     the particular flight linking 2 cities to the methods in the Route Manager Class.
    private Flight getFlight(Location source, Location destination)
    {
        Flight returnThis = null;
        int index = 0;                                                   // Index variable for our Flight List

        while(returnThis==null && index<flightList.size()) {            // Iterate until we find the particular flight linking the cities or until the list runs out.

            Flight temp = flightList.get(index);                        // This is the Current Flight the Iteration is checking for.
            if(temp.getSource().equals(source) &&
                temp.getDestination().equals(destination)) {            // If found simply make returnThis NON-NULL and Loop Breaks.

                returnThis=temp;
            }
        }

        return returnThis;
    }
}

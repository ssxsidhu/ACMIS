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

    private String checkDirectRoute(Location source, Location dest, ArrayList<Route> returnThis) {

        ArrayList<Node> tempNodes = new ArrayList<>();
        graph.getAdjacentNodes(source,tempNodes);
        Flight temp = null;

        for(int i=0;i<tempNodes.size();i++)
        {
            if(tempNodes.get(i).getLoc().equals(dest)){
             temp = getFlight(source,dest);

            }

            returnThis.add(new Route(temp));

        }

        return null;
    }

    private Flight getFlight(Location source, Location destination)      // get the FLight that connects these 2 cities. We dont care if they are stopovers are direct routes
    {
        Flight returnThis = null;

        ArrayList <Flight> allFlights = new ArrayList();
        dataAccess.getAllFlights(allFlights);

        // Iterate Until We find the particular flight in the List we got from Data Base.
        for(int i=0;i<allFlights.size();i++){

            Flight tempFlight = allFlights.get(i);                                                           // Increase Readability. Iteration is checking this particular flight in Database.
            if(tempFlight.getDestination().equals(destination) && tempFlight.getSource().equals(source)){   // If The flight connects the above 2 cities, assign this to param flight variable.
                returnThis = tempFlight;
            }
        }

        return returnThis;
    }
}

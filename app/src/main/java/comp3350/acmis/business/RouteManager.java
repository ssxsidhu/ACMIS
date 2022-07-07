package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.MyGraph;
import comp3350.acmis.objects.Route;
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

    public String searchRoute(Location source, Location dest, Route returnThis)
    {

        return null;
    }
}

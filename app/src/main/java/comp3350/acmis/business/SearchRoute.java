package comp3350.acmis.business;

//import androidx.room.jarjarred.org.antlr.v4.misc.Graph;






import java.util.ArrayList;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.persistence.DataAccessStub;

public class SearchRoute {

    private Graph<Location> graph;
    private DataAccessStub data;
    private ArrayList<Flight> flights;

    public SearchRoute() {
//        this.graph = new Graph<>();
        data = Services.getDataAccess(Main.dbName);
        data.getAllFlights(flights);
        this.addAllEdges(flights);
    }

    //takes a list of all the flights in the database,
    //Using each flight an edge is created between 2 location and added to the graph
    public void addAllEdges(ArrayList<Flight> flights){
        Flight input;
        for(int i=0; i<flights.size();i++){
            input= flights.get(i);
            //get the source and the location.
            // the edge will be from the source to the destination
//            graph.addEdge(input.getSource(),input.getDestination());
        }
    }

    //get all the routes from the source to the destination
    //returns an arraylist of routes that stores all the routes.
    public ArrayList<Route> getRoute(String source, String destination){
        ArrayList<Route> listOfPaths = new ArrayList<>();

        return findAllRoutes(source,destination,listOfPaths);
    }
    //helper method of getRoute
    private ArrayList<Route> findAllRoutes(String source, String destination, ArrayList<Route> listOfPaths) {


         return null;
    }


    public Graph<Location> getGraph(Location l) {
        return graph;
    }

}

/*
*  This Class implements a Graph using Adjacency Lists. The Adjacency Lists are stored in an
*  ArrayList and The Source Node of every List is accessible so as to ADD sourceNodes ONLY. The Graph
*  Class Basically Stores All the sourceNodes and all the edges from all the sourceNodes to other connected sourceNodes
* */

package comp3350.acmis.objects;
import java.util.ArrayList;

public class MyGraph {

    // INSTANCE VARIABLES
    private ArrayList<AdjacencyList> sourceNodes;         // Stores all the sourceNodes of a graph in a list;
    private int numSourceNodes;
    private int numEdges;

    // CONSTRUCTOR
    public MyGraph() {

        sourceNodes = new ArrayList<>();
        numSourceNodes = 0;
        numEdges = 0;
    }

    // SETTERS
    public String addNode(Location newLocation)
    {
        Node newNode = new Node(newLocation);
        sourceNodes.add(new AdjacencyList(newNode));
        return null;
    }

    public String addEdge(Flight newFlight)
    {
        Location start = newFlight.getSource();

        // Iterate until we find sourceCity
        for(int i=0;i<sourceNodes.size();i++)
        {
           //if(sourceNodes.get(i).equals(start))              // If we Find that Node then add the Flight and Location to this Node
            if(sourceNodes.get(i).getSourceCity().equals(start.getCity()))
            {
                sourceNodes.get(i).addNext(newFlight.getDestination(), newFlight);        // Supply Connecting Node and the flight to that connecting Node to the Adjacency List having that Source Node.
            }
        }

        return null;
    }

    // Iterate the List, Supplying every Source Node to the Graph
    public String populateSourceNodes(ArrayList<Location> list) {

        for(int i=0;i<list.size();i++) {
            addNode(list.get(i));
        }
        return null;
    }

    // Iterate the List, Supplying every Flight and Connecting Node to the Graph.
    public String populateEdges(ArrayList<Flight> list){

        for(int i=0;i<list.size();i++){
            addEdge(list.get(i));
        }
        return null;
    }

    // GETTERS
    public int getNumSourceNodes() {
        return numSourceNodes;
    }
    public int getNumEdges() {
        return numEdges;
    }

}

/*
 *  This Class implements a Graph using Adjacency Lists. The Adjacency Lists are stored in an
 *  ArrayList and The Source Node of every List is accessible so as to ADD sourceNodes ONLY. The Graph
 *  Class Basically Stores All the sourceNodes and all the edges from all the sourceNodes to other connected sourceNodes
 * */

package comp3350.acmis.objects;

import java.util.ArrayList;

public class MyGraph {

    // INSTANCE VARIABLES
    private final ArrayList<AdjacencyList> sourceNodes;         // Stores all the sourceNodes of a graph in a list;
    private int numSourceNodes;
    private int numEdges;

    // CONSTRUCTOR
    public MyGraph() {
        sourceNodes = new ArrayList<>();
        numSourceNodes = 0;
        numEdges = 0;
    }

    // SETTERS
    public String addNode(Location newLocation) {

        if (newLocation != null) {
            sourceNodes.add(new AdjacencyList(newLocation));
            numSourceNodes++;

        } else {
            throw new NullPointerException("Location Cannot be NULL !");
        }
        return null;
    }

    public String addEdge(Flight newFlight) {

        if (newFlight != null) {
            Location start = newFlight.getSource();                 // For easier Readability.

            // Iterate until we find sourceCity the flight originates from.
            for (int i = 0; i < sourceNodes.size(); i++) {

                if (sourceNodes.get(i).getSourceCity().equals(start.getCity()))                // If we Find that Node then add the Flight and Location to this Node
                {
                    sourceNodes.get(i).addNext(newFlight.getDestination(), newFlight);        // Supply Connecting Node and the flight to that connecting Node to the Adjacency List having that Source Node.
                    numEdges++;
                }
            }
        } else {
            throw new NullPointerException("Flight cannot be NULL !");
        }

        return null;
    }

    // Iterate the List, Supplying every Source Node to the Graph
    public String populateSourceNodes(ArrayList<Location> list) {

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                addNode(list.get(i));
            }
        } else if (list == null) {
            throw new NullPointerException("List Passed for Populating Source Nodes is NULL !");
        } else if (list.isEmpty()) {
            throw new IllegalStateException("List Passed for Populating Source Nodes is EMPTY !");
        }
        return null;
    }

    // Iterate the List, Supplying every Flight and Connecting Node to the Graph.
    public String populateEdges(ArrayList<Flight> list) {

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                addEdge(list.get(i));
            }
        } else if (list == null) {
            throw new NullPointerException("List Passed for Populating Source Nodes is NULL !");
        } else if (list.isEmpty()) {
            throw new IllegalStateException("List Passed for Populating Edges is EMPTY !");
        }

        return null;
    }

    // Get Adjacent Nodes connected by an Edge for the Given Node
    public String getNeighborCities(Location getNeighbors, ArrayList<Location> returnCities) {

        if (getNeighbors != null && returnCities != null) {

            if (!containsSourceCity(getNeighbors))                                     // Do Something only if requested City exists in the Graph.
                return "Source City Does Not Exist in Graph";
            else {

                int index = 0;
                while (!getNeighbors.getCity().equals(sourceNodes.get(index).getSourceCity()) &&
                        index < sourceNodes.size()) {                                  // Iterate until we find the  requested sourceNode in our graph.

                    index++;
                }

                AdjacencyList temp = sourceNodes.get(index);                        // Once we find the Adjacency List having getNeighbors as source Node,
                temp.copyList(returnCities);                                        // Extract all the cities connected to Source Node and return in a List.

                return null;
            }
        } else {
            throw new NullPointerException("Location or List for Getting cities cannot be NULL !");
        }
    }

    // GETTERS
    public int getNumSourceNodes() {
        return numSourceNodes;
    }

    public int getNumEdges() {
        return numEdges;
    }

    // The following method checks if a requested Location or City
    // Exists in our Graph or Not. It traverses through all adjacency
    // Lists stored in the Graph and returns a boolean variable
    public boolean containsSourceCity(Location checkThis) {

        if (checkThis != null) {
            boolean exists = false;
            for (int i = 0; i < sourceNodes.size(); i++) {

                if (sourceNodes.get(i).contains(checkThis))
                    exists = true;
            }

            return exists;
        } else {
            throw new NullPointerException("PARAMETER PASSED IS NULL !");
        }
    }
}
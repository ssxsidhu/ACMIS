/*
 * This class implements an Adjacency List. The adjacency List stores all the Nodes
 * that this particular Node is connected to. It DOES NOT STORE THE ROUTE ONLY THE CONNECTED NODES
 * Figuring out a Route will be done by the Graph and Route Manager Classes.
 * An Adjacency List stores two kinds of Nodes - One that has a single param constructor and one that has double params.
 * A Node with a single param is a Source Node. A node with 2 params are the connected Nodes to that Source Node
 * */

package comp3350.acmis.objects;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AdjacencyList {

    // INSTANCE VARIABLES
    private final Node source;                    // This is the Node from which Flights Originate. There will be several such Nodes stored in a list in some class.
    private int size;                       // Stores number of nodes but also denotes the total stopovers in a Route.

    // CONSTRUCTOR
    public AdjacencyList(Location source) {

        if (source != null) {
            this.source = new Node(source);
            size = 0;
        } else
            throw new NullPointerException();
    }

    // SETTERS - WRAPPER METHOD
    public String addNext(Location addThisLoc, Flight newFlight) {

        if (addThisLoc != null && newFlight != null) {
            Node addThis = new Node(addThisLoc, newFlight);
            return addNext(addThis);
        } else {
            throw new NullPointerException();
        }
    }

    // GETTERS
    public int getSize() {
        return size;
    }

    public String getSourceCity() {
            return source.getLoc().getCity();
    }

    public String copyList(ArrayList<Location> copyHere) {                      // Deep Copy of our List. We are returning ONLY LOCATIONS

        if (copyHere == null) {                                                 // GUARD CONDITION --> Do Something ONLY if we have a valid list.
            throw new NullPointerException();
        }

        Node temp = source;
        while (temp.getNext() != null) {                                       // Iterate until the last Node and extract Locations and push to List in Params.
            if (!copyHere.contains(temp.getLoc())) {
                copyHere.add(temp.getLoc());
            }
            temp = temp.getNext();
        }

        if (!copyHere.contains(temp.getLoc()))
            copyHere.add(temp.getLoc());                                        // Make sure we dont miss last Location. Structure of Iteration does not add last node to List in Params
        return null;
    }

    public boolean contains(Location thisLoc) {

        boolean exists = false;
        Node temp = source;
        while (temp.getNext() != null && !exists) {                             // Iterate until we find the Destination in the Adjacency List or until the List runs out.

            if (temp.getLoc().equals(thisLoc)) {
                exists = true;
            }
            temp = temp.getNext();
        }

        if (temp.getLoc().equals(thisLoc))                        // Check for last Node. Iteration DOES NOT CHECK FOR LAST NODE. Hence checking here.
            exists = true;

        return exists;
    }


    // TO STRING
    @NonNull
    public String toString() {

        Node temp = source;
        String returnThis = "(((";

        returnThis += source.getLoc().getCity();                              // Concat Source City
        returnThis += ")))";
        returnThis += "  --->";

        // Iterate Until Every Node has its toString() invoked.
        while (temp.getNext() != null) {                                        // Iterate List and Concat every Node to String.

            returnThis = returnThis + temp.getNext();                       // FORMAT : ((( SOURCE ))) ---> ((( DEST 1 ))) ---> ((( DEST 2 )))
            returnThis += " ---> ";
            temp = temp.getNext();
        }

        returnThis += temp;                                                   // Concat Last Node to list. ITERATION DOES NOT CHECK FOR LAST NODE.
        return returnThis;
    }

    // PRIVATE HELPER METHOD
    // This Method is what actually adds the Node. User Only needs to supply Location and Flight to that Location in Wrapper Method above.
    // It is guaranteed to have a Node Object passed to it because the wrapper method creates the node.
    private String addNext(Node addThis) {     // This method will always add to end because it simply stores to what other Nodes this Node is connected tp

        Node temp = source;
        if (source.getNext() == null) {         // If Nothing exists, then this has to be added immediately next.
            source.setNext(addThis);
        } else {
            while (temp.getNext() != null) {    // Iterate until last Node and then Add addThis
                temp = temp.getNext();
            }
            // Add After the Last Node.
            temp.setNext(addThis);
        }

        size++;
        return null;
    }
}


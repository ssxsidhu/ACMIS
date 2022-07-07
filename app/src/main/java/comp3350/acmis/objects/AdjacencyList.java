/*
* This class implements an Adjacency List. The adjacency List stores all the Nodes
* that this particular Node is connected to. It DOES NOT STORE THE ROUTE ONLY THE CONNECTED NODES
* Figuring out a Route will be done by the Graph and Route Manager Classes.
* An Adjacency List stores two kinds of Nodes - One that has a single param constructor and one that has double params.
* A Node with a single param is a Source Node. A node with 2 params are the connected Nodes to that Source Node
* */

package comp3350.acmis.objects;

import androidx.annotation.NonNull;

import org.threeten.bp.Duration;

public class AdjacencyList {

    // INSTANCE VARIABLES
    private Node source;                    // This is the Node from which Flights Originate. There will be several such Nodes stored in a list in some class.
    private int size;                       // Stores number of nodes but also denotes the total stopovers in a Route.

    // CONSTRUCTOR
    public AdjacencyList(Node source) {
        this.source = source;
        size = 0;
    }

    // SETTERS - WRAPPER METHOD
    public String addNext(Location addThisLoc, Flight newFlight) {

        Node addThis = new Node(addThisLoc, newFlight);
        return addNext(addThis);
    }

    // PRIVATE HELPER METHOD
    // This Method is what actually adds the Node. User Only needs to supply Location and Flight to that Location in Wrapper Method above.
    private String addNext(Node addThis) {     // This method will always add to end because it simply stores to what other Nodes this Node is connected tp

        Node temp = source;
        if (source.getNext() == null) {         // If Nothing exists, then this has to be added immediately next.
            source.setNext(addThis);
        } else {
            while (temp.getNext() != null) {    // Iterate until last Node and then Add addThis
                temp = temp.getNext();

                temp.setNext(addThis);
            }
        }
        return null;
    }

    // GETTERS
    public int getSize() {
        return size;
    }
    public String getSourceCity(){
        return source.getLoc().getCity();
    }               // MAY NOT NEED THIS IF USING ONE BELOW

    // toString()
    @NonNull
    public String toString(){

        Node temp = source;
        String returnThis = "(((";

        returnThis+=source.getLoc().getCity();
        returnThis+=")))";
        returnThis+="  --->";

        // Iterate Until Every Node has its toString() invoked.
        while(temp.getNext()!=null){

            returnThis = returnThis + temp.getNext();
            returnThis+=" ---> ";
            temp = temp.getNext();
        }

        returnThis+=temp;
        return returnThis;
    }
}


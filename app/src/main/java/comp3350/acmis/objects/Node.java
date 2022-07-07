package comp3350.acmis.objects;

import androidx.annotation.NonNull;

import org.threeten.bp.Duration;

class Node {

    // INSTANCE VARIABLES
    private Location loc;                       // The location that is being represented as a Node.
    private Flight flight;                      // The flight that connects this Node to some bode
    private Duration weightToNode;                // This variable basically extracts weight from the Location. The weight is from THAT NODE to THIS NODE
    private Node next;

    // CONSTRUCTOR 1  -- This constructor is Necessary for the Linked Nodes that Arise from the Source Nodes in a List. The List will store the Source Node as Root Node and the nodes connected to that Root Node will be the linked Nodes
    public Node(Location newData, Flight newFlight) {

        loc = newData;
        flight = newFlight;
        weightToNode = newFlight.getDuration();
        next = null;
    }

    // CONSTRUCTOR 2 -- This constructor is Necessary for Populating a List with ONLY NODES. This List would contain the Source Node as Root Node and the Other Connected Nodes as its Linked Nodes
    public  Node(Location newData)
    {
        loc = newData;
        flight = null;
        weightToNode = null;
        next = null;
    }

    // SETTERS
    public void setNext(Node next) {
        this.next = next;
    }
    public Duration getWeightToNode() {
        return weightToNode;
    }
    public void setWeightToNode(Duration weightToNode) {
        this.weightToNode = weightToNode;
    }


    // GETTERS
    public Node getNext() {
        return next;
    }
    public Location getLoc() {
        return loc;
    }
    public Duration getWeight() {
        return weightToNode;
    }

    @NonNull
    public String toString(){

        String returnThis = "||";

        if(flight!=null)
        returnThis = returnThis + loc+flight+weightToNode;
        else
            returnThis = returnThis + loc;

        returnThis += "||";
        return returnThis;
    }
}
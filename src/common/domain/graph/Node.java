package common.domain.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public abstract class Node {
    //Attributes
    private String value;
    private int nodeID;
    private HashMap<Integer, Set<Integer>> relations;

    //Constructors

    public Node() {
        this.nodeID = -1;
        this.value = "-1";
    }

    public Node(String value) {
        this.nodeID = -1;
        this.value = value;
    }

    public Node(int nodeID, String value) {
        this.nodeID = nodeID;
        this.value = value;
    }

    public int getId() {
        return nodeID;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    protected void addRelation(int relationID, int nodeID) {
        relations.get(relationID).add(nodeID);
    }

    protected void removeRelation(int relationID, int nodeID) {
        relations.get(relationID).remove(nodeID);
    }

    public boolean isRelated(int relationID, int nodeID) {
        return relations.get(relationID).contains(nodeID);
    }

    public boolean isRelated(int relationID, Node node) {
        return relations.get(relationID).contains(node.getId());
    }

    protected ArrayList<Integer> getRelations(int relationID) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.addAll(relations.get(relationID));
        return result;
    }

    public void setID(int nodeID) {
        this.nodeID = nodeID;
    }
}



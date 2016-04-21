package common.domain;

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
    }

    public Node(int nodeID, String value) {
        this.value = value;
        this.nodeID = nodeID;
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

    public void addRelation(int relationID, int nodeID) {
        relations.get(relationID).add(nodeID);
    }

    public void removeRelation(int relationID, int nodeID) {
        relations.get(relationID).remove(nodeID);
    }

    public Boolean isRelated(int relationID, int nodeID) {
        return relations.get(relationID).contains(nodeID);
    }

    public Set<Integer> getRelations(int relationID) {
        return relations.get(relationID);
    }

    public void setID(int nodeID) {
        this.nodeID = nodeID;
    }
}



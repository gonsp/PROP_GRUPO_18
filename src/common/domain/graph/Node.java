package common.domain.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Node extends Element{
    //Attributes
    private HashMap<Integer, HashSet<Integer>> relations;

    //Constructors

    public Node() {
        super();
    }

    public Node(String value) {
        super(value);
    }

    public Node(int nodeID, String value) {
        super(nodeID, value);
    }

    protected void addEdge(int relationID, int nodeID) {
        relations.get(relationID).add(nodeID);
    }

    protected void removeEdge(int relationID, int nodeID) {
        relations.get(relationID).remove(nodeID);
    }

    public boolean isRelated(int relationID, int nodeID) {
        return relations.get(relationID).contains(nodeID);
    }

    public boolean isRelated(int relationID, Node node) {
        return relations.get(relationID).contains(node.getId());
    }

    protected ArrayList<Integer> getEdges(int relationID) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.addAll(relations.get(relationID));
        return result;
    }

    protected void addRelation(Relation relation) {
        relations.put(relation.getId(), new HashSet<Integer>());
    }

    protected void removeRelation(Relation relation) {
        relations.remove(relation.getId());
    }
}



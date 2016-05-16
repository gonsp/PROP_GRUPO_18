package common.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public abstract class Node extends Element {
    //Attributes
    private HashMap<Integer, HashSet<Integer>> relations;

    //Constructors

    protected Node(String value) {
        super(value);
        relations = new HashMap<>();
    }

    protected Node(int nodeID, String value) {
        super(nodeID, value);
        relations = new HashMap<>();
    }

    public Node(Node n) {
        super(n);
        relations = (HashMap<Integer, HashSet<Integer>>) n.relations.clone();
    }

    protected void addEdge(int relationID, int nodeID) throws GraphException {
        if (!relations.containsKey(relationID)) {
            throw new GraphException(GraphException.Error.ID_RELATION_NONEXISTENT);
        } else {
            relations.get(relationID).add(nodeID);
        }
    }

    protected void removeEdge(int relationID, int nodeID) throws GraphException {
        if (!relations.containsKey(relationID)) {
            throw new GraphException(GraphException.Error.ID_RELATION_NONEXISTENT);
        } else {
            relations.get(relationID).remove(nodeID);
        }
    }

    public boolean isRelated(int relationID, int nodeID) {
        return relations.get(relationID).contains(nodeID);
    }

    public boolean isRelated(int relationID, Node node) {
        return relations.get(relationID).contains(node.getId());
    }

    protected ArrayList<Integer> getEdges(int relationID) { //TODO sustituir esto por getIterator(). Más eficiente
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.addAll(relations.get(relationID));
        return result;
    }

    public int getSizeEdges(int relationID) {
        return relations.get(relationID).size();
    }

    protected void addRelation(Relation relation) {
        relations.put(relation.getId(), new HashSet<Integer>());
    }

    protected void removeRelation(Relation relation) {
        relations.remove(relation.getId());
    }

}



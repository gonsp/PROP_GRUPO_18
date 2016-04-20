package common.domain;

import java.util.HashMap;
import java.util.Set;


public class NodeContainer {
	//Attributes
    private int lastID;
    private HashMap<Integer, Node> nodes;

    //Constructors
    public NodeContainer() {
        lastID = 0;
    }

    //Set & Get
    public int getSize() {
        return nodes.size();
    }

    public int getLastID() {
        return lastID;
    }

    public Set<Integer> getKeySet(){
        return nodes.keySet();
    }
    
    //Container Edition
    public void addNode(Node n) {
        nodes.put(++lastID, n);
    }

    public void addNode(Node n, int ID) throws GraphException {
        if (!checkNewID(ID)) {
        	throw new GraphException(GraphExceptionError.ID_INVALID);
        } else if (nodes.containsKey(ID)) {
        	throw new GraphException(GraphExceptionError.ID_USED);
        } else {
            lastID = ID;
            addNode(n);
        }
    }

    public void removeNode(int ID) throws GraphException {
        if (!checkID(ID)) {
        	throw new GraphException(GraphExceptionError.ID_INVALID);
        } else if (nodes.containsKey(ID)) {
            nodes.remove(ID);
        } else {
        	throw new GraphException(GraphExceptionError.ID_NONEXISTENT);
        }
    }

    //Queries
    public Node getNode(int ID) throws GraphException {
        if (!checkID(ID)) {
        	throw new GraphException(GraphExceptionError.ID_INVALID);
        } else if (nodes.containsKey(ID)) {
            return nodes.get(ID);
        } else {
        	throw new GraphException(GraphExceptionError.ID_NONEXISTENT);
        }
    }

    private boolean checkID(int ID) {
    	return ID > 0;
    }
    
    private boolean checkNewID(int ID) {
    	return ID > lastID;
    }
}
 

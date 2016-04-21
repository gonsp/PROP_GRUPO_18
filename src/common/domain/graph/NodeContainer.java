package common.domain.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class NodeContainer {
	//Attributes
    private int lastID;
    private HashMap<Integer, Node> nodes;

    //Constructors
    protected NodeContainer() {
        nodes = new HashMap<Integer, Node>();
        lastID = 1;
    }

    //Set & Get
    protected int getSize() {
        return nodes.size();
    }

    protected int getLastID() {
        return lastID;
    }

    protected NodeContainerIterator getIterator() {
        return new NodeContainerIterator(this);
    }

    //Queries
    protected void addNode(Node n) {
        n.setID(lastID);
        nodes.put(lastID++, n);
    }

    protected void addNode(Node n, int ID) throws GraphException {
        if (!checkNewID(ID)) {
        	throw new GraphException(GraphExceptionError.ID_INVALID);
        } else if (nodes.containsKey(ID)) {
        	throw new GraphException(GraphExceptionError.ID_USED);
        } else {
            lastID = ID;
            addNode(n);
        }
    }

    protected void removeNode(int ID) throws GraphException {
        if (!checkID(ID)) {
        	throw new GraphException(GraphExceptionError.ID_INVALID);
        } else if (nodes.containsKey(ID)) {
            nodes.remove(ID);
        } else {
        	throw new GraphException(GraphExceptionError.ID_NONEXISTENT);
        }
    }

    protected Node getNode(int ID) throws GraphException {
        if (!checkID(ID)) {
        	throw new GraphException(GraphExceptionError.ID_INVALID);
        } else if (nodes.containsKey(ID)) {
            return nodes.get(ID);
        } else {
        	throw new GraphException(GraphExceptionError.ID_NONEXISTENT);
        }
    }

    private boolean checkID(int ID) {
    	return ID > 0 && ID < lastID;
    }
    
    private boolean checkNewID(int ID) {
    	return ID >= lastID;
    }

    public class NodeContainerIterator implements Iterator<Map.Entry<Integer, Node>> {

        private Iterator<Map.Entry<Integer, Node>> iterator;

        private NodeContainerIterator(NodeContainer nodeContainer) {
            iterator = nodeContainer.nodes.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Map.Entry<Integer, Node> next() {
            return iterator.next();
        }
    }

}
 

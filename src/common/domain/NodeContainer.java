package common.domain;

import java.util.HashMap;


public class NodeContainer {

    private int lastID;
    private HashMap<Integer, Node> nodes;

    public NodeContainer() {
        lastID = 1;
    }

    public void setLastID(int ID) throws GraphException {
        if (!checkID(ID)) {
            throw new GraphException(GraphExceptionError.ID_INVALID);
        } else {
            lastID = ID;
        }
    }

    public int getSize() {
        return nodes.size();
    }

    public int getLastID() {
        return lastID;
    }

    public void addNode(Node n) {
        nodes.put(++lastID, n);
    }

    public void addNode(Node n, int ID) throws GraphException {
        if (!checkID(ID)) {
            GraphException e = new GraphException(ID_INVALID);
            throw e;
        } else if (nodes.find(ID)) { //TODO fix this to work with hashmap
            GraphException e = new GraphException(ID_USED);
            throw e;
        } else {
            lastID = ID;
            addNode(n);
        }
    }

    public void removeNode(int ID) throws GraphException {
        if (!checkID(ID)) {
            GraphException e = new GraphException(ID_INVALID);
            throw e;
        } else if (nodes.find(ID)) {
            nodes.remove(ID);
            //TODO si no existia:
            //GraphException e = new GraphException(ID_NOT_EXISTS);
            //throw e;
        }
    }

    public Node getNode(int ID) {
        if (!checkID(ID)) {
            GraphException e = new GraphException(ID_INVALID);
            throw e;
        } else if (nodes.find(ID)) {
            return nodo; //TODO implementar bien todo esto
            //TODO si no existia:
            //GraphException e = new GraphException(ID_NOT_EXISTS);
            //throw e;
        }
    }

    private boolean checkID(int ID) {
        return ID >= 0 && ID > lastID;
    }
}
 

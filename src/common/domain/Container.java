package common.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Container<T extends  Element> {
    //Attributes
    private int lastID;
    private HashMap<Integer, T> elements;

    //Constructors
    protected Container() {
        elements = new HashMap<Integer, T>();
        lastID = 0;
    }

    //Set & Get
    protected int getSize() {
        return elements.size();
    }

    protected int getLastID() {
        return lastID;
    }

    protected ContainerIterator getIterator() {
        return new ContainerIterator(this);
    }

    //Queries
    protected void addElement(T element) {
        element.setId(lastID);
        elements.put(lastID++, element);
    }

    protected void addElement(T element, int ID) throws GraphException {
        if(ID < 0) {
            throw new GraphException(GraphException.Error.ID_INVALID);
        } else if(elements.containsKey(ID)) {
            throw new GraphException(GraphException.Error.ID_USED);
        } else {
            if(ID >= lastID) {
                lastID = ID;
                addElement(element);
            } else {
                element.setId(ID);
                elements.put(ID, element);
            }
        }
    }

    protected void removeElement(int ID) throws GraphException {
        if(!checkID(ID)) {
            throw new GraphException(GraphException.Error.ID_INVALID);
        } else if (elements.containsKey(ID)) {
            elements.remove(ID);
        } else {
            throw new GraphException(GraphException.Error.ID_NONEXISTENT);
        }
    }

    protected T getElement(int ID) throws GraphException {
        if (!checkID(ID)) {
            throw new GraphException(GraphException.Error.ID_INVALID);
        } else if (elements.containsKey(ID)) {
            return elements.get(ID);
        } else {
            throw new GraphException(GraphException.Error.ID_NONEXISTENT);
        }
    }

    protected ArrayList<Integer> getIdFromValue(String value) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ContainerIterator iterator = getIterator();
        while(iterator.hasNext()) {
            Element aux = iterator.next();
            if(aux.getValue() != null && aux.getValue().equalsIgnoreCase(value)) {
                ids.add(aux.getId());
            }
        }
        return ids;
    }

    private boolean checkID(int ID) {
        return ID >= 0 && ID < lastID;
    }

    public class ContainerIterator implements Iterator {

        private Iterator<Map.Entry<Integer, T>> iterator;

        private ContainerIterator(Container<T> container) {
            iterator = container.elements.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return iterator.next().getValue();
        }
    }
}


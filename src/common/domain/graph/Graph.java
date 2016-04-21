package common.domain.graph;


import java.util.HashMap;

public class Graph {
    //Attributes
    private String name;
    private HashMap<NodeType, NodeContainer> containters;

    //Constructors
    public Graph() {
        this("-1");
    }

    public Graph(String name) {
        this.name = name;
        containters = new HashMap<NodeType, NodeContainer>();
        containters.put(NodeType.AUTHOR, new NodeContainer());
        containters.put(NodeType.PAPER, new NodeContainer());
        containters.put(NodeType.LABEL, new NodeContainer());
        containters.put(NodeType.CONFERENCE, new NodeContainer());
        containters.put(NodeType.TERM, new NodeContainer());
    }

    //Get & Set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize(NodeType type) throws GraphException {
        return getNodeContainer(type).getSize();
    }

    //Graph Edition
    public void addNode(Node node) throws GraphException {
        getNodeContainer(getNodeType(node)).addNode(node);
    }

    public void addNode(Node node, int id) throws GraphException {
        getNodeContainer(getNodeType(node)).addNode(node, id);
    }

    public void removeNode(NodeType type, int id) throws GraphException {
        getNodeContainer(type).removeNode(id);
    }

    public Node getNode(NodeType type, int id) throws GraphException {
        return getNodeContainer(type).getNode(id);
    }

    public void addRelation(int idRelation, Node a, Node b) {
        a.addRelation(idRelation, b.getId());
        b.addRelation(idRelation, a.getId());
    }

    public void addRelation(int idRelation, NodeType typeA, int idA, NodeType typeB, int idB) throws GraphException {
        if(typeA == typeB && idA == idB) {
            throw new GraphException(GraphExceptionError.ID_EQUAL);
        }
        addRelation(idRelation, getNodeContainer(typeA).getNode(idA), getNodeContainer(typeB).getNode(idB));
    }

    public void removeRelation(int idRelation, Node a, Node b) {
        a.removeRelation(idRelation, b.getId());
        b.removeRelation(idRelation, a.getId());
    }

    public void removeRelation(int idRelation, NodeType typeA, int idA, NodeType typeB, int idB) throws GraphException {
        removeRelation(idRelation, getNodeContainer(typeA).getNode(idA), getNodeContainer(typeB).getNode(idB));
    }

    public NodeContainer.NodeContainerIterator getIterator(NodeType type) throws GraphException {
        return getNodeContainer(type).getIterator();
    }
    
    private NodeType getNodeType(Node node) {
        if (node instanceof Author) {
            return NodeType.AUTHOR;
        } else if (node instanceof Paper) {
            return NodeType.PAPER;
        } else if (node instanceof Term) {
            return NodeType.TERM;
        } else if (node instanceof Label) {
            return NodeType.LABEL;
        } else {
            return NodeType.CONFERENCE;
        }
    }

    private NodeContainer getNodeContainer(NodeType type) {
        return containters.get(type);
    }
}
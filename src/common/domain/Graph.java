package common.domain;

import java.util.Set;

public class Graph {
    //Attributes
    private String name;
    private NodeContainer authors;
    private NodeContainer papers;
    private NodeContainer terms;
    private NodeContainer labels;
    private NodeContainer conferences;

    //Constructors
    public Graph() {
        authors = new NodeContainer();
        papers = new NodeContainer();
        terms = new NodeContainer();
        labels = new NodeContainer();
        conferences = new NodeContainer();
    }

    public Graph(String name) {
        this.name = name;
        authors = new NodeContainer();
        papers = new NodeContainer();
        terms = new NodeContainer();
        labels = new NodeContainer();
        conferences = new NodeContainer();
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
    
    public void addRelation() {
    	//TODO dfvfdddfvvdddfveqrrthrhrvdfbdfbsdfbsdfbdfb
    }

    //Queries
    public Node getNode(NodeType type, int id) throws GraphException {
        return getNodeContainer(type).getNode(id);
    }
    
    
    
    //???
    public Set<Integer> getTypeKeySet(NodeType type) throws GraphException {
        return getNodeContainer(type).getKeySet();
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

    private NodeContainer getNodeContainer(NodeType type) throws GraphException {
        switch (type) {
            case AUTHOR:
                return authors;
            case PAPER:
                return papers;
            case TERM:
                return terms;
            case LABEL:
                return labels;
            case CONFERENCE:
                return conferences;
            default:
                throw new GraphException(GraphExceptionError.TYPE_INVALID);
        }
    }
}
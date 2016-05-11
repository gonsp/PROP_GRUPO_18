package common.domain;


import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    //Attributes
    private String name;
    private HashMap<NodeType, Container<Node>> nodeContainers;
    private Container<Relation> relations;

    //Constructors
    public Graph() {
        nodeContainers = new HashMap<NodeType, Container<Node>>();
        nodeContainers.put(NodeType.AUTHOR, new Container<Node>());
        nodeContainers.put(NodeType.PAPER, new Container<Node>());
        nodeContainers.put(NodeType.LABEL, new Container<Node>());
        nodeContainers.put(NodeType.CONF, new Container<Node>());
        nodeContainers.put(NodeType.TERM, new Container<Node>());
        relations = new Container<Relation>();
        addRelation(new AuthorPaper());
        addRelation(new ConferencePaper());
        addRelation(new TermPaper());
        addRelation(new AuthorLabel());
        addRelation(new PaperLabel());
        addRelation(new ConferenceLabel());
    }

    //Get & Set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize(NodeType type) {
        return getNodeContainer(type).getSize();
    }

    //Graph Edition
    public void addNode(Node node) {
        getNodeContainer(getNodeType(node)).addElement(node);
    }

    public void addNode(Node node, int id) throws GraphException {
        getNodeContainer(getNodeType(node)).addElement(node, id);
    }

    public Node createNode(NodeType type, String value) {
        Node node;
        if(type == NodeType.AUTHOR) {
            node = new Author(value);
        } else if(type == NodeType.CONF) {
            node = new Conference(value);
        } else if(type == NodeType.PAPER) {
            node = new Paper(value);
        } else if(type == NodeType.TERM) {
            node = new Term(value);
        } else {
            node = new Label(value);
        }
        Container<Relation>.ContainerIterator iterator = relations.getIterator();
        while(iterator.hasNext()) {
            Relation relation = iterator.next();
            if(relation.getNodeTypeA() == type || relation.getNodeTypeB() == type) {
                node.addRelation(relation);
            }
        }
        return node;
    }

    public void removeNode(NodeType type, int id) throws GraphException {
        Node node = getNode(type, id);
        Container<Relation>.ContainerIterator iterator = relations.getIterator();
        while(iterator.hasNext()) {
            Relation relation = iterator.next();
            ArrayList<Node> connected = getEdges(relation.getId(), node);
            for(int i = 0; i < connected.size(); ++i) {
                removeEdge(relation.getId(), node, connected.get(i));
            }
        }
        getNodeContainer(type).removeElement(id);
    }

    public Node getNode(NodeType type, int id) throws GraphException {
        return getNodeContainer(type).getElement(id);
    }

    public ArrayList<Node> getEdges(int relationID, Node node) throws GraphException {
        Relation relation = getRelation(relationID);
        NodeType typeDest = relation.getNodeTypeA();
        if(typeDest == getNodeType(node)) {
            typeDest = relation.getNodeTypeB();
        }
        ArrayList<Integer> relations = node.getEdges(relationID);
        ArrayList<Node> result = new ArrayList<Node>();
        for(int i = 0; i < relations.size(); ++i) {
            result.add(getNode(typeDest, relations.get(i)));
        }
        return result;
    }

    public ArrayList<Node> getEdges(int relationID, NodeType type, int id) throws GraphException {
        return getEdges(relationID, getNode(type, id));
    }

    public void addEdge(int relationID, Node a, Node b) throws GraphException {
        a.addEdge(relationID, b.getId());
        b.addEdge(relationID, a.getId());
    }

    public void addEdge(int relationID, NodeType typeA, int idA, NodeType typeB, int idB) throws GraphException {
        if(typeA == typeB && idA == idB) {
            throw new GraphException(GraphException.Error.ID_EQUAL);
        }
        addEdge(relationID, getNodeContainer(typeA).getElement(idA), getNodeContainer(typeB).getElement(idB));
    }

    public void removeEdge(int relationID, Node a, Node b) throws GraphException {
        a.removeEdge(relationID, b.getId());
        b.removeEdge(relationID, a.getId());
    }

    public void removeEdge(int relationID, NodeType typeA, int idA, NodeType typeB, int idB) throws GraphException {
        removeEdge(relationID, getNodeContainer(typeA).getElement(idA), getNodeContainer(typeB).getElement(idB));
    }

    public Container<Node>.ContainerIterator getNodeIterator(NodeType type) {
        return getNodeContainer(type).getIterator();
    }

    public Container<Relation>.ContainerIterator getRelationIterator() {
        return relations.getIterator();
    }

    private void i_addRelation(Relation relation, NodeType type) {
        Container<Node>.ContainerIterator iterator = getNodeIterator(type);
        while(iterator.hasNext()) {
            Node node = iterator.next();
            node.addRelation(relation);
        }
    }

    public void addRelation(Relation relation) {
        relations.addElement(relation);
        i_addRelation(relation, relation.getNodeTypeA());
        if(relation.getNodeTypeA() != relation.getNodeTypeB()) {
            i_addRelation(relation, relation.getNodeTypeB());
        }
    }

    private void i_removeRelation(Relation relation, NodeType type) throws GraphException {
        if(!relation.isDefault()) {
            Container<Node>.ContainerIterator iterator = getNodeIterator(type);
            while(iterator.hasNext()) {
                Node node = iterator.next();
                node.removeRelation(relation);
            }
        } else {
            throw new GraphException(GraphException.Error.RELATION_DEFAULT);
        }
    }

    public void removeRelation(int relationID) throws GraphException {
        Relation relation = relations.getElement(relationID);
        i_removeRelation(relation, relation.getNodeTypeA());
        if(relation.getNodeTypeA() != relation.getNodeTypeB()) {
            i_removeRelation(relation, relation.getNodeTypeB());
        }
        relations.removeElement(relationID);
    }

    public Relation getRelation(int relationID) throws GraphException {
        return relations.getElement(relationID);
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
            return NodeType.CONF;
        }
    }

    private Container<Node> getNodeContainer(NodeType type) {
        return nodeContainers.get(type);
    }
}

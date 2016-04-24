package common.domain;


import java.util.ArrayList;
import java.util.Iterator;

public class OriginSearch extends RelationalSearch {

    private Node from;

    public OriginSearch(Graph graph, RelationStructure rs, Node from) {
        super(graph, rs);
        this.from = from;
    }

    @Override
    protected Iterator getIteratorFirstMatrix() {
        ArrayList<Node> aux = new ArrayList<Node>();
        aux.add(from);
        return aux.iterator();
    }

    @Override
    protected ArrayList<Node> getEdgesNodeFrom(Relation edgeRelation, Node nodeB) throws GraphException {
        ArrayList<Node> aux = new ArrayList<Node>();
        if(nodeB.isRelated(edgeRelation.getId(), from)) {
            aux.add(from);
        }
        return aux;
    }
}

package common.domain;

import java.util.ArrayList;
import java.util.Iterator;

public class OriginDestinationSearch extends RelationalSearch {

    private Node from;
    private Node to;

    public OriginDestinationSearch(Graph graph, RelationStructure rs, Node from, Node to) {
        super(graph, rs);
        this.from = from;
        this.to = to;
    }

    @Override
    protected Iterator getIteratorFirstMatrix() {
        return null;//TODO implementar
    }

    @Override
    protected ArrayList<Node> getEdgesNodeFrom(Relation edgeRelation, Node nodeB) throws GraphException {
        return null;//TODO implementar
    }
}

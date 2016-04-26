package common.domain;


import java.util.ArrayList;
import java.util.Iterator;

public class OriginSearch extends RelationalSearch {

    protected Node from;

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
    protected Matrix createFirstMatrix(Relation relation) {
        return new Matrix(1, graph.getSize(relation.getNodeTypeB()));
    }

    @Override
    protected Matrix createFirstEdgeMatrix(Relation edgeRelation) {
        return new Matrix(1, getColumnsEdgeMatrix(edgeRelation));
    }
}

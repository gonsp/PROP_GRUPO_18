package common.domain;

import java.util.ArrayList;
import java.util.Iterator;

public class FreeSearch extends RelationalSearch {

    public FreeSearch(Graph graph, RelationStructure rs) {
        super(graph, rs);
    }

    @Override
    protected Iterator getIteratorFirstMatrix() {
        return graph.getNodeIterator(rs.get(0).getNodeTypeA());
    }

    @Override
    protected Matrix createFirstMatrix(Relation relation) {
        return new Matrix(graph.getSize(relation.getNodeTypeA()), graph.getSize(relation.getNodeTypeB()));
    }

    @Override
    protected Matrix createFirstEdgeMatrix(Relation edgeRelation) {
        return new Matrix(graph.getSize(edgeRelation.getNodeTypeA()), getColumnsEdgeMatrix(edgeRelation));
    }

}

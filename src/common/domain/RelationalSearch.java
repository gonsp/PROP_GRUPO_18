package common.domain;

import org.jblas.DoubleMatrix;

import java.util.ArrayList;
import java.util.HashMap;

public class RelationalSearch {

    private Graph graph;
    private RelationStructure rs;
    private ArrayList<Result> results;
    private HashMap<String, Integer> edgesID;

    public RelationalSearch(Graph graph, RelationStructure rs) {
        this.graph = graph;
        this.rs = rs;
        edgesID = new HashMap<String, Integer>();
    }

    private DoubleMatrix i_hetesim(int i, int j, boolean normalize_rows) {
        if(i >= j) {
            return getNormalizedMatrix(rs.get(i), normalize_rows);
        } else {
            int m = (j-i+1)/2;
            return i_hetesim(0, m-1, normalize_rows).mmul(i_hetesim(m, rs.size()-1, normalize_rows));
        }
    }

    private DoubleMatrix hetesim() {
        int m = rs.size()/2;
        if(rs.size()%2 == 0) {
            return i_hetesim(0, m-1, true).mmul(i_hetesim(m, rs.size()-1, false));
        } else {
            return i_hetesim(0, m-1, true).mmul(getNormalizedEdgeLeftMatrix(rs.get(m))).mmul(getNormalizedEdgeRightMatrix(rs.get(m))).mmul(i_hetesim(m, rs.size()-1, false));
        }
    }

    public void search() {
        DoubleMatrix res = hetesim();
    }

    private DoubleMatrix getNormalizedMatrix(Relation relation, boolean normalize_rows) {
        DoubleMatrix matrix = DoubleMatrix.zeros(graph.getSize(relation.getNodeTypeA()), graph.getSize(relation.getNodeTypeB()));
        Container<Node>.ContainerIterator iteratorA = graph.getNodeIterator(relation.getNodeTypeA());
        Container<Node>.ContainerIterator iteratorB;
        for(int i = 0; i < matrix.getRows(); ++i) {
            Node a = iteratorA.next().getValue();
            iteratorB = graph.getNodeIterator(relation.getNodeTypeB());
            for(int j = 0; j < matrix.getColumns(); ++j) {
                Node b = iteratorB.next().getValue();
                if(a.isRelated(relation.getId(), b)) {
                    double n;
                    if(normalize_rows) {
                        n = a.getSizeEdges(relation.getId());
                    } else {
                        n = b.getSizeEdges(relation.getId());
                    }
                    matrix.put(i, j, 1./n);
                }
            }
        }
        return matrix;
    }

    private DoubleMatrix getNormalizedEdgeLeftMatrix(Relation edgeRelation) {
        DoubleMatrix edgeMatrixA = DoubleMatrix.zeros(graph.getSize(edgeRelation.getNodeTypeA()), getColumnsEdgeMatrix(edgeRelation));
        int i = 0;
        int j = 0;
        Container<Node>.ContainerIterator iteratorA = graph.getNodeIterator(edgeRelation.getNodeTypeA());
        while(iteratorA.hasNext()) {
            Node nodeA = iteratorA.next().getValue();
            Container<Node>.ContainerIterator iteratorB = graph.getNodeIterator(edgeRelation.getNodeTypeB()); //TODO MAAAAAAAL
            for(int k = 0; k < nodeA.getSizeEdges(edgeRelation.getId()); ++k) {
                Node nodeB = iteratorB.next().getValue();
                edgesID.put(String.valueOf(nodeA.getId()).concat(String.valueOf(nodeB.getId())), j);
                edgeMatrixA.put(i, j, 1./nodeA.getSizeEdges(edgeRelation.getId()));
                ++j;
            }
            ++i;
        }
        return edgeMatrixA;
    }

    private DoubleMatrix getNormalizedEdgeRightMatrix(Relation edgeRelation) {
        DoubleMatrix edgeMatrixB = DoubleMatrix.zeros(edgesID.size(), graph.getSize(edgeRelation.getNodeTypeB()));
        Container<Node>.ContainerIterator iteratorB = graph.getNodeIterator(edgeRelation.getNodeTypeB());
        //TODO ACABAAAAAAR
        return edgeMatrixB;
    }

    private int getColumnsEdgeMatrix(Relation edgeRelation) {
        int columns = 0;
        Container<Node>.ContainerIterator iterator = graph.getNodeIterator(edgeRelation.getNodeTypeA());
        while(iterator.hasNext()) {
            Node node = iterator.next().getValue();
            columns += node.getSizeEdges(edgeRelation.getId());
        }
        return columns;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public class Result {
        public Node from;
        public Node to;
        public double hetesim;

        Result(Node from, Node to, double hetesim) {
            this.from = from;
            this.to = to;
            this.hetesim = hetesim;
        }
    }
}

package common.domain;

import org.jblas.DoubleMatrix;

import java.util.ArrayList;

public class RelationalSearch {

    private Graph graph;
    private RelationStructure rs;
    private ArrayList<Result> results;

    public RelationalSearch(Graph graph, RelationStructure rs) {
        this.graph = graph;
        this.rs = rs;
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
                    matrix.put(i, j, 1./a.getSizeEdges(relation.getId()));
                }
            }
        }
        return matrix;
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
            return null; //TODO
        }
    }

    public void search() {
        DoubleMatrix res = hetesim();
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

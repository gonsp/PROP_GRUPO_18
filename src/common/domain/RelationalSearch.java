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
        results = new ArrayList<Result>();
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
            if(rs.size() == 1) {
                DoubleMatrix result = getNormalizedEdgeLeftMatrix(rs.get(m)).mmul(getNormalizedEdgeRightMatrix(rs.get(m)));
                return result;
            } else {
                return i_hetesim(0, m-1, true).mmul(getNormalizedEdgeLeftMatrix(rs.get(m))).mmul(getNormalizedEdgeRightMatrix(rs.get(m))).mmul(i_hetesim(m, rs.size()-1, false));
            }
        }
    }

    public void search() {
        DoubleMatrix matrix = hetesim();
        //TODO normalize hetesim
        Container<Node>.ContainerIterator iteratorA = graph.getNodeIterator(rs.get(0).getNodeTypeA());
        for(int i = 0; i < matrix.getRows(); ++i) {
            Node nodeA = iteratorA.next().getValue();
            Container<Node>.ContainerIterator iteratorB = graph.getNodeIterator(rs.get(rs.size()-1).getNodeTypeB());
            for(int j = 0; j < matrix.getColumns(); ++j) {
                Node nodeB = iteratorB.next().getValue();
                if(matrix.get(i, j) > 0) {
                    results.add(new Result(nodeA, nodeB, matrix.get(i, j)));
                }
            }
        }
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
        try {
            int i = 0;
            int j = 0;
            Container<Node>.ContainerIterator iteratorA = graph.getNodeIterator(edgeRelation.getNodeTypeA());
            while(iteratorA.hasNext()) {
                Node nodeA = iteratorA.next().getValue();
                ArrayList<Node> connected = graph.getEdges(edgeRelation.getId(), nodeA);
                for(int iteratorB = 0; iteratorB < nodeA.getSizeEdges(edgeRelation.getId()); ++iteratorB) {
                    Node nodeB = connected.get(iteratorB);
                    edgesID.put(String.valueOf(nodeA.getId()).concat(String.valueOf(nodeB.getId())), j);
                    edgeMatrixA.put(i, j, 1./nodeA.getSizeEdges(edgeRelation.getId()));
                    ++j;
                }
                ++i;
            }
        } catch (GraphException e) {
            e.printStackTrace();
        }
        return edgeMatrixA;
    }

    private DoubleMatrix getNormalizedEdgeRightMatrix(Relation edgeRelation) {
        DoubleMatrix edgeMatrixB = DoubleMatrix.zeros(edgesID.size(), graph.getSize(edgeRelation.getNodeTypeB()));
        try {
            Container<Node>.ContainerIterator iteratorB = graph.getNodeIterator(edgeRelation.getNodeTypeB());
            int j = 0;
            while(iteratorB.hasNext()) {
                Node nodeB = iteratorB.next().getValue();
                ArrayList<Node> connected = graph.getEdges(edgeRelation.getId(), nodeB);
                for(int iteratorA = 0; iteratorA < nodeB.getSizeEdges(edgeRelation.getId()); ++iteratorA) {
                    Node nodeA = connected.get(iteratorA);
                    edgeMatrixB.put(edgesID.get(String.valueOf(nodeA.getId()).concat(String.valueOf(nodeB.getId()))), j, 1./nodeB.getSizeEdges(edgeRelation.getId()));
                }
                ++j;
            }
        } catch(GraphException e) {
            e.printStackTrace();
        }
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

        public void print() {
            System.out.println(from.getValue() + " - " + to.getValue() + " - " + String.valueOf(hetesim));
        }
    }
}

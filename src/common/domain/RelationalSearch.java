package common.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class RelationalSearch extends GraphSearch {

    private RelationStructure rs;
    private HashMap<String, Integer> edgesID;

    public RelationalSearch(Graph graph, RelationStructure rs) {
        super(graph);
        this.rs = rs;
        edgesID = new HashMap<String, Integer>();
    }

    private Matrix i_hetesim(int i, int j, boolean normalize_rows) {
        if(i >= j) {
            return getNormalizedMatrix(rs.get(i), normalize_rows);
        } else {
            int m = (j-i+1)/2;
            return i_hetesim(0, m-1, normalize_rows).mul(i_hetesim(m, rs.size()-1, normalize_rows));
        }
    }

    private Matrix hetesim() {
        int m = rs.size()/2;
        if(rs.size()%2 == 0) {
            return i_hetesim(0, m-1, true).mul(i_hetesim(m, rs.size()-1, false));
        } else {
            if(rs.size() == 1) {
                return getNormalizedEdgeLeftMatrix(rs.get(m)).mul(getNormalizedEdgeRightMatrix(rs.get(m)));
            } else {
                return i_hetesim(0, m-1, true).mul(getNormalizedEdgeLeftMatrix(rs.get(m))).mul(getNormalizedEdgeRightMatrix(rs.get(m))).mul(i_hetesim(m, rs.size()-1, false));
            }
        }
    }

    public void search() {
        if(!executed) {
            executed = true;
            Matrix matrix = hetesim();
            //TODO normalize hetesim
            //TODO order results
            //TODO implement subclasses
            Container<Node>.ContainerIterator iteratorA = graph.getNodeIterator(rs.get(0).getNodeTypeA());
            for(int i = 0; i < matrix.getRows(); ++i) {
                Node nodeA = iteratorA.next();
                Container<Node>.ContainerIterator iteratorB = graph.getNodeIterator(rs.get(rs.size()-1).getNodeTypeB());
                for(int j = 0; j < matrix.getColumns(); ++j) {
                    Node nodeB = iteratorB.next();
                    if(matrix.get(i, j) > 0) {
                        results.add(new Result(nodeA, nodeB, matrix.get(i, j)));
                    }
                }
            }
        }
    }

    private Matrix getNormalizedMatrix(Relation relation, boolean normalize_rows) {
        Matrix matrix = new Matrix(graph.getSize(relation.getNodeTypeA()), graph.getSize(relation.getNodeTypeB()));
        Container<Node>.ContainerIterator iteratorA = graph.getNodeIterator(relation.getNodeTypeA());
        Container<Node>.ContainerIterator iteratorB;
        for(int i = 0; i < matrix.getRows(); ++i) {
            Node a = iteratorA.next();
            iteratorB = graph.getNodeIterator(relation.getNodeTypeB());
            for(int j = 0; j < matrix.getColumns(); ++j) {
                Node b = iteratorB.next();
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

    private Matrix getNormalizedEdgeLeftMatrix(Relation edgeRelation) {
        Matrix edgeMatrixA = new Matrix(graph.getSize(edgeRelation.getNodeTypeA()), getColumnsEdgeMatrix(edgeRelation));
        try {
            int i = 0;
            int j = 0;
            Container<Node>.ContainerIterator iteratorA = graph.getNodeIterator(edgeRelation.getNodeTypeA());
            while(iteratorA.hasNext()) {
                Node nodeA = iteratorA.next();
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

    private Matrix getNormalizedEdgeRightMatrix(Relation edgeRelation) {
        Matrix edgeMatrixB = new Matrix(edgesID.size(), graph.getSize(edgeRelation.getNodeTypeB()));
        try {
            Container<Node>.ContainerIterator iteratorB = graph.getNodeIterator(edgeRelation.getNodeTypeB());
            int j = 0;
            while(iteratorB.hasNext()) {
                Node nodeB = iteratorB.next();
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
            Node node = iterator.next();
            columns += node.getSizeEdges(edgeRelation.getId());
        }
        return columns;
    }
}

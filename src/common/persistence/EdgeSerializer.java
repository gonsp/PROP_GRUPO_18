package common.persistence;

import common.domain.Graph;
import common.domain.GraphException;
import common.domain.Node;
import common.domain.NodeType;

import java.io.Serializable;


public class EdgeSerializer implements Serializable {

    protected String data;
    protected Graph graph;
    protected NodeType ntype1;
    protected NodeType ntype2;
    protected Node node1;
    protected Node node2;

    protected void inflate() {
        if (graph != null) {
            int m = data.indexOf("\t");
            if (m == -1) {
                m = data.indexOf(" ");
            }
            int id1 = Integer.parseInt(data.substring(0, m));
            int id2 = Integer.parseInt(data.substring(m + 1, data.length()));
            try {
                node1 = graph.getNode(ntype1, id1);
                node2 = graph.getNode(ntype2, id2);
            } catch (GraphException e) {
                e.printStackTrace();
            }
        }
    }

    protected void deflate() {
        if (data == null) {
            int node1Id = node1.getId();
            int node2Id = node2.getId();
            data = Integer.toString(node1Id) + "\t" + Integer.toString(node2Id);
        }
    }

    public EdgeSerializer(Graph graph, String data, NodeType ntype1, NodeType ntype2) {
        this.graph = graph;
        this.ntype1 = ntype1;
        this.ntype2 = ntype2;
        this.data = data;
    }

    public EdgeSerializer(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() {
        this.inflate();
        return node1;
    }

    public Node getNode2() {
        this.inflate();
        return node2;
    }

    public String getData() {
        this.deflate();
        return data;
    }

}
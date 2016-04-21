package common.persistence;

import common.domain.graph.Graph;
import common.domain.graph.GraphException;
import common.domain.graph.Node;
import common.domain.graph.NodeType;

import java.io.Serializable;


public class EdgeSerializer implements Serializable {

    protected String data = null;
    protected Graph graph;
    protected NodeType ntype1;
    protected NodeType ntype2;
    protected Node node1;
    protected Node node2;
    protected String etype;

    protected void inflate() throws GraphException {
        if (graph != null && data != null) {
            int m = data.indexOf(" ");
            int id1 = Integer.parseInt(data.substring(0, m - 1));
            int id2 = Integer.parseInt(data.substring(m + 1, data.length() - 1));
            node1 = graph.getNode(ntype1, id1);
            node2 = graph.getNode(ntype2, id2);
        }
    }

    protected void deflate() {

    }

    public EdgeSerializer(Graph graph, String data, String etype, NodeType ntype1, NodeType ntype2) {
        this.graph = graph;
        this.etype = etype;
        this.ntype1 = ntype1;
        this.ntype2 = ntype2;
        this.data = data;
    }

    public EdgeSerializer(Node node1, Node node2, String etype) {
        this.etype = etype;
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() throws GraphException {
        this.inflate();
        return node1;
    }

    public Node getNode2() throws GraphException {
        this.inflate();
        return node2;
    }

    public String getType() {
        return etype;
    }

}
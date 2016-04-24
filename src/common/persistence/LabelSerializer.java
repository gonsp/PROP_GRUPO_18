package common.persistence;

import common.domain.Graph;
import common.domain.GraphException;
import common.domain.NodeType;
import common.domain.Node;

public class LabelSerializer extends EdgeSerializer {

    public LabelSerializer(Graph graph, String data, NodeType ntype1, NodeType ntype2) {
        super(graph, data, ntype1, ntype2);
    }

    public LabelSerializer(Node node1, Node node2){
        super(node1, node2);
    }

    @Override
    protected void inflate() {
        if (graph != null) {
            int m = data.indexOf("\t");
            if (m == -1) {
                m = data.indexOf(" ");
            }
            int id1 = Integer.parseInt(data.substring(0, m));
            int id2 = Integer.parseInt(data.substring(m + 1, m + 2));
            try {
                node1 = graph.getNode(ntype1, id1);
                node2 = graph.getNode(ntype2, id2);
            } catch (GraphException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void deflate() {
        if (data == null) {
            int node1Id = node1.getId();
            int node2Id = node2.getId();
            String node1Val = node1.getValue();
            data = Integer.toString(node1Id) + "\t" + Integer.toString(node2Id) + "\t" + node1Val;
        }
    }

}

package common.persistence;

import common.domain.Graph;
import common.domain.GraphException;
import common.domain.NodeType;

public class LabelSerializer extends EdgeSerializer {

    public LabelSerializer(Graph graph, String data, NodeType ntype1, NodeType ntype2) {
        super(graph, data, ntype1, ntype2);
    }

    @Override
    protected void inflate() throws GraphException {
        if (graph != null && data != null) {
            int m = data.indexOf("\t");
            if (m == -1) {
                m = data.indexOf(" ");
            }
            int id1 = Integer.parseInt(data.substring(0, m));
            int id2 = Integer.parseInt(data.substring(m + 1, m + 2));
            node1 = graph.getNode(ntype1, id1);
            node2 = graph.getNode(ntype2, id2);
        }
    }

}

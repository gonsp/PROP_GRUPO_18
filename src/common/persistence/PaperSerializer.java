package common.persistence;


import common.domain.graph.Node;
import common.domain.graph.Paper;

public class PaperSerializer extends NodeSerializer {

    public PaperSerializer(Node node) {
        super(node);
    }

    public PaperSerializer(String data) {
        super(data);
    }

    @Override
    public Paper getNode() {
        this.inflate();
        node = new Paper(nodeId, name);
        return ((Paper)node);
    }
}

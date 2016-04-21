package common.persistence;


import common.domain.graph.Node;
import common.domain.graph.Term;

public class TermSerializer extends NodeSerializer {

    public TermSerializer(Node node) {
        super(node);
    }

    public TermSerializer(String data) {
        super(data);
    }

    @Override
    public Term getNode() {
        this.inflate();
        node = new Term(nodeId,name);
        return ((Term)node);
    }
}

package common.persistence;


import common.domain.Node;

public class TermSerializer extends NodeSerializer {

    public TermSerializer(Node node) {
        super(node);
    }

    @Override
    public Node getNode() {
        return null;
    }
}

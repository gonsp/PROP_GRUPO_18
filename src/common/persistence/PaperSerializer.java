package common.persistence;


import common.domain.Node;

public class PaperSerializer extends NodeSerializer {

    public PaperSerializer(Node node) {
        super(node);
    }

    @Override
    public Node getNode() {
        return null;
    }
}

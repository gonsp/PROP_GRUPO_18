package common.persistence;


import common.domain.Node;

public class ConferenceSerializer extends NodeSerializer {

    public ConferenceSerializer(Node node) {
        super(node);
    }

    @Override
    public Node getNode() {
        return null;
    }
}

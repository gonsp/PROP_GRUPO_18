package common.persistence;


import common.domain.Node;
import common.domain.Paper;

public class PaperSerializer extends NodeSerializer {

    public PaperSerializer(Node node) {
        super(node);
    }

    public PaperSerializer(String data) {
        super(data);
    }

}

package common.domain;

public class ConferencePaper extends Relation {
    protected ConferencePaper() {
        super(NodeType.CONFERENCE, NodeType.PAPER, "ConferencePaper", 2);
        isDefault = true;
    }
}
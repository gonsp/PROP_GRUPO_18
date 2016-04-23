package common.domain;

public class PaperLabel extends Relation {
    protected PaperLabel() {
        super(NodeType.PAPER, NodeType.LABEL, "PaperLabel", 5);
        isDefault = true;
    }
}
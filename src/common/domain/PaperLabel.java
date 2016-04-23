package common.domain;

public class PaperLabel extends Relation {
    protected PaperLabel() {
        super(NodeType.PAPER, NodeType.LABEL, "PaperLabel", 4);
        isDefault = true;
    }
}
package common.domain;

public class PaperLabel extends Relation {
    protected PaperLabel() {
        super(NodeType.PAPER, NodeType.LABEL, "paper_label", 4);
        isDefault = true;
    }
}
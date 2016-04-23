package common.domain;

public class TermPaper extends Relation {
    protected TermPaper() {
        super(NodeType.TERM, NodeType.PAPER, "TermPaper", 3);
        isDefault = true;
    }
}
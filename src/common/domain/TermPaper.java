package common.domain;

public class TermPaper extends Relation {
    protected TermPaper() {
        super(NodeType.TERM, NodeType.PAPER, "TermPaper", 2);
        isDefault = true;
    }
}
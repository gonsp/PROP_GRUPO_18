package common.domain;

public class PaperTerm extends Relation {
    protected PaperTerm() {
        super(NodeType.PAPER, NodeType.TERM, "paper_term", 2);
        isDefault = true;
    }
}
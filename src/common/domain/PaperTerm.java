package common.domain;

public class PaperTerm extends Relation {
    protected PaperTerm() {
        super(NodeType.TERM, NodeType.PAPER, "paper_term", 2);
        isDefault = true;
    }
}
package common.domain;

public class PaperAuthor extends Relation {
    protected PaperAuthor() {
        super(NodeType.AUTHOR, NodeType.PAPER, "paper_author", 0);
        isDefault = true;
    }
}

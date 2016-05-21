package common.domain;

public class PaperAuthor extends Relation {
    protected PaperAuthor() {
        super(NodeType.PAPER, NodeType.AUTHOR, "paper_author", 0);
        isDefault = true;
    }
}

package common.domain;

public class AuthorPaper extends Relation {
    protected AuthorPaper() {
        super(NodeType.AUTHOR, NodeType.PAPER, "AuthorPaper", 0);
        isDefault = true;
    }
}

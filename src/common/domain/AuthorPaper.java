package common.domain;

public class AuthorPaper extends Relation {
    protected AuthorPaper() {
        super(NodeType.AUTHOR, NodeType.PAPER, "AuthorPaper", 1);
        isDefault = true;
    }
}

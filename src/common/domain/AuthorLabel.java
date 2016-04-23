package common.domain;


public class AuthorLabel extends Relation {
    protected AuthorLabel() {
        super(NodeType.AUTHOR, NodeType.LABEL, "AuthorLabel", 3);
        isDefault = true;
    }
}

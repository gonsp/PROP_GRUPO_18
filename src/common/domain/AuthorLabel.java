package common.domain;


public class AuthorLabel extends Relation {
    protected AuthorLabel() {
        super(NodeType.AUTHOR, NodeType.LABEL, "author_label", 3);
        isDefault = true;
    }
}

package common.domain;

public class PaperConference extends Relation {
    protected PaperConference() {
        super(NodeType.CONF, NodeType.PAPER, "paper_conf", 1);
        isDefault = true;
    }
}
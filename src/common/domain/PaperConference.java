package common.domain;

public class PaperConference extends Relation {
    protected PaperConference() {
        super(NodeType.PAPER, NodeType.CONF, "paper_conf", 1);
        isDefault = true;
    }
}
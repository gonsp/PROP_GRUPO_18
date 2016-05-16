package common.domain;

public class ConferenceLabel extends Relation {
    protected ConferenceLabel() {
        super(NodeType.CONF, NodeType.LABEL, "conf_label", 5);
        isDefault = true;
    }
}
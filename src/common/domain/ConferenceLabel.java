package common.domain;

public class ConferenceLabel extends Relation {
    protected ConferenceLabel() {
        super(NodeType.CONFERENCE, NodeType.LABEL, "ConferenceLabel", 5);
        isDefault = true;
    }
}
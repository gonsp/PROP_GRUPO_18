package common.persistence;


import common.domain.Conference;
import common.domain.Node;

public class ConferenceSerializer extends NodeSerializer {

    public ConferenceSerializer(Node node) {
        super(node);
    }

    public ConferenceSerializer(String data) {
        super (data);
    }

}

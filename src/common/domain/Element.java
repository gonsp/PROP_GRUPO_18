package common.domain;

public abstract class Element {

    protected String value;
    protected int id;

    protected Element(String value) {
        id = -1;
        this.value = value;
    }

    protected Element(int nodeID, String value) {
        this.id = nodeID;
        this.value = value;
    }

    protected Element(Element e) {
        this(e.getId(), e.getValue());
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

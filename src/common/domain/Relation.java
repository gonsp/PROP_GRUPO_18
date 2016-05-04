package common.domain;

public class Relation extends Element {
    //Attributes
    private NodeType A;
    private NodeType B;
    protected boolean isDefault = false;

    //Constructors
    public Relation(NodeType A, NodeType B, String name) {
        super(name);
        this.A = A;
        this.B = B;
    }

    public Relation(NodeType A, NodeType B, String name, int id) {
        super(id, name);
        this.A = A;
        this.B = B;
    }

    public Relation(Relation r) {
        super(r);
        this.A = r.A;
        this.B = r.B;
    }

    //Get & Set
    public String getName() {
        return value;
    }

    public boolean isDefault() {
        return isDefault;
    }

    protected NodeType getNodeTypeA() {
        return A;
    }

    protected void setNodeTypeA(NodeType A) {
        this.A = A;
    }

    protected NodeType getNodeTypeB() {
        return B;
    }

    protected void setNodeTypeB(NodeType B) {
        this.B = B;
    }
}
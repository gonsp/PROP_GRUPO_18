package common.domain;

public class Relation extends Element{
	//Attributes
	private NodeType A;
	private NodeType B;
	
	//Constructors
	public Relation(NodeType A, NodeType B, String name, int id) {
        super(id, name);
		this.A = A;
		this.B = B;
	}
	
	//Get & Set
	public String getName() {
		return value;
	}
	
	public void setName(String name) {
		value = name;
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
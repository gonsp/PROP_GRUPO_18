package common.domain.graph;

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
	
	public NodeType getNodeTypeA() {
		return A;
	}
	
	public void setNodeTypeA(NodeType A) {
		this.A = A;
	}
	
	public NodeType getNodeTypeB() {
		return B;
	}
	
	public void setNodeTypeB(NodeType B) {
		this.B = B;
	}
}
package common.domain.search;

import common.domain.graph.NodeType;

public class Relation {
	//Attributes
	private NodeType A;
	private NodeType B;
	private String name;
	private int id;
	
	//Constructors
	public Relation(NodeType A, NodeType B, String name, int id) {
		this.A = A;
		this.B = B;
		this.name = name;
		this.id = id;
	}
	
	//Get & Set
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public NodeType getNodeA() {
		return A;
	}
	
	public void setNodeA(NodeType A) {
		this.A = A;
	}
	
	public NodeType getNodeB() {
		return B;
	}
	
	public void setNodeB(NodeType B) {
		this.B = B;
	}
}
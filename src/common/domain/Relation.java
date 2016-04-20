package common.domain;

public class Relation {
	//Attributes
	private NodeType from;
	private NodeType to;
	private String name;
	
	//Constructors
	public Relation(NodeType node_out, NodeType node_in, String name) {
		from = node_out;
		to = node_in;
		this.name = name;
	}
	
	//Get & Set
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public NodeType getNodeFrom() {
		return from;
	}
	
	public void setNodeFrom(NodeType from) {
		this.from = from;
	}
	
	public NodeType getNodeTo() {
		return to;
	}
	
	public void setNodeTo(NodeType to) {
		this.to = to;
	}
	

}
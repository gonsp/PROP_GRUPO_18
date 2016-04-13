package Domini;

import java.util.HashMap;

public class Graph {
	//Variables
	private String name;
	private double lastNodeId = 0, lastTermId = 0;
	private HashMap<String, Node> graph;
	
	
	//Constructors
	protected Graph(String name) {
		this.name = name;
		graph = new HashMap<String, Node>();
	}
	
	//Get & Set
	protected String getName() {
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	protected double getSize() {
		return graph.size();
	}
	
	
	//Graph Edition
	protected void addNewNode(Node node) {
		if (node.isTerm()) {
			graph.put("T" + Double.toString(lastTermId + 1), node);
			lastTermId++;
		} 
		else {
			graph.put(Double.toString(lastNodeId + 1), node);
			lastNodeId++;
		}
	}
	protected void removeNode(String key) {
		graph.remove(key);
	}
	
	
	//Queries
	protected Node getNode(String Id) throw GraphException {
		try{
			
			return graph.get(Id);
		}
		if(graph.find(id)) {
			return nodo;
		} else {
			GraphException e = new GraphEception();
		}

	}
}

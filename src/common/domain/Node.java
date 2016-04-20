package common.domain;

import java.util.List;
import java.util.HashMap;

public abstract class Node {
	//Attributes
	private String name;
	private int nodeID;
	private HashMap<Integer, List<Integer>> map;
	
	
	
	//Constructors
	public Node(){
	}
	public Node(String name, int nodeID){
		this.name = name;
		this.nodeID = nodeID;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addRelation(int relationID, int nodeID) {
		
		map.get(relationID).add(nodeID);
		
	}
	public void removeRelation (int relationID,int nodeID){
	
		map.get(relationID).remove(nodeID);
	}
	public Boolean isRelated (int relationID, int nodeID){
		return map.get(relationID).contains(nodeID);
	}
	public List<Integer> getRelations(int relationID){
		return map.get(relationID);
	}
	
}



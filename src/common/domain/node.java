package domini;

import java.util.List;
import java.util.HashMap;


public abstract class node {
	//Attributes
	private String name;
	private int nodeID;
	private HashMap<Integer, List<Integer>> map;
	
	
	
	//Constructors
	public node(){
	}
	public node(String name, int nodeID){
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
		//otropen
		map.get(relationID).remove(nodeID);
	}
	public Boolean isRelated (int relationID, int nodeID){
		return map.get(relationID).contains(nodeID);
	}
	public List<Integer> getRelations(int relationID){
		return map.get(relationID);
	}
	
}


//node(string name, int Id) constructora
//setValue(string value) establecer el valor de la instancia
//addRelation(int relationID, int nodeID)
//removeRelation(int relationID, int nodeID)
//isRelated(int TypeID, int nodeID)
//List<int> get Relations(int relationID)


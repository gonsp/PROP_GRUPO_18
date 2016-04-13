package domain;

import java.util.HashMap;

public class Graph {
	//Variables
	private String name;
	private int lastAuthorId = 0, lastPaperId = 0, lastTermId = 0;
	private int lastLabelId = 0, lastConferenceId = 0;
	private HashMap<Integer, Node> authors;
	private HashMap<Integer, Node> papers;
	private HashMap<Integer, Node> terms;
	private HashMap<Integer, Node> labels;
	private HashMap<Integer, Node> conferences;
	
	//Constructors
	protected Graph() {
		authors = new HashMap<Integer, Node>();
		papers = new HashMap<Integer, Node>();
		terms = new HashMap<Integer, Node>();
		labels = new HashMap<Integer, Node>();
		conferences = new HashMap<Integer, Node>();
	}
	
	protected Graph(String name) {
		this.name = name;
		authors = new HashMap<Integer, Node>();
		papers = new HashMap<Integer, Node>();
		terms = new HashMap<Integer, Node>();
		labels = new HashMap<Integer, Node>();
		conferences = new HashMap<Integer, Node>();
	}
	
	//Get & Set
	protected String getName() {
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected void setLastId(int lastUsed, NodeType type) {
		switch(type) {
			case AUTHOR:
				lastAuthorId = lastUsed;
				break;
			case PAPER:
				lastPaperId = lastUsed;
				break;
			case TERM:
				lastTermId = lastUsed;
				break;
			case LABEL:
				lastLabelId = lastUsed;
				break;
			case CONFERENCE:
				lastConferenceId = lastUsed;
				break;
		}
	}
	
	protected int getNodesSize(NodeType type) {
		int size;
		switch(type) {
			case AUTHOR:
				size = authors.size();
				break;
			case PAPER:
				size = papers.size();
				break;
			case TERM:
				size = terms.size();
				break;
			case LABEL:
				size = labels.size();
				break;
			case CONFERENCE:
				size = conferences.size();
				break;
			default: size = -1;
		}
		return size;
	}
	
	//Graph Edition
	protected void addNode(Node node, NodeType type) {
		switch(type) {
			case AUTHOR:
				authors.put(lastAuthorId + 1, node);
				break;
			case PAPER:
				papers.put(lastPaperId + 1, node);
				break;
			case TERM:
				terms.put(lastTermId + 1, node);
				break;
			case LABEL:
				labels.put(lastLabelId + 1, node);
				break;
			case CONFERENCE:
				conferences.put(lastConferenceId + 1, node);
				break;
		}
	}
	
	protected void addNode(Node node, NodeType type, int id) {
		switch(type) {
			case AUTHOR:
				authors.put(id, node);
				break;
			case PAPER:
				papers.put(id, node);
				break;
			case TERM:
				terms.put(id, node);
				break;
			case LABEL:
				labels.put(id, node);
				break;
			case CONFERENCE:
				conferences.put(id, node);
				break;
		}
	}
	
	protected void removeNode(NodeType type, int id) { /*TODO: Exceptions*/ 
		switch(type) {
			case AUTHOR:
				authors.remove(id);
				break;
			case PAPER:
				papers.remove(id);
				break;
			case TERM:
				terms.remove(id);
				break;
			case LABEL:
				labels.remove(id);
				break;
			case CONFERENCE:
				conferences.remove(id);
				break;
		}
	}

	//Queries
	protected Node getNode(int id, NodeType type) throws Exception { /*TODO: Exceptions*/ 
		Node n;
		switch(type) {
			case AUTHOR:
				if (authors.containsKey(id)) {
					n = authors.get(id);
				} else {
					Exception e = new Exception();
					n = null;
				}
				break;
			case PAPER:
				if (papers.containsKey(id)) {
					n = papers.get(id);
				} else {
					Exception e = new Exception();
					n = null;
				}
				break;
			case TERM:
				if (terms.containsKey(id)) {
					n = terms.get(id);
				} else {
					Exception e = new Exception();
					n = null;
				}
				break;
			case LABEL:
				if (labels.containsKey(id)) {
					n = labels.get(id);
				} else {
					Exception e = new Exception();
					n = null;
				}
				break;
			case CONFERENCE:
				if (conferences.containsKey(id)) {
					n = conferences.get(id);
				} else {
					Exception e = new Exception();
					n = null;
				}
				break;
			default: n = null;
		}
		return n;
	}
}

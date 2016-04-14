package domain;

import java.util.HashMap;

public class Graph {
        //Atributos
	private String name;

        private NodeContainer authors;
        private NodeContainer papers;
        private NodeContainer terms;
        private NodeContainer labels;
        private NodeContainer conferences;
	
	//Constructors
        public Graph() {
                authors = new NodeContainer();
                papers = new NodeContainer();
                terms = new NodeContainer();
                labels = new NodeContainer();
                conferences = new NodeContainer();
	}
	
        public Graph(String name) {
		this.name = name;
		Graph();
	}
	
	//Get & Set
        public String getName() {
		return name;
	}
	
        public void setName(String name) {
		this.name = name;
	}
	
        public void setLastId(int lastUsed, NodeType type) throws GraphException {
                getNodeContainerFromNodeType(type).setLastID(ID);
	}
	
        public int getSize(NodeType type) throws GraphException {
		return getNodeContainerFromNodeType(type).getSize();
	}
	
	//Graph Edition
        public void addNode(Node node) {
                getNodeContainerFromNodeType(getNodeTypeFromNode(node)).addNode(node);
	}
	
        public void addNode(Node node, int id) throws GraphException {
                getNodeContainerFromNodeType(getNodeTypeFromNode(node)).addNode(node, id);
	}
	
        public void removeNode(NodeType type, int id) throws GraphException {
		getNodeContainerFromNodeType(type).removeNode(id);
	}

	//Queries
        public Node getNode(NodeType type, int id) throws GraphException {
		return getNodeContainerFromNodeType(type).getNode(id);
	}
	
	private NodeType getNodeTypeFromNode(Node node) {
                if(node instanceof Author) {
                        return AUTHOR;
                } else if(node instanceof Paper) {
                        return PAPER;
                } else if(node instanceof Term) {
                        return TERM;
                } else if(node instanceof Label) {
                        return LABEL;
                } else {
                        return CONFERENCE
                }
        }
	
        private NodeContainer getNodeContainerFromNodeType(NodeType type) {
                switch(type) {
			case AUTHOR:
				return authors;
				break;
			case PAPER:
                                return papers;
                                break;
			case TERM:
				return terms;
				break;
			case LABEL:
				return labels;
				break;
			case CONFERENCE:
				return conferences;
				break;
		}
        }
}

package common.domain.tests;

import common.domain.*;
import common.persistence.PersistenceController;

import java.util.ArrayList;

public class DomainController {

    private Graph graph;
    private PersistenceController persistenceController;

    public DomainController() {
        graph = new Graph();
        persistenceController = new PersistenceController(graph);
    }

    public DomainController(String path) {
        persistenceController = new PersistenceController(graph);
        importDB(path);
    }

    public void importDB(String path) {
        graph = new Graph();
        persistenceController.importNodes(path + "author.txt", NodeType.AUTHOR);
        persistenceController.importNodes(path + "conf.txt", NodeType.CONF);
        persistenceController.importNodes(path + "paper.txt", NodeType.PAPER);
        persistenceController.importNodes(path + "term.txt", NodeType.TERM);
        persistenceController.importEdges(path + "author_label.txt", NodeType.AUTHOR, NodeType.LABEL);
        persistenceController.importEdges(path + "conf_label.txt", NodeType.CONF, NodeType.LABEL);
        persistenceController.importEdges(path + "paper_author.txt", NodeType.PAPER, NodeType.AUTHOR);
        persistenceController.importEdges(path + "paper_conf.txt", NodeType.PAPER, NodeType.CONF);
        persistenceController.importEdges(path + "paper_label.txt", NodeType.PAPER, NodeType.LABEL);
        persistenceController.importEdges(path + "paper_term.txt", NodeType.PAPER, NodeType.TERM);
    }

    public void exportDB(String path) {
        try {
            persistenceController.exportGraph(path);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    public void addElement(NodeType type, String value) {
        Node node = graph.createNode(type, value);
        graph.addNode(node);
    }

    public void removeElement(NodeType type, int id) {
        try {
            graph.removeNode(type, id);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    public void addRelation(NodeType A, NodeType B, String name) {
        graph.addRelation(new Relation(A, B, name));
    }

    public void removeRelation(int id) {
        try {
            graph.removeRelation(id);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    public void addEdge(int relationID, NodeType typeA, int nodeA, NodeType typeB, int nodeB) {
        try {
            graph.addEdge(relationID, typeA, nodeA, typeB, nodeB);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    public void removeEdge(int relationID, NodeType typeA, int nodeA, NodeType typeB, int nodeB) {
        try {
            graph.removeEdge(relationID, typeA, nodeA, typeB, nodeB);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<GraphSearch.Result> simpleSearch(NodeType type, String filter) {
        SimpleSearch simpleSearch = new SimpleSearch(graph, type, filter);
        simpleSearch.search();
        return simpleSearch.getResults();
    }
}

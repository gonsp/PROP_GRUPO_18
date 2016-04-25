package common.domain.tests;

import common.domain.Graph;
import common.domain.GraphException;
import common.domain.Node;
import common.domain.NodeType;
import common.persistence.PersistenceController;

public class DomainController {

    private Graph graph;
    private PersistenceController persistenceController;

    public DomainController() {
        graph = new Graph();
        persistenceController = new PersistenceController(graph);
    }

    public DomainController(String path) {
        this();
        persistenceController.importNodes("data/author.txt", NodeType.AUTHOR);
        persistenceController.importNodes("data/conf.txt", NodeType.CONF);
        persistenceController.importNodes("data/paper.txt", NodeType.PAPER);
        persistenceController.importNodes("data/term.txt", NodeType.TERM);
        persistenceController.importEdges("data/author_label.txt", NodeType.AUTHOR, NodeType.LABEL);
        persistenceController.importEdges("data/conf_label.txt", NodeType.CONF, NodeType.LABEL);
        persistenceController.importEdges("data/paper_author.txt", NodeType.PAPER, NodeType.AUTHOR);
        persistenceController.importEdges("data/paper_conf.txt", NodeType.PAPER, NodeType.CONF);
        persistenceController.importEdges("data/paper_label.txt", NodeType.PAPER, NodeType.LABEL);
        persistenceController.importEdges("data/paper_term.txt", NodeType.PAPER, NodeType.TERM);
    }

    public void addElement(NodeType type, String value) {
        Node node = graph.createNode(type, value);
        graph.addNode(node);
    }
}

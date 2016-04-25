package common.domain.tests;

import common.domain.*;
import common.persistence.*;

/* This class loads the default files into a Graph structure through the persistence controller,
    to be used in Test Drivers (in order to have some data to carry the tests with).
 */

class StubGraph {
    private Graph g;

    public StubGraph() {
        g = new Graph();

        PersistenceController pc = new PersistenceController(g);

        pc.importNodes("data/author.txt", NodeType.AUTHOR);
        pc.importNodes("data/conf.txt", NodeType.CONF);
        pc.importNodes("data/paper.txt", NodeType.PAPER);
        pc.importNodes("data/term.txt", NodeType.TERM);
        pc.importEdges("data/author_label.txt", NodeType.AUTHOR, NodeType.LABEL);
        pc.importEdges("data/conf_label.txt", NodeType.CONF, NodeType.LABEL);
        pc.importEdges("data/paper_author.txt", NodeType.PAPER, NodeType.AUTHOR);
        pc.importEdges("data/paper_conf.txt", NodeType.PAPER, NodeType.CONF);
        pc.importEdges("data/paper_label.txt", NodeType.PAPER, NodeType.LABEL);
        pc.importEdges("data/paper_term.txt", NodeType.PAPER, NodeType.TERM);

    }

    public Graph getGraph() {
        return g;
    }
}
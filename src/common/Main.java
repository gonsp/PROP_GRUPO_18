package common;

import common.domain.graph.Graph;
import common.domain.graph.GraphException;
import common.persistence.PersistenceController;


public class Main {

    public static void main(String[] args) throws GraphException {

        Graph g = new Graph();
        PersistenceController pc = new PersistenceController();
        //pc.importNodes(g, "data/author.txt", "Author");
        //pc.importEdges(g, "data/author_label.txt", "Author", "Label");

    }

}

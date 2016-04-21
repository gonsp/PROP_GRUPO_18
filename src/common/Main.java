package common;

import common.domain.Graph;
import common.domain.GraphException;
import common.persistence.PersistenceController;


public class Main {

    public static void main(String[] args) throws GraphException {

        Graph g = new Graph();
        PersistenceController pc = new PersistenceController();
        pc.importNodes(g, "data/author.txt", "Author");

    }

}

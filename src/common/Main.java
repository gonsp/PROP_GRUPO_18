package common;

import common.domain.Graph;
import common.persistence.PersistenceController;


public class Main {

    public static void main(String[] args) {

        Graph g = new Graph();
        PersistenceController pc = new PersistenceController();

        pc.importNode(g, "data/test.txt", "Author");

    }

}

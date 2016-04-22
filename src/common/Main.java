package common;

import common.domain.graph.*;
import common.domain.search.SimpleSearch;
import common.persistence.PersistenceController;
import org.jblas.DoubleMatrix;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws GraphException {

        Graph g = new Graph();
        PersistenceController pc = new PersistenceController();

        Author test = new Author("pepe");

        g.addNode(test);

        SimpleSearch search = new SimpleSearch(g, NodeType.AUTHOR, "pep");
        ArrayList<Node> result = search.getResult();
        for(int i = 0; i < result.size(); ++i) {
            System.out.println(result.get(i).getValue());
        }


        DoubleMatrix m = new DoubleMatrix(10, 10);


        //pc.importNodes(g, "data/author.txt", "Author");
        //pc.importEdges(g, "data/author_label.txt", "Author", "Label");

    }

}

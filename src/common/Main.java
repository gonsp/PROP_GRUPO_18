package common;

import common.domain.*;
import common.persistence.PersistenceController;
import org.jblas.DoubleMatrix;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws GraphException {

        Graph g = new Graph();

        PersistenceController pc = new PersistenceController();
        pc.importNodes(g, "data/author.txt", NodeType.AUTHOR);
        pc.importNodes(g, "data/conf.txt", NodeType.CONFERENCE);
        pc.importNodes(g, "data/paper.txt", NodeType.PAPER);
        pc.importNodes(g, "data/term.txt", NodeType.TERM);
        pc.exportNodes(g, "exp/");
        //pc.importEdges(g, "data/author_label.txt", "Author", "Label");


        /*Author a1 = new Author("a1");
        Author a2 = new Author("a2");
        Author a3 = new Author("a3");

        Paper p1 = new Paper("p1");
        Paper p2 = new Paper("p2");
        Paper p3 = new Paper("p3");
        Paper p4 = new Paper("p4");

        Relation AP = new Relation(NodeType.PAPER, NodeType.AUTHOR, "AP");

        g.addNode(a1);
        g.addNode(a2);
        g.addNode(a3);
        g.addNode(p1);
        g.addNode(p2);
        g.addNode(p3);
        g.addNode(p4);
        g.addRelation(AP);
        try {
            g.addEdge(AP.getId(), a1, p1);
        } catch(GraphException e) {
            e.printStackTrace();
        }
        g.addEdge(AP.getId(), a1, p2);
        g.addEdge(AP.getId(), a2, p2);
        g.addEdge(AP.getId(), a2, p3);
        g.addEdge(AP.getId(), a2, p4);
        g.addEdge(AP.getId(), a3, p4);

        try {
            ArrayList<Relation> aux = new ArrayList<Relation>();
            aux.add(AP);
            aux.add(AP);
            RelationStructure rs = new RelationStructure(NodeType.AUTHOR, aux, NodeType.AUTHOR);
            RelationalSearch relationalSearch = new RelationalSearch(g, rs);
            relationalSearch.search();
            ArrayList<RelationalSearch.Result> results = relationalSearch.getResults();
            for(int i = 0; i < results.size(); ++i) {
                results.get(i).print();
            }
        } catch (RelationStructureException e) {
            e.printStackTrace();
        }*/


        //TODO implementar relation nula en medio de relationStructure cuando es impar
        //TODO método públio en grafo para acceder a getIdFromValue de container
        //TODO Subclases de Relation para las relaciones predeterminadas
        //TODO metodos en relationStructure para facilitar busqueda
        //TODO removeRelation by name throws exception
    }

}

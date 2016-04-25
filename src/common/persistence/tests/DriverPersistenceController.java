package common.persistence.tests;


import common.domain.Graph;
import common.domain.GraphException;
import common.domain.Node;
import common.domain.NodeType;
import common.persistence.PersistenceController;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverPersistenceController {

    PersistenceController controller = null;
    Graph g = new Graph();

    public void constructor() {
        controller = new PersistenceController(g);
    }

    public void testImportNodes() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Write a path to import");
        String path = keyboard.nextLine();
        System.out.println(g.getSize(NodeType.AUTHOR)); //comprovem que s'han afegit mirant el tamany del graf abans i despres de importar
        controller.importNodes(path, NodeType.AUTHOR);
        System.out.println(g.getSize(NodeType.AUTHOR));
    }

    public void testImportEdges() throws GraphException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Write a path to import");
        String path = keyboard.nextLine();
        System.out.println("Write two id for the nodes");
        int id1 = keyboard.nextInt();
        int id2 = keyboard.nextInt();
        Node n1 = g.createNode(NodeType.PAPER, "Piedra Filosofal");
        g.addNode(n1, id1);
        Node n2 = g.createNode(NodeType.AUTHOR, "Harry Potter");
        g.addNode(n2, id2);
        controller.importEdges(path, NodeType.PAPER, NodeType.AUTHOR);
        ArrayList<Node> a = g.getEdges(0, n1);
        System.out.println(a.get(0).getId());
        System.out.println(a.get(0).getValue());
    }

    public void testExportGraph() throws GraphException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Write a path to export the graph");
        String path = keyboard.nextLine();
        System.out.println("Write two id for the nodes");
        int id1 = keyboard.nextInt();
        int id2 = keyboard.nextInt();
        Node n1 = g.createNode(NodeType.PAPER, "Piedra Filosofal");
        g.addNode(n1, id1);
        Node n2 = g.createNode(NodeType.AUTHOR, "Harry Potter");
        g.addNode(n2, id2);
        g.addEdge(0, n1, n2);
        controller.exportGraph(path);
    }

    public void main() throws GraphException {
        int menu = 0;
        while (menu != 4) {
            System.out.println("CHOOSE A METHOD TO TEST:");
            System.out.println("1. PersistenceController(Graph graph) + importNodes(String path, NodeType type)");
            System.out.println("2. PersistenceController(Graph graph) + importEdges(String path, NodeType type1, NodeType type2)");
            System.out.println("3. PersistenceController(Graph graph) + exportGraph(String path)");
            System.out.println("4. Return Main Menu");
            Scanner keyboard = new Scanner(System.in);
            menu = keyboard.nextInt();
            switch (menu) {
                case 1:
                    this.constructor();
                    this.testImportNodes();
                    break;
                case 2:
                    this.constructor();
                    this.testImportEdges();
                    break;
                case 3:
                    this.constructor();
                    this.testExportGraph();
                    break;
            }
            System.out.println(" ");
        }
    }
}

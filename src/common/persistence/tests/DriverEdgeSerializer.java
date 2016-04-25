package common.persistence.tests;


import common.domain.*;
import common.persistence.EdgeSerializer;
import common.persistence.LabelSerializer;

import java.util.Scanner;

public class DriverEdgeSerializer {

    EdgeSerializer serializer = null;
    int id1 = 0;
    int id2 = 0;

    public void constructor1() throws GraphException {
        Graph g = new Graph();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Write two integers");
        id1 = keyboard.nextInt();
        id2 = keyboard.nextInt();
        String data = Integer.toString(id1) + "\t" + Integer.toString(id2);
        Node n1 = g.createNode(NodeType.AUTHOR, "Harry Potter");
        Node n2 = g.createNode(NodeType.PAPER, "Gollum");
        g.addNode(n1, id1);
        g.addNode(n2, id2);
        serializer = new EdgeSerializer(g, data, NodeType.AUTHOR, NodeType.PAPER);
    }

    public void constructor2() throws GraphException {
        Graph g = new Graph();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Write a two integers");
        id1 = keyboard.nextInt();
        id2 = keyboard.nextInt();
        Node n1 = g.createNode(NodeType.AUTHOR, "Harry Potter");
        Node n2 = g.createNode(NodeType.PAPER, "Gollum");
        g.addNode(n1, id1);
        g.addNode(n2, id2);
        serializer = new EdgeSerializer(n1, n2);
    }

    public void testGetData() {
        System.out.println(serializer.getData());
    }

    public void testGetNode1() {
        System.out.println(serializer.getNode1().getId());
    }

    public void testGetNode2() {
        System.out.println(serializer.getNode2().getId());
    }

    public void main() throws GraphException {
        int menu = 0;
        while (menu != 3) {
            System.out.println("CHOOSE A METHOD TO TEST:");
            System.out.println("1. EdgeSerializer(Node node1, Node node2) + getData()");
            System.out.println("2. EdgeSerializer(Graph graph, String data, NodeType ntype1, NodeType ntype2) + int getNode1() + String getNode2()");
            System.out.println("3. Return Main Menu");
            Scanner keyboard = new Scanner(System.in);
            menu = keyboard.nextInt();
            switch (menu) {
                case 1:
                    this.constructor2();
                    this.testGetData();
                    break;
                case 2:
                    this.constructor1();
                    this.testGetNode1();
                    this.testGetNode2();
                    break;
            }
            System.out.println(" ");
        }
    }
}

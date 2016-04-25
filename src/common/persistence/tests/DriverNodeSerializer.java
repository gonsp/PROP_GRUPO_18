package common.persistence.tests;

import common.domain.Graph;
import common.domain.GraphException;
import common.domain.Node;
import common.domain.NodeType;
import common.persistence.NodeSerializer;

import java.util.Scanner;


public class DriverNodeSerializer {

    NodeSerializer serializer = null;

    public void testConstructor1() {
        System.out.println("Write an int (id) and String (name)");
        Scanner keyboard = new Scanner(System.in);
        String data = keyboard.nextLine();
        serializer = new NodeSerializer(data);
    }

    public void testConstructor2() throws GraphException {
        System.out.println("Write an int (id) String (name)");
        Scanner keyboard = new Scanner(System.in);
        int id = keyboard.nextInt();
        String data = keyboard.nextLine();
        Graph g = new Graph();
        Node n = g.createNode(NodeType.AUTHOR, data);
        g.addNode(n, id);
        serializer = new NodeSerializer(n);
    }

    public void testGetData() {
        System.out.println(serializer.getData());
    }

    public void testGetId() {
        System.out.println(serializer.getId());
    }

    public void testGetName() {
        System.out.println(serializer.getName());
    }

    public void main() throws GraphException {
        int menu = 0;
        while (menu != 3) {
            System.out.println("CHOOSE A METHOD TO TEST:");
            System.out.println("1. NodeSerializer(String data) + int getId() + String getName()");
            System.out.println("2. NodeSerializer(Node node) + int getData()");
            System.out.println("3. Return Main Menu");
            Scanner keyboard = new Scanner(System.in);
            menu = keyboard.nextInt();
            switch (menu) {
                case 1:
                    this.testConstructor1();
                    this.testGetId();
                    this.testGetName();
                    break;
                case 2:
                    this.testConstructor2();
                    this.testGetData();
                    break;
            }
            System.out.println(" ");
        }
    }
}

package common.persistence.tests;


import common.domain.*;
import common.persistence.LabelSerializer;
import java.util.Scanner;

public class DriverEdgeSerializer {

    LabelSerializer serializer = null;
    int id1 = 0;
    int id2 = 0;

    public void constructor() {
        Graph g = new Graph();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Write a String (id1, id2)");
        id1 = keyboard.nextInt();
        id2 = keyboard.nextInt();
        String data = Integer.toString(id1) + "\t" + Integer.toString(id2);
        serializer = new LabelSerializer(g, data, NodeType.AUTHOR,NodeType.PAPER);
    }

    public void testGetData(){
        System.out.println(serializer.getData());
    }

    public void testGetNode1() {
        System.out.println(id1);
    }

    public void testGetNode2() {
        System.out.println(id2);
    }

    public void main(){
        int menu = 0;
        while (menu != 3) {
            System.out.println("CHOOSE A METHOD TO TEST:");
            System.out.println("1. int getNode1() + String getNode2()" );
            System.out.println("2. EdgeSerializer + getData()");
            System.out.println("3. Return Main Menu");
            Scanner keyboard = new Scanner(System.in);
            menu = keyboard.nextInt();
            switch (menu) {
                case 1:
                    this.constructor();
                    this.testGetNode1();
                    this.testGetNode2();
                    break;
                case 2:
                    this.constructor();
                    this.testGetData();
                    break;
            }
            System.out.println(" ");
        }
    }
}

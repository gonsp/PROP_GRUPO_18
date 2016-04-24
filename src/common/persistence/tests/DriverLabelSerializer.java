package common.persistence.tests;


import common.domain.Graph;
import common.domain.NodeType;
import common.persistence.LabelSerializer;
import java.util.Scanner;

public class DriverLabelSerializer {

    LabelSerializer serializer = null;

    public void constructor() {
        Graph g = new Graph();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Write a String (id1, id2)");
        String data = keyboard.nextLine();
        serializer = new LabelSerializer(g,data, NodeType.AUTHOR,NodeType.PAPER);
    }

    public void testGetData(){
        System.out.println(serializer.getData());
    }


    public void main(){
        int menu = 0;
        while (menu != 2) {
            System.out.println("CHOOSE A METHOD TO TEST:");
            System.out.println("1. LabelSerializer + getData()");
            System.out.println("2. Return Main Menu");
            Scanner keyboard = new Scanner(System.in);
            menu = keyboard.nextInt();
            switch (menu) {
                case 1:
                    this.constructor();
                    this.testGetData();
                    break;
            }
            System.out.println(" ");
        }
    }
}

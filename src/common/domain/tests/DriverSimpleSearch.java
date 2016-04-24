/*package common.domain.tests;

import common.domain.*;
import java.util.Scanner;
import java.util.ArrayList;

public class DriverRelationalSearch {
    private Graph g;

    public void DriverRelationalSearch() {
        StubGraph sb = new StubGraph();
        g = sb.getGraph();
    }

    private NodeType getNodeType(int nt) {
        if (nt == 1) return NodeType.AUTHOR;
        else if (nt == 2) return NodeType.CONF;
        else if (nt == 3) return NodeType.TERM;
        else return NodeType.PAPER;
    }

    public static void main(String[] args) {
        System.out.println("SimpleSearch class test.");
        int nodtype;
        System.out.println("Enter node type: (1 - Author, 2 - Conference, 3 - Term, 4 - Paper,  0 - EXIT)");
        Scanner input = new Scanner(System.in);
        nodtype = input.nextInt();
        while (nodtype != 0) {
            System.out.println("Enter patter to search: (or quit to go back)");
            String pattern = input.next();
            while(!pattern.equals("quit")) {
                SimpleSearch ss = new SimpleSearch(g, this.getNodeType(nodtype), pattern);
                ss.search();
                ArrayList<Result> res = ss.getResults();
                for (int i = 0; i < res.size(); i++) {
                    System.out.println("Node: " + res.get(i).from.getValue());
                }
                System.out.println("Enter patter to search: (or quit to go back)");
                String pattern = input.next();
            }
            System.out.println("Enter node type: (1 - Author, 2 - Conference, 3 - Term, 4 - Paper,  0 - EXIT)");
            nodtype = input.nextInt();
        }
    }

}*/
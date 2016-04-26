package common.domain.tests;

import common.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DriverRelationStructure {

    private Graph g;
    private HashMap<Integer, Integer> relationids;

    private NodeType getNodeType(char c) {
        NodeType n;
        switch(c) {
            case 'A':
                n = NodeType.AUTHOR;
                break;
            case 'T':
                n = NodeType.TERM;
                break;
            case 'L':
                n = NodeType.LABEL;
                break;
            case 'P':
                System.out.println("Paper");
                n = NodeType.PAPER;
                break;
            default:
                n = NodeType.CONF;
                break;
        }
        return n;
    }

    private int translateId(Integer id) {
        return relationids.get(id).intValue();
    }

    public DriverRelationStructure() {
        g = new Graph();
        relationids = new HashMap<>();
    }

    public void main() {
        System.out.println("RelationStructure Test:");
        System.out.println("Insert new relation ID:");
        Scanner s = new Scanner(System.in);
        int rid = s.nextInt();
        while(rid >= 0) {
            System.out.println("Insert relation name:");
            String name = s.next();
            System.out.println("Insert type of the first node (A, C, T, L, P):");
            NodeType first = getNodeType(s.next().toUpperCase().toCharArray()[0]);
            System.out.println("Insert type of the second node (A, C, T, L, P):");
            NodeType second = getNodeType(s.next().toUpperCase().toCharArray()[0]);
            Relation r = new Relation(first, second, name, rid);
            g.addRelation(r);
            relationids.put(rid, r.getId());
            System.out.println("Relation added. Insert new relation ID or -1 co continue:");
            rid = s.nextInt();
        }
        System.out.println("Specify a origin node type for the Relation Structure (A, C, T, L, P) or quit:");
        String st = s.next();
        while(!st.equals("quit")) {
            NodeType origin = getNodeType(st.toUpperCase().toCharArray()[0]);
            System.out.println("Specify a destiny node type for the Relation Structure (A, C, T, L, P) or quit:");
            NodeType destiny = getNodeType(s.next().toUpperCase().toCharArray()[0]);
            System.out.println("Now write the relation IDs to go trough, one per line. End with -1.");
            ArrayList<Integer> intlist = new ArrayList<>();
            rid = s.nextInt();
            while (rid >= 0) {
                intlist.add(rid);
                rid = s.nextInt();
            }
            System.out.println("Validating Relation Structure...");
            try {
                int[] indices = new int[intlist.size()];
                for(int i = 0; i < intlist.size(); ++i) indices[i] = translateId(intlist.get(i));
                RelationStructure rs = new RelationStructure(g, origin, indices, destiny);
                System.out.println("Valid Relation Structure!");
            } catch (RelationStructureException rse) {
                rse.printStackTrace();
                System.out.println("Invalid Relation Structure!");
            }
            System.out.println("Specify a origin node type for the Relation Structure (A, C, T, L, P) or quit:");
            st = s.next();
        }
    }



}

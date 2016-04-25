package common.domain.tests;

import common.domain.*;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverRelationStructure {

    private ArrayList<Relation> relationList;
    private NodeType from, to;
    boolean error;

    public DriverRelationStructure () {
        relationList = new ArrayList<Relation>();
    }

    private void parseRelation(String seq) {
        char[] ch_array = seq.toCharArray();
        from = this.getNodeType(ch_array[0]);
        to = this.getNodeType(ch_array[ch_array.length-1]);
        Relation r = new Relation(from, this.getNodeType(ch_array[1]), "");
        relationList.add(r);
        NodeType last = this.getNodeType(ch_array[1]);
        for(int i = 2; i < ch_array.length; ++i) {
            r = new Relation(last, this.getNodeType(ch_array[i]), "");
            relationList.add(r);
            last = this.getNodeType(ch_array[i]);
        }
    }

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
                n = NodeType.PAPER;
                break;
            default:
                n = NodeType.CONF;
                break;
        }
        return n;
    }

    public void main() {
        System.out.println("RelationStructure Test:");
        System.out.println("Enter a sequence of NodeTypes or quit:");
        Scanner s = new Scanner(System.in);
        String seq = s.next();
        while(!seq.equals("quit") && seq.length() >= 2) {
            error = false;
            this.parseRelation(seq);
            try {
                RelationStructure rs = new RelationStructure(from, relationList, to);
            } catch (RelationStructureException rse) {
                error = true;
            }
            if (error) System.out.println("Incorrect relation structure (exception)");
            else System.out.println("Correct relation structure");
            seq = s.next();
        }
    }

}

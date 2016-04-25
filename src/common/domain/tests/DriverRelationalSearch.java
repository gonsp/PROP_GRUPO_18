package common.domain.tests;

import common.domain.*;
import java.util.ArrayList;

public class DriverRelationalSearch {

    protected Graph g;
    protected ArrayList<Relation> relationList;
    protected NodeType from, to;

    public DriverRelationalSearch() {
        StubGraph sb = new StubGraph();
        g = sb.getGraph();
        relationList = new ArrayList<>();
    }

    protected void parseRelation(String seq) {
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

    protected NodeType getNodeType(char c) {
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
}

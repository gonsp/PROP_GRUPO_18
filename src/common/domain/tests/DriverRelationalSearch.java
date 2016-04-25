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

    protected void parseRelation(String seq) throws GraphException {
        char[] ch_array = seq.toCharArray();
        from = this.getNodeType(ch_array[0]);
        to = this.getNodeType(ch_array[ch_array.length-1]);
        NodeType last = this.getNodeType(ch_array[0]);
        for(int i = 1; i < ch_array.length; ++i) {
            Relation r = g.getRelation(this.getRelationId(last, this.getNodeType(ch_array[i])));
            relationList.add(r);
            last = this.getNodeType(ch_array[i]);
        }
    }

    protected int getRelationId(NodeType f, NodeType t) {
        int res = -1;
        if (f.equals(NodeType.AUTHOR) && t.equals(NodeType.LABEL)) {
            res = 3;
        } else if (f.equals(NodeType.CONF) && t.equals(NodeType.LABEL)) {
            res = 5;
        } else if (f.equals(NodeType.PAPER) && t.equals(NodeType.AUTHOR)) {
            res = 0;
        } else if (f.equals(NodeType.PAPER) && t.equals(NodeType.CONF)) {
            res = 1;
        } else if (f.equals(NodeType.PAPER) && t.equals(NodeType.LABEL)) {
            res = 4;
        } else if (f.equals(NodeType.PAPER) && t.equals(NodeType.TERM)) {
            res = 2;
        } else if (f.equals(NodeType.LABEL) && t.equals(NodeType.AUTHOR)) {
            res = 3;
        } else if (f.equals(NodeType.LABEL) && t.equals(NodeType.CONF)) {
            res = 5;
        } else if (f.equals(NodeType.AUTHOR) && t.equals(NodeType.PAPER)) {
            res = 0;
        } else if (f.equals(NodeType.CONF) && t.equals(NodeType.PAPER)) {
            res = 1;
        } else if (f.equals(NodeType.LABEL) && t.equals(NodeType.PAPER)) {
            res = 4;
        } else if (f.equals(NodeType.TERM) && t.equals(NodeType.PAPER)) {
            res = 2;
        }
        return res;
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

package common.persistence;

import java.io.Serializable;


public class EdgeSerializer implements Serializable {

    protected String data = null;

    protected int node1;
    protected int node2;
    protected String ntype;

    protected void inflate() {

    }

    protected void deflate() {

    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    public String getType() {
        return ntype;
    }

}
package common.persistence;

import common.domain.Node;

import java.io.Serializable;


public class EdgeSerializer implements Serializable {

    protected String data = null;
    protected Node node1;
    protected Node node2;
    protected String etype;

    protected void inflate() {
        if(true){

        }
    }

    protected void deflate() {

    }

    public EdgeSerializer(String data, String etype){
        this.etype = etype;
        this.data = data;
    }

    public EdgeSerializer(Node node1, Node node2, String etype){
        this.etype = etype;
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public String getType() {
        return etype;
    }

}
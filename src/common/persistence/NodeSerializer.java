package common.persistence;

import java.io.Serializable;

import common.domain.Node;


public abstract class NodeSerializer implements Serializable {

    private Node node = null;
    private String data = null;

    private int nodeId;
    private String name;
    
    protected void inflate(String text) {
        if (node == null) {
            int m = text.indexOf('\t');
            nodeId = Integer.parseInt(text.substring(0,m-1));
            name = text.substring(m+1);
        }
    }    

    protected void deflate() {
        if (data == null) {
            data = new String();
            data.concat(node.nodeId.toString());
            data.concat("\t");
            data.concat(node.name);
        }
    }

    public NodeSerializer(Node node) {
        this.node = node;
    }

    public NodeSerializer(String data) {
        this.data = data;
    }
    
    public abstract Node getNode(); 
    
    public String getData() {
        this.deflate();
        return data;
    }

}

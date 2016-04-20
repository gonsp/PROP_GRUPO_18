package common.persistence;

import java.io.Serializable;

import common.domain.Node;


public abstract class NodeSerializer implements Serializable {

    // TODO: Controlar a inflate/defalte quan no hi ha data o node

    protected Node node = null;
    protected String data = null;

    protected int nodeId;
    protected String name;

    protected void inflate() {
        if (node == null) {
            int m = data.indexOf('\t');
            nodeId = Integer.parseInt(data.substring(0,m-1));
            name = data.substring(m+1);
        }
    }    

    protected void deflate() {
        if (data == null) {
            data = new String();
            data.concat(node.getNodeId().toString());
            data.concat("\t");
            data.concat(node.getName());
        }
    }

    public NodeSerializer(Node node) {
        this.node = node;
    }

    public NodeSerializer(String data) {
        this.data = data;
    }
    
    public String getData() {
        this.deflate();
        return data;
    }

    public int getId() {
        return nodeId;
    }

    public abstract Node getNode();

}

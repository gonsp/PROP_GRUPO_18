package common.persistence;

import java.io.Serializable;

import common.domain.Node;


public abstract class NodeSerializer implements Serializable {

    protected Node node = null;
    protected String data = null;

    protected int nodeId;
    protected String name;

    protected void inflate() {
        if (node == null && data != null) {
            int m = data.indexOf("\t");
            if(m == -1){
                m = data.indexOf(" ");
            }
            nodeId = Integer.parseInt(data.substring(0, m));
            name = data.substring(m + 1, data.length()).trim();
        }
    }

    protected void deflate() {
        if (data == null && node != null) {
            data = new String();
            data.concat(String.valueOf(node.getId()));
            data.concat("\t");
            data.concat(node.getValue());
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

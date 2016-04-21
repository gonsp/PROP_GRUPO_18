package common.persistence;

import java.io.Serializable;

import common.domain.Node;


public abstract class NodeSerializer implements Serializable {

    protected Node node = null;
    protected String data = null;

    protected int nodeId;
    protected String name;

    protected void inflate() {
        if (data != null) {
            int m = data.indexOf(" ");
            nodeId = Integer.parseInt(data.substring(0, m - 1));
            name = data.substring(m + 1, data.length() - 1).trim();
        }
    }

    protected void deflate() {
        if (node != null) {
            data = new String();
            data.concat(String.valueOf(node.getId()));
            data.concat(" ");
            data.concat(node.getName());
        }
    }

    public NodeSerializer(Node node) {
        this.node = node;
    }

    public NodeSerializer(String data) {
        this.data = data;
    }

    // TODO: Handle exception when it does not have data
    public String getData() {
        this.deflate();
        return data;
    }

    public int getId() {
        return nodeId;
    }

    public abstract Node getNode();

}

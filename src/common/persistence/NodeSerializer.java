package common.persistence;

import java.io.Serializable;

import common.domain.Node;


public class NodeSerializer implements Serializable {

    protected Node node;
    protected String data;

    protected int nodeId;
    protected String name;

    protected void inflate() {
        if (node == null) {
            int m = data.indexOf("\t");
            if (m == -1) {
                m = data.indexOf(" ");
            }
            nodeId = Integer.parseInt(data.substring(0, m));
            name = data.substring(m + 1, data.length()).trim();
        }
    }

    protected void deflate() {
        if (data == null) {
            nodeId = node.getId();
            name = node.getValue();
            data = Integer.toString(nodeId) + "\t" + name;
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
        this.inflate();
        return nodeId;
    }

    public String getName() {
        this.inflate();
        return name;
    }

}

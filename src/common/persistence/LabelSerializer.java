package common.persistence;

import common.domain.Label;


public class LabelSerializer extends NodeSerializer {

    private int labelId;

    public LabelSerializer(Label node) { super(node); }

    public LabelSerializer(String data) { super(data); }

    @Override
    public void inflate() {
        if(data != null){
            int m = data.indexOf(" ");
            nodeId = Integer.parseInt(data.substring(0, m-1));
            labelId = Integer.parseInt(data.substring(m+1,m+1));
            name = data.substring(m + 3, data.length() - 1).trim();
        }
    }

    @Override
    public Label getNode(){
        this.inflate();
        node = new Label(nodeId, labelId, name);
        return ((Label)node);
    }
}

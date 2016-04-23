package common.persistence;

import common.domain.Label;


public class LabelSerializer extends NodeSerializer {

    public LabelSerializer(Label node) {
        super(node);
    }

    public LabelSerializer(String data) {
        super(data);
    }

    @Override
    public void inflate() {
        if(data != null){
            int m = data.indexOf("\t");
            if (m == -1) {
                m = data.indexOf(" ");
            }
            nodeId = Integer.parseInt(data.substring(0, m));
            name = data.substring(m + 3, data.length()).trim();
        }
    }

    @Override
    public Label getNode(){
        this.inflate();
        node = new Label(nodeId, name);
        return ((Label)node);
    }
}

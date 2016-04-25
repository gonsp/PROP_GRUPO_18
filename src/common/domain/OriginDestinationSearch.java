package common.domain;

import java.util.ArrayList;

public class OriginDestinationSearch extends OriginSearch {

    private Node to;

    public OriginDestinationSearch(Graph graph, RelationStructure rs, Node from, Node to) {
        super(graph, rs, from);
        this.to = to;
    }

    @Override
    protected void generateResults(Matrix matrix) {
        super.generateResults(matrix);
        boolean found = false;
        for(int i = 0; !found && i < results.size(); ++i) {
            if(results.get(i).to.getId() == to.getId()) {
                Result aux = results.get(i);
                results = new ArrayList<Result>();
                results.add(aux);
                found = true;
            }
        }
    }
}

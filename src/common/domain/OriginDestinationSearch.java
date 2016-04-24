package common.domain;

public class OriginDestinationSearch extends OriginSearch {

    private Node to;

    public OriginDestinationSearch(Graph graph, RelationStructure rs, Node from, Node to) {
        super(graph, rs, from);
        this.to = to;
    }

    @Override
    protected void generateResults(Matrix matrix) {
        super.generateResults(matrix);
        for(int i = 0; i < results.size(); ++i) {
            if(results.get(i).to.getId() != to.getId()) {
                results.remove(i);
            }
        }
    }
}

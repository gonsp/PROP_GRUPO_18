package common.domain;

public class OriginDestinationSearch extends OriginSearch {

    private Node to;

    public OriginDestinationSearch(Graph graph, RelationStructure rs, Node from, Node to) {
        super(graph, rs, from);
        this.to = to;
    }

    @Override
    protected void generateResults(Matrix matrix) {

    }
}

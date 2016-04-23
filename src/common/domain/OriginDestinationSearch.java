package common.domain;

public class OriginDestinationSearch extends RelationalSearch {

    private Node from;
    private Node to;

    public OriginDestinationSearch(Graph graph, RelationStructure rs, Node from, Node to) {
        super(graph, rs);
        this.from = from;
        this.to = to;
    }
}

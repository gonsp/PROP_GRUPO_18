package common.domain;


public class OriginSearch extends RelationalSearch {

    private Node from;

    public OriginSearch(Graph graph, RelationStructure rs, Node from) {
        super(graph, rs);
        this.from = from;
    }
}

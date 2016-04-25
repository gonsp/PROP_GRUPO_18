package common.domain;

import java.util.Collections;


public class SimpleSearch extends GraphSearch {

    private String filter;
    private NodeType type;

    public SimpleSearch(Graph graph, NodeType type, String filter) {
        super(graph);
        this.filter = filter.toLowerCase();
        this.type = type;
    }

    @Override
    public void search() {
        Container<Node>.ContainerIterator iterator = graph.getNodeIterator(type);
        while(iterator.hasNext()) {
            Node aux = iterator.next();
            if(aux.getValue().toLowerCase().contains(filter)) {
                results.add(new Result(aux, null, 0));
            }
        }
        Collections.sort(results);
    }
}

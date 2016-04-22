package common.domain;

import java.util.ArrayList;


public class SimpleSearch {

    private ArrayList<Node> result;

    public SimpleSearch(Graph graph, NodeType nodeType, String filtro) throws GraphException {
        filtro = filtro.toLowerCase();
        result = new ArrayList<>();
        Container<Node>.ContainerIterator iterator = graph.getNodeIterator(nodeType);
        while(iterator.hasNext()) {
            Node aux = iterator.next().getValue();
            if(aux.getValue().toLowerCase().contains(filtro)) {
                result.add(aux);
            }
        }
    }

    public ArrayList<Node> getResult(){
        return result;
    }
}

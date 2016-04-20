package common.domain;

import java.util.ArrayList;

public class SimpleSearch {

    private ArrayList<Integer> result;

    public SimpleSearch(NodeContainer nodeContainer, String filtro) throws GraphException {

        result = new ArrayList<>();
        for (Integer i : nodeContainer.getKeySet()) {
            if (nodeContainer.getNode(i).getName().equalsIgnoreCase(filtro)) {
                result.add(i);
            }
        }
    }

    public SimpleSearch(Graph graph, NodeType nodeType, String filtro) throws GraphException {

        result = new ArrayList<>();
        for (Integer i : graph.getTypeKeySet(nodeType)) {
            if (graph.getNode(nodeType,i).getName().equalsIgnoreCase(filtro)) {
                result.add(i);
            }
        }
    }

    public ArrayList<Integer> getResult(){
        return result;
    }
}

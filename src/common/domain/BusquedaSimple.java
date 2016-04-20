package common.domain;

import java.util.ArrayList;

public class BusquedaSimple{

    private ArrayList<Integer> result;

    public BusquedaSimple(NodeContainer nodeContainer, String filtro) throws GraphException {
        result = new ArrayList<>();
        for (Integer i : nodeContainer.getKeySet()) {
            if (nodeContainer.getNode(i).getName().equalsIgnoreCase(filtro)) {
                result.add(i);
            }
        }
    }

    public BusquedaSimple(Graph graph, NodeType nodeType, String filtro) throws GraphException {
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

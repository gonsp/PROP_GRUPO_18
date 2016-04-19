package common.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BusquedaSimple {

    private HashMap<Integer,Node> hashMap;

    public BusquedaSimple(NodeContainer nodeContainer, String filtro) throws GraphException {
        //buscar(nodeContainer.getKeySet(),filtro);
    }

    public ArrayList<Integer> buscar(Set<Integer> keySet, String filtro){
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer i : keySet) {
            if (hashMap.get(i).getName().equals(filtro)) {
                result.add(i);
            }
        }
        return result;
    }
}

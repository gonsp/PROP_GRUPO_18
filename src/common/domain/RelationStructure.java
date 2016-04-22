package common.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class RelationStructure extends ArrayList<Relation> {

	public RelationStructure(NodeType from, ArrayList<Relation> r, NodeType to) throws RelationStructureException {
		super(r);
		if(size() == 0) {
			throw new RelationStructureException(RelationStructureError.EMPTY_STRUCTURE);
		}
        if(!setOrder(from, 0, to)) {
            throw new RelationStructureException(RelationStructureError.MALFORMED_STRUCTURE);
        }
	}

	public RelationStructure(NodeType from, Relation[] r, NodeType to) throws RelationStructureException {
		this(from, (ArrayList<Relation>)Arrays.asList(r), to);
	}

    public RelationStructure(Graph graph, NodeType from, int[] ids, NodeType to) throws RelationStructureException {
        this(from, getArrayList(graph, ids), to);
    }

    private boolean setOrder(NodeType from, int i, NodeType to) {
        if(i == size()) {
            return from == to;
        } else {
            Relation relation = get(i);
            if(from == relation.getNodeTypeA()) {
                return setOrder(relation.getNodeTypeB(), i+1, to);
            } else if(from == relation.getNodeTypeB()) {
                boolean aux = setOrder(relation.getNodeTypeB(), i+1, to);
                relation.setNodeTypeB(relation.getNodeTypeA());
                relation.setNodeTypeA(from);
                return aux;
            } else {
                return false;
            }
        }
    }

    private static ArrayList<Relation> getArrayList(Graph graph, int[] ids) throws RelationStructureException {
        ArrayList<Relation> relations = new ArrayList<Relation>();
        for(int i = 0; i < ids.length; ++i) {
            try {
                relations.add(graph.getRelation(ids[i]));
            } catch (GraphException e) {
                e.printStackTrace();
                throw new RelationStructureException(RelationStructureError.DOESNT_EXIST_RELATION);
            }
        }
        return relations;
    }
}

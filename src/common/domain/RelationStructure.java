package common.domain;

import java.util.ArrayList;

public class RelationStructure extends ArrayList<Relation> {

    private static ArrayList<Relation> cloneArrayList(ArrayList<Relation> r) {
        ArrayList<Relation> result = new ArrayList<Relation>();
        for(int i = 0; i < r.size(); ++i) {
            result.add(new Relation(r.get(i)));
        }
        return result;
    }

	public RelationStructure(NodeType from, ArrayList<Relation> r, NodeType to) throws RelationStructureException {
		super(cloneArrayList(r));
		if(size() == 0) {
			throw new RelationStructureException(RelationStructureException.Error.EMPTY_STRUCTURE);
		}
        if(!setOrder(from, 0, to)) {
            throw new RelationStructureException(RelationStructureException.Error.MALFORMED_STRUCTURE);
        }
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
                relation.setNodeTypeB(relation.getNodeTypeA());
                relation.setNodeTypeA(from);
                return setOrder(relation.getNodeTypeB(), i+1, to);
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
                throw new RelationStructureException(RelationStructureException.Error.DOESNT_EXIST_RELATION);
            }
        }
        return relations;
    }
}

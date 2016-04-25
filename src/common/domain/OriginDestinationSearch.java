package common.domain;

import java.util.Iterator;
import java.util.function.Predicate;

public class OriginDestinationSearch extends OriginSearch {

    private Node to;

    public OriginDestinationSearch(Graph graph, RelationStructure rs, Node from, Node to) {
        super(graph, rs, from);
        this.to = to;
    }

    protected class Pred implements Predicate<Result> {
        int nodeid;

        public Pred(int idfilter) {
            nodeid = idfilter;
        }
        
        public boolean test(Result test_res) {
            if(test_res.to.getId() != nodeid) return true;
            return false;
        }
    }

    @Override
    protected void generateResults(Matrix matrix) {
        super.generateResults(matrix);
        // Aquí la cagó Gon.
        Pred filter = new Pred(to.getId());
        results.removeIf(filter);
    }
}

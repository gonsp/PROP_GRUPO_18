package common.domain;

import java.util.ArrayList;

public class RelationalSearch {

    private Graph graph;
    private RelationStructure rs;
    private ArrayList<Result> results;

    public RelationalSearch(Graph graph, RelationStructure rs) {
        this.graph = graph;
        this.rs = rs;
    }

    private void i_search() {

    }

    public void search() {

    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public class Result {
        public Node from;
        public Node to;
        public double hetesim;

        Result(Node from, Node to, double hetesim) {
            this.from = from;
            this.to = to;
            this.hetesim = hetesim;
        }
    }
}

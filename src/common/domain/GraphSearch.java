package common.domain;

import java.util.ArrayList;

public abstract class GraphSearch {

    protected ArrayList<Result> results;
    protected Graph graph;
    protected boolean executed;

    GraphSearch(Graph graph) {
        this.graph = graph;
        results = new ArrayList<Result>();
        executed = false;
    }

    public abstract void search();

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

        public void print() {
            if(to == null) {
                System.out.println(from.getValue());
            } else {
                System.out.println(from.getValue() + " - " + to.getValue() + " - " + String.valueOf(hetesim));
            }
        }
    }
}

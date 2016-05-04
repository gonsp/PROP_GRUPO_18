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

    public class Result implements Comparable<Result> {
        public Node from;
        public Node to;
        public double hetesim;

        protected Result(Node from, Node to, double hetesim) {
            this.from = from;
            this.to = to;
            this.hetesim = hetesim;
        }

        public void print() {
            if(to == null) {
                System.out.println(from.getValue() + " - id: " + from.getId());
            } else {
                System.out.println(from.getValue() + " - " + to.getValue() + " - " + String.valueOf(hetesim));
            }
        }

        @Override
        public int compareTo(Result r2) {
            int comparison = this.from.getValue().compareTo(r2.from.getValue());
            if(comparison > 0) {
                return 1;
            } else if(comparison < 0) {
                return -1;
            } else {
                if(this.hetesim < r2.hetesim) {
                    return 1;
                } else if(this.hetesim > r2.hetesim) {
                    return -1;
                } else {
                    if(this.to == null || r2.to == null) {
                        return 0;
                    }
                    comparison = this.to.getValue().compareTo(r2.to.getValue());
                    if(comparison > 0) {
                        return 1;
                    } else if(comparison < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }
}

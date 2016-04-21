package common.domain.graph;


public class GraphException extends Exception {

    private GraphExceptionError error;

    public GraphException(GraphExceptionError error) {
        super();
        this.error = error;
    }

    public GraphExceptionError getError() {
        return error;
    }
}



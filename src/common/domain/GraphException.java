package domain;

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

public enum GraphExceptionError {
    ID_USED, ID_INVALID, ID_NOT_EXISTS
}




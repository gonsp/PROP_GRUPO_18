package common.domain.graph;


public class GraphException extends Exception {

    private GraphExceptionError error;

    public GraphException(GraphExceptionError error) {
        super();
        this.error = error;
        Exception e;
    }

    public GraphExceptionError getError() {
        return error;
    }

    @Override
    public void printStackTrace() {
        if(error == GraphExceptionError.ID_INVALID) {
            System.out.print("ID invalid. Maybe last id is greater?");
        } else if(error == GraphExceptionError.ID_EQUAL) {
            System.out.print("You cannot connect a node to itself");
        } else if(error == GraphExceptionError.ID_NONEXISTENT) {
            System.out.print("ID not exists. Element doesn't exist with this id");
        } else if(error == GraphExceptionError.TYPE_INVALID) {
            System.out.print("This type doesn't exists");
        } else if(error == GraphExceptionError.ID_USED) {
            System.out.print("ID used");
        }
    }
}



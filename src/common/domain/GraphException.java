package common.domain;


public class GraphException extends Exception {

    private GraphExceptionError error;

    public GraphException(GraphExceptionError error) {
        super();
        this.error = error;
    }

    public GraphExceptionError getError() {
        return error;
    }

    @Override
    public void printStackTrace() {
        if(error == GraphExceptionError.ID_INVALID) {
            System.out.println("ID invalid. Maybe last id is greater?");
        } else if(error == GraphExceptionError.ID_EQUAL) {
            System.out.println("You cannot connect a node to itself");
        } else if(error == GraphExceptionError.ID_NONEXISTENT) {
            System.out.println("ID not exists. Element doesn't exist with this id");
        } else if(error == GraphExceptionError.TYPE_INVALID) {
            System.out.println("This type doesn't exists");
        } else if(error == GraphExceptionError.ID_USED) {
            System.out.println("ID used");
        }
    }
}



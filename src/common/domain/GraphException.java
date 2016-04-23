package common.domain;


public class GraphException extends Exception {

    private Error error;

    public GraphException(Error error) {
        super();
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    @Override
    public void printStackTrace() {
        if(error == Error.ID_INVALID) {
            System.out.println("ID invalid. Maybe last id is greater?");
        } else if(error == Error.ID_EQUAL) {
            System.out.println("You cannot connect a node to itself");
        } else if(error == Error.ID_NONEXISTENT) {
            System.out.println("ID not exists. Element doesn't exist with this id");
        } else if(error == Error.TYPE_INVALID) {
            System.out.println("This type doesn't exists");
        } else if(error == Error.ID_USED) {
            System.out.println("ID used");
        } else if(error == Error.ID_RELATION_NONEXISTENT) {
            System.out.println("relationID doesn't exist");
        } else if(error == Error.RELATION_DEFAULT) {
            System.out.println("A default relation type cannot be deleted!");
        }
    }

    public enum Error {
        ID_INVALID, ID_USED, ID_NONEXISTENT, TYPE_INVALID, ID_EQUAL, ID_RELATION_NONEXISTENT, RELATION_DEFAULT;
    }
}



package common.domain;


public class RelationStructureException extends Exception{

    private Error error;

    public RelationStructureException(Error error) {
        super();
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    @Override
    public void printStackTrace() {
        if(error == Error.MALFORMED_STRUCTURE) {
            System.out.println("The structure must be concatenated. It means that the nodeTypeB of relation i must be the same that nodeTypeA of relation i+1");
        } else if(error == Error.DOESNT_EXIST_RELATION) {
            System.out.println("Some relation id doesn't exist");
        } else if(error == Error.EMPTY_STRUCTURE) {
            System.out.println("The structure cannot be empty");
        }
    }

    public enum Error {
        MALFORMED_STRUCTURE, DOESNT_EXIST_RELATION, EMPTY_STRUCTURE
    }
}

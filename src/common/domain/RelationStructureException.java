package common.domain;


public class RelationStructureException extends Exception{

    private RelationStructureError error;

    public RelationStructureException(RelationStructureError error) {
        super();
        this.error = error;
    }

    public RelationStructureError getError() {
        return error;
    }

    @Override
    public void printStackTrace() {
        if(error == RelationStructureError.MALFORMED_STRUCTURE) {
            System.out.println("The structure must be concatenated. It means that the nodeTypeB of relation i must be the same that nodeTypeA of relation i+1");
        } else if(error == RelationStructureError.DOESNT_EXIST_RELATION) {
            System.out.println("Some relation id doesn't exist");
        } else if(error == RelationStructureError.EMPTY_STRUCTURE) {
            System.out.println("The structure cannot be empty");
        }
    }
}

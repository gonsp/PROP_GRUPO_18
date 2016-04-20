package common.persistence;

import common.domain.Author;


public class AuthorSerializer extends NodeSerializer {

    public AuthorSerializer(Author node){
        super(node);
    }

    public AuthorSerializer(String data){
        super(data);
    }

    public Author getNode(){
        Author a = node;
        return a;
    }

}

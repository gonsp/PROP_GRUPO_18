package common.persistence;

import common.domain.graph.Author;


public class AuthorSerializer extends NodeSerializer {

    public AuthorSerializer(Author node){
        super(node);
    }

    public AuthorSerializer(String data){
        super(data);
    }

    @Override
    public Author getNode() {
        this.inflate();
        node = new Author(nodeId, name);
        return ((Author)node);
    }

}

package common;

import common.domain.Graph;
import common.persistence.PersistenceController;


public class Main {

    public static void main(String[] args) {

        PersistenceController pc = new PersistenceController();

        pc.importAuthor("data/test.txt");

    }

}

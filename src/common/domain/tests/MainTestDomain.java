package common.domain.tests;

import common.domain.*;
import java.util.Scanner;

public class MainTestDomain {
    public static void main (String[] args) throws GraphException {
        System.out.println("Select class to test:");
        System.out.println("1 - SimpleSearch");
        System.out.println("2 - RelationStructure");

        Scanner s = new Scanner(System.in);
        int testclass = s.nextInt();

        switch (testclass) {
            case 1:
                DriverSimpleSearch dss = new DriverSimpleSearch();
                dss.main();
                break;
            case 2:
                DriverRelationStructure drs = new DriverRelationStructure();
                drs.main();
                break;
            case 3:
                DriverRelationalSearch drsearch = new DriverRelationalSearch();
                drsearch.main();
                break;
        }

    }
}

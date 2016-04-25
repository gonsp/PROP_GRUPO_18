package common.domain.tests;

import common.domain.*;
import java.util.Scanner;

public class MainTestDomain {
    public static void main (String[] args) throws GraphException {
        System.out.println("Select class to test:");
        System.out.println("1 - SimpleSearch");
        System.out.println("2 - RelationStructure");
        System.out.println("3 - FreeSearch");
        System.out.println("4 - OriginSearch");
        System.out.println("5 - OriginDestinationSearch");
        System.out.println("6 - Matrix");

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
                DriverFreeSearch dfs = new DriverFreeSearch();
                dfs.main();
                break;
            case 4:
                DriverOriginSearch dos = new DriverOriginSearch();
                dos.main();
                break;
            case 5:
                DriverOriginDestinationSearch dods = new DriverOriginDestinationSearch();
                dods.main();
                break;
            case 6:
                DriverMatrix dm = new DriverMatrix();
                dm.main();
                break;
        }

    }
}

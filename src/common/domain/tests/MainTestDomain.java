package common.domain.tests;

import common.domain.*;

public class MainTestDomain {
    public static void main (String[] args) {
        DriverSimpleSearch dss = new DriverSimpleSearch();
        System.out.println("Select class to test:");
        System.out.println("1 - SimpleSearch");
        try {
            dss.main();
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }
}

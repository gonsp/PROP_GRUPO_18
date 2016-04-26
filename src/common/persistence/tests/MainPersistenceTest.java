package common.persistence.tests;

import common.domain.GraphException;

import java.util.Scanner;


public class MainPersistenceTest {

    public static void main(String[] args) throws GraphException {
        int menu = 0;
        while (menu != 5) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("CHOOSE A CLASS TO TEST:");
            System.out.println("1. Edge Serializer");
            System.out.println("2. Label Serializer");
            System.out.println("3. Node Serializer");
            System.out.println("4. Persistence Controller");
            System.out.println("5. Exit");
            menu = keyboard.nextInt();
            switch (menu) {
                case 1:
                    DriverEdgeSerializer de = new DriverEdgeSerializer();
                    de.main();
                    break;
                case 2:
                    DriverLabelSerializer dl = new DriverLabelSerializer();
                    dl.main();
                    break;
                case 3:
                    DriverNodeSerializer dn = new DriverNodeSerializer();
                    dn.main();
                    break;
                case 4:
                    DriverPersistenceController dp = new DriverPersistenceController();
                    dp.main();
                    break;
            }
        }
    }
}

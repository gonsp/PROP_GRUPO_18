package common.persistence.tests;

import common.persistence.NodeSerializer;
import java.util.Scanner;


public class DriverNodeSerializer {

    NodeSerializer serializer = null;

    public void testConstructor1() {
        System.out.println("Write a String (id, value)");
        Scanner keyboard = new Scanner(System.in);
        String data = keyboard.nextLine();
        serializer = new NodeSerializer(data);
    }

    public void testGetData(){
        System.out.println(serializer.getData());
    }

    public void testGetId() {
        System.out.println(serializer.getId());
    }

    public void testGetName() {
        System.out.println(serializer.getName());
    }

    public void main(){
        int menu = 0;
        while (menu != 3) {
            System.out.println("CHOOSE A METHOD TO TEST:");
            System.out.println("1. NodeSerializer(String data) + int getData()");
            System.out.println("2. int getId() + String getName()");
            System.out.println("3. Return Main Menu");
            Scanner keyboard = new Scanner(System.in);
            menu = keyboard.nextInt();
            switch (menu) {
                case 1:
                    this.testConstructor1();
                    this.testGetData();
                    break;
                case 2:
                    this.testConstructor1();
                    this.testGetId();
                    this.testGetName();
                    break;
            }
            System.out.println(" ");
        }
    }
}

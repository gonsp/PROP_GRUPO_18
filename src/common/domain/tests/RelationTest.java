/*package common.domain.tests;

import common.domain.*;

import java.io.IOException;
import java.util.Scanner;


public class RelationTest {

    private static Relation relation;


    private static void printMenu() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("0. Menu.");
        System.out.println("1. Create Relation 1.");
        System.out.println("2. Create Relation 2.");
        System.out.println("3. Get name.");
        System.out.println("4. Is this a default relation?");
        System.out.println("5. Get type of node A.");
        System.out.println("6. Set type of node A.");
        System.out.println("7. Get type of node B.");
        System.out.println("8. Set type of node B.");


        System.out.println("Escull un número (0-9) per realitzar l'acció desitjada.");
        System.out.println("-----------------------------------------------------------");
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Integer n = new Integer(0);

        printMenu();
        while (n != 8) {
            n = sc.nextInt();
            NodeType A = null;
            NodeType B = null;
            String name = new String();
            Integer id;

            switch (n) {
                //Consulta Menu
                case 0:
                    printMenu();
                    break;
                //Crear relacio tipus 1
                case 1:
                    System.out.println("Enter the type of node A:");
                    System.out.println("(0)Author, (1)Paper, (2)Conference, (3)Term, (4)Label");


                    switch (sc.nextInt()) {
                        case 0:
                            A = NodeType.AUTHOR;
                            break;
                        case 1:
                            A = NodeType.PAPER;
                            break;
                        case 2:
                            A = NodeType.CONF;
                            break;
                        case 3:
                            A = NodeType.TERM;
                            break;
                        case 4:
                            A = NodeType.LABEL;
                            break;
                        default:
                            System.out.println("You must enter a number from 0 to 4!");
                            break;
                    }

                    System.out.println("Enter the type of node B:");
                    System.out.println("(0)Author, (1)Paper, (2)Conference, (3)Term, (4)Label");


                    switch (sc.nextInt()) {
                        case 0:
                            B = NodeType.AUTHOR;
                            break;
                        case 1:
                            B = NodeType.PAPER;
                            break;
                        case 2:
                            B = NodeType.CONF;
                            break;
                        case 3:
                            B = NodeType.TERM;
                            break;
                        case 4:
                            B = NodeType.LABEL;
                            break;
                        default:
                            System.out.println("You must enter a number from 0 to 4!");
                            break;
                    }

                    System.out.println("Enter the name of the relation:");
                    name = sc.next();

                    relation = new Relation(A, B, name);
                    System.out.println("Relation created");
                    System.out.println("-----------------------------------------------------------");
                    break;
                //crear relacio tipus 2
                case 2:
                    System.out.println("Enter the type of node A:");
                    System.out.println("(0)Author, (1)Paper, (2)Conference, (3)Term, (4)Label");


                    switch (sc.nextInt()) {
                        case 0:
                            A = NodeType.AUTHOR;
                            break;
                        case 1:
                            A = NodeType.PAPER;
                            break;
                        case 2:
                            A = NodeType.CONF;
                            break;
                        case 3:
                            A = NodeType.TERM;
                            break;
                        case 4:
                            A = NodeType.LABEL;
                            break;
                        default:
                            System.out.println("You must enter a number from 0 to 4!");
                            break;
                    }

                    System.out.println("Enter the type of node B:");
                    System.out.println("(0)Author, (1)Paper, (2)Conference, (3)Term, (4)Label");


                    switch (sc.nextInt()) {
                        case 0:
                            B = NodeType.AUTHOR;
                            break;
                        case 1:
                            B = NodeType.PAPER;
                            break;
                        case 2:
                            B = NodeType.CONF;
                            break;
                        case 3:
                            B = NodeType.TERM;
                            break;
                        case 4:
                            B = NodeType.LABEL;
                            break;
                        default:
                            System.out.println("You must enter a number from 0 to 4!");
                            break;
                    }

                    System.out.println("Enter the name of the relation:");
                    name = sc.next();

                    System.out.println("Enter the id of the relation:");
                    id = sc.nextInt();

                    relation = new Relation(A, B, name, id);
                    System.out.println("Relation created");
                    System.out.println("-----------------------------------------------------------");
                    break;
                //obtenir nom de la relacio
                case 3:
                    System.out.println("Do you want to get the name of the relation?");
                    System.out.println("(0)Yes, (1)No");
                    switch (sc.nextInt()) {
                        case 0:
                            try {
                                System.out.println("The name of the relation is " + relation.getName() + ".");
                            } catch (NullPointerException e1) {
                                System.out.println("You need to create a relation to get its name");
                            }
                            break;
                        case 1:
                            System.out.println("Nothing happened");
                            break;
                        default:
                            System.out.println("You must enter 0 or 1");
                            break;
                    }
                    System.out.println("-----------------------------------------------------------");
                    break;
                case 4:
                    System.out.println("Do you want to know if this is a default relation?");
                    System.out.println("(0)Yes, (1)No");

                    switch (sc.nextInt()) {
                        case 0:
                            try {
                                if (relation.isDefault()) System.out.println("This is a default relation");
                                else System.out.println("This is not a default relation");
                            } catch (NullPointerException e1) {
                                System.out.println("You need to create a relation to know if it's a default relation");
                            }
                            break;
                        case 1:
                            System.out.println("Nothing happened");
                            break;
                        default:
                            System.out.println("You must enter 0 or 1");
                            break;
                    }
                    System.out.println("-----------------------------------------------------------");
                    break;
                //obtener el tipo de nodo de A
                case 5:
                    System.out.println("Do you want to know the type of the node A?");
                    System.out.println("(0)Yes, (1)No");

                    switch (sc.nextInt()) {
                        case 0:
                            try {
                                System.out.println("The node A is type: " + relation.getNodeTypeA());
                            } catch (NullPointerException e1) {
                                System.out.println("You need to create a relation");
                            }
                            break;
                        case 1:
                            System.out.println("Nothing happened");
                            break;
                        default:
                            System.out.println("You must enter 0 or 1");
                            break;
                    }
                    System.out.println("-----------------------------------------------------------");
                    break;
                //editar el tipo de nodo de A
                case 6:
                    System.out.println("Enter the type that you want to set to node A:");
                    System.out.println("(0)Author, (1)Paper, (2)Conference, (3)Term, (4)Label");


                    switch (sc.nextInt()) {
                        case 0:
                            relation.setNodeTypeA(NodeType.AUTHOR);
                            break;
                        case 1:
                            relation.setNodeTypeA(NodeType.PAPER);
                            break;
                        case 2:
                            relation.setNodeTypeA(NodeType.CONF);
                            break;
                        case 3:
                            relation.setNodeTypeA(NodeType.TERM);
                            break;
                        case 4:
                            relation.setNodeTypeA(NodeType.LABEL);
                            break;
                        default:
                            System.out.println("You must enter a number from 0 to 4!");
                            break;
                    }
                    System.out.println("Type changed");
                    System.out.println("-----------------------------------------------------------");
                    break;

                //obtener el tipo de nodo de B
                case 7:
                    System.out.println("Do you want to know the type of the node B?");
                    System.out.println("(0)Yes, (1)No");

                    switch (sc.nextInt()) {
                        case 0:
                            try {
                                System.out.println("The node B is type: " + relation.getNodeTypeB());
                            } catch (NullPointerException e1) {
                                System.out.println("You need to create a relation");
                            }
                            break;
                        case 1:
                            System.out.println("Nothing happened");
                            break;
                        default:
                            System.out.println("You must enter 0 or 1");
                            break;
                    }
                    System.out.println("-----------------------------------------------------------");
                    break;
                //editar el tipo de nodo de B
                case 8:
                    System.out.println("Enter the type that you want to set to node B:");
                    System.out.println("(0)Author, (1)Paper, (2)Conference, (3)Term, (4)Label");


                    switch (sc.nextInt()) {
                        case 0:
                            relation.setNodeTypeB(NodeType.AUTHOR);
                            break;
                        case 1:
                            relation.setNodeTypeB(NodeType.PAPER);
                            break;
                        case 2:
                            relation.setNodeTypeB(NodeType.CONF);
                            break;
                        case 3:
                            relation.setNodeTypeB(NodeType.TERM);
                            break;
                        case 4:
                            relation.setNodeTypeB(NodeType.LABEL);
                            break;
                        default:
                            System.out.println("You must enter a number from 0 to 4!");
                            break;
                    }
                    System.out.println("Type changed");
                    System.out.println("-----------------------------------------------------------");
                    break;

            }
        }
    }
}

  
*/
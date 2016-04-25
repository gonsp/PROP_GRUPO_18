package common.domain.tests;

import common.domain.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverFreeSearch extends DriverRelationalSearch {

    public DriverFreeSearch() {
        super();
    }

    public void main() {
        System.out.println("FreeSearch test. The data is extracted from the default graph files.");
        System.out.println("Please insert a VALID Relation Structure or quit:");

        Scanner s = new Scanner(System.in);
        String parse = s.next();

        while(!parse.equals("quit") && parse.length() >= 2) {
            relationList.clear();
            System.out.println("Searching full database... (May take a few seconds)");
            try {
                this.parseRelation(parse);
                RelationStructure rs = new RelationStructure(from, relationList, to);
                FreeSearch fs = new FreeSearch(g, rs);
                fs.search();
                System.out.println("Search done. Printing results");
                ArrayList<GraphSearch.Result> results = fs.getResults();
                for(int i = 0; i < results.size(); ++i) {
                    results.get(i).print();
                }
            } catch (RelationStructureException rse) {
                System.out.println("Invalid Relation Structure");
            } catch (GraphException ge) {
                ge.printStackTrace();
                System.out.println("Invalid Relation Structure");
            }
            System.out.println("Please insert a VALID Relation Structure or quit:");
            parse = s.next();
        }

    }

}

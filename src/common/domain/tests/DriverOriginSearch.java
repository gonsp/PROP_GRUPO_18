package common.domain.tests;

import common.domain.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverOriginSearch extends DriverRelationalSearch {

    public DriverOriginSearch() {
        super();
    }

    public void main() {
        System.out.println("FreeSearch test. The data is extracted from the default graph files.");
        System.out.println("Please insert a VALID Relation Structure or quit:");

        Scanner s = new Scanner(System.in);
        String parse = s.next();

        while(!parse.equals("quit") && parse.length() >= 2) {
            relationList.clear();
            System.out.println("Please insert the origin NodeId: ");
            int nodeid = s.nextInt();
            try {
                this.parseRelation(parse);
                RelationStructure rs = new RelationStructure(from, relationList, to);
                OriginSearch os = new OriginSearch(g, rs, g.getNode(from, nodeid));
                os.search();
                System.out.println("Search done. Printing results");
                ArrayList<GraphSearch.Result> results = os.getResults();
                for(int i = 0; i < results.size(); ++i) {
                    results.get(i).print();
                }
            } catch (RelationStructureException rse) {
                System.out.println("Invalid Relation Structure");
            } catch (GraphException ge) {
                System.out.println("GraphException. Probably incorrect origin node id.");
            }
            System.out.println("Please insert a VALID Relation Structure or quit:");
            parse = s.next();
        }

    }

}

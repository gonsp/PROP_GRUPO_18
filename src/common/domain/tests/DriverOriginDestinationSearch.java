package common.domain.tests;

import common.domain.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverOriginDestinationSearch extends DriverRelationalSearch {

    public DriverOriginDestinationSearch() {
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
            int origin = s.nextInt();
            System.out.println("Please insert the destination NodeId: ");
            int destination = s.nextInt();
            try {
                this.parseRelation(parse);
                RelationStructure rs = new RelationStructure(from, relationList, to);
                OriginDestinationSearch ods = new OriginDestinationSearch(g, rs, g.getNode(from, origin), g.getNode(to, destination));
                ods.search();
                System.out.println("Search done. Printing results");
                ArrayList<GraphSearch.Result> results = ods.getResults();
                for(int i = 0; i < results.size(); ++i) {
                    results.get(i).print();
                }
            } catch (RelationStructureException rse) {
                System.out.println("Invalid Relation Structure");
            } catch (GraphException ge) {
                System.out.println("GraphException. Probably incorrect origin or destination node id.");
            }
            System.out.println("Please insert a VALID Relation Structure or quit:");
            parse = s.next();
        }

    }

}

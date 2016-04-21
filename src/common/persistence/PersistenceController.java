package common.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.domain.Graph;
import common.domain.GraphException;
import common.domain.Node;

public class PersistenceController {

    private List<String> readFile(String path) {
        List<String> toReturn = new ArrayList<>();
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String filePath = new File(path).getAbsolutePath();
            FileReader fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            while ((sCurrentLine = br.readLine()) != null) {
                toReturn.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return toReturn;
    }

    public void importNodes(Graph g, String path, String ntype) throws GraphException {
        List<String> strings = readFile(path);
        for (String s : strings) {
            NodeSerializer serializer = null;
            switch (ntype) {
                case "Author":
                    serializer = new AuthorSerializer(s);
                    break;
                case "Conference":
                    serializer = new ConferenceSerializer(s);
                    break;
                case "Paper":
                    serializer = new PaperSerializer(s);
                    break;
                case "Term":
                    serializer = new TermSerializer(s);
                    break;
            }
            Node node = serializer.getNode();
            //g.addNode(node, serializer.getId());
        }
    }

    public void importEdges(Graph g, String path, String ntype1, String ntype2) throws GraphException {
        List<String> strings = readFile(path);
        for (String s : strings) {
            String etype = null;
            if (ntype1.equals("Author") && ntype2.equals("Label")) {
                etype = "AuthorLabel";
            } else if (ntype1.equals("Conference") && ntype2.equals("Label")){
                etype = "ConferenceLabel";
            } // so on...
            EdgeSerializer serializer = new EdgeSerializer(s, etype);
        }
    }

}
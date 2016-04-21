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
import common.domain.NodeType;


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

    public void importNodes(Graph g, String path, NodeType type) throws GraphException {
        List<String> strings = readFile(path);
        for (String s : strings) {
            NodeSerializer serializer = null;
            switch (type) {
                case AUTHOR:
                    serializer = new AuthorSerializer(s);
                    break;
                case CONFERENCE:
                    serializer = new ConferenceSerializer(s);
                    break;
                case PAPER:
                    serializer = new PaperSerializer(s);
                    break;
                case TERM:
                    serializer = new TermSerializer(s);
                    break;
            }
            Node node = serializer.getNode();
            //g.addNode(node, serializer.getId());
        }
    }

    public void importEdges(Graph g, String path, NodeType type1, NodeType type2) throws GraphException {
        List<String> strings = readFile(path);
        for (String s : strings) {
            String etype = null;
            if (type1.equals(NodeType.AUTHOR) && type2.equals(NodeType.LABEL)) {
                etype = "AuthorLabel";
            } else if (type1.equals(NodeType.CONFERENCE) && type2.equals(NodeType.LABEL)) {
                etype = "ConferenceLabel";
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.AUTHOR)) {
                etype = "PaperAuthor";
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.CONFERENCE)) {
                etype = "PaperConference";
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.LABEL)) {
                etype = "PaperLabel";
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.TERM)) {
                etype = "PaperTerm";
            }
            EdgeSerializer serializer = new EdgeSerializer(g, s, etype, type1, type2);
            Node node1 = serializer.getNode1();
            Node node2 = serializer.getNode2();
            //g.addRelation()
        }
    }

}
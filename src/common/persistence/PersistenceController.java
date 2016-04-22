package common.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import common.domain.graph.Graph;
import common.domain.graph.GraphException;
import common.domain.graph.Node;
import common.domain.graph.NodeType;


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

    private void writeFile(String path){
        PrintWriter writer = null;
        try {
            String filePath = new File(path).getAbsolutePath();
            writer = new PrintWriter(filePath, "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
                case LABEL:
                    serializer = new LabelSerializer(s);
                    break;
                case PAPER:
                    serializer = new PaperSerializer(s);
                    break;
                case TERM:
                    serializer = new TermSerializer(s);
                    break;
            }
            Node node = serializer.getNode();
            g.addNode(node, serializer.getId());
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

    public void exportNodes(Graph g, String path){

    }

    public void exportEdges(Graph g, String path){

    }

    public void exportGraph(Graph g, String path){
        exportNodes(g, path);
        exportEdges(g, path);
    }

}
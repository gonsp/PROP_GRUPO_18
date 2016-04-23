package common.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import common.domain.*;


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

    private void writeFile(String path, String data) {
        try {
            String absolutePath = new File(path).getAbsolutePath();
            File file = new File(absolutePath);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
            //g.addEdge(¿¿RELATIONID??,node1,node2);
        }
    }

    public void exportNodes(Graph g, String path) {
        for (NodeType n : NodeType.values()) {
            Container<Node>.ContainerIterator iterator = g.getNodeIterator(n);
            while (iterator.hasNext()) {
                NodeSerializer serializer = null;
                String filename = null;
                switch (n) {
                    case AUTHOR:
                        Author aut = (Author) iterator.next().getValue();
                        serializer = new AuthorSerializer(aut);
                        filename = "author";
                        break;
                    case CONFERENCE:
                        Conference con = (Conference) iterator.next().getValue();
                        serializer = new ConferenceSerializer(con);
                        filename = "conference";
                        break;
                    case LABEL:
                        Label lab = (Label) iterator.next().getValue();
                        serializer = new LabelSerializer(lab);
                        filename = "label";
                        break;
                    case PAPER:
                        Paper pap = (Paper) iterator.next().getValue();
                        serializer = new PaperSerializer(pap);
                        filename = "paper";
                        break;
                    case TERM:
                        Term ter = (Term) iterator.next().getValue();
                        serializer = new TermSerializer(ter);
                        filename = "term";
                        break;
                }
                String d = serializer.getData();
                String filepath = path + filename + ".txt";
                writeFile(filepath, d);
            }
        }
    }

    public void exportEdges(Graph g, String path) {

    }

    public void exportGraph(Graph g, String path) {
        exportNodes(g, path);
        exportEdges(g, path);
    }

}
package common.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import common.domain.*;


public class PersistenceController {

    private Graph graph;

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
            if (!file.exists()) {
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

    public PersistenceController(Graph graph){
        this.graph = graph;
    }

    public void importNodes(String path, NodeType type) throws GraphException {
        List<String> strings = readFile(path);
        for (String s : strings) {
            switch (type) {
                case AUTHOR:
                    AuthorSerializer as = new AuthorSerializer(s);
                    Author author = (Author) graph.createNode(type, as.getId(), as.getName());
                    graph.addNode(author);
                    break;
                case CONFERENCE:
                    ConferenceSerializer cs = new ConferenceSerializer(s);
                    Conference conference = (Conference) graph.createNode(type, cs.getId(), cs.getName());
                    graph.addNode(conference);
                    break;
                case PAPER:
                    PaperSerializer ps = new PaperSerializer(s);
                    Paper paper = (Paper) graph.createNode(type, ps.getId(), ps.getName());
                    graph.addNode(paper);
                    break;
                case TERM:
                    TermSerializer ts = new TermSerializer(s);
                    Term term = (Term) graph.createNode(type, ts.getId(), ts.getName());
                    graph.addNode(term);
                    break;
            }
        }
    }

    public void importEdges(String path, NodeType type1, NodeType type2) throws GraphException {
        List<String> strings = readFile(path);
        for (String s : strings) {
            int relId = -1;
            EdgeSerializer serializer = null;
            if (type1.equals(NodeType.AUTHOR) && type2.equals(NodeType.LABEL)) {
                relId = 4;
                serializer = new LabelSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.CONFERENCE) && type2.equals(NodeType.LABEL)) {
                relId = 6;
                serializer = new LabelSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.AUTHOR)) {
                relId = 1;
                serializer = new EdgeSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.CONFERENCE)) {
                relId = 2;
                serializer = new EdgeSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.LABEL)) {
                relId = 5;
                serializer = new LabelSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.TERM)) {
                relId = 3;
                serializer = new EdgeSerializer(graph, s, type1, type2);
            }
            Node node1 = serializer.getNode1();
            Node node2 = serializer.getNode2();
            graph.addEdge(relId, node1, node2);
        }
    }

    public void exportNodes(String path) {
        for (NodeType n : NodeType.values()) {
            Container<Node>.ContainerIterator iterator = graph.getNodeIterator(n);
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

    public void exportEdges(String path) {

    }

    public void exportGraph(String path) {
        exportNodes(path);
        exportEdges(path);
    }

}
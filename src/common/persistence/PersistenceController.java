package common.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import common.domain.*;

import static common.domain.NodeType.*;


public class PersistenceController {

    private Graph graph;

    private List<String> readFile(String path) {
        List<String> toReturn = new ArrayList<>();
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String absolutePath = new File(path).getAbsolutePath();
            FileReader fr = new FileReader(absolutePath);
            br = new BufferedReader(fr);
            while ((sCurrentLine = br.readLine()) != null) {
                toReturn.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    private void writeFile(String path, List<String> strings) {
        try {
            String absolutePath = new File(path).getAbsolutePath();
            File file = new File(absolutePath);
            file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            for (String s : strings) {
                out.println(s);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearDir(String path) {
        String absolutePath = new File(path).getAbsolutePath();
        File folder = new File(absolutePath);
        if(!folder.exists()){
            folder.mkdir();
        }
        File flist[] = folder.listFiles();
        for (int i = 0; i < flist.length; i++) {
            String pes = flist[i].getName();
            if (pes.endsWith(".txt")) {
                flist[i].delete();
            }
        }
    }

    private void exportNodes(String path) {
        for (NodeType n : NodeType.values()) {
            if (n != LABEL) {
                List<String> strings = new ArrayList<>();
                Container<Node>.ContainerIterator it = graph.getNodeIterator(n);
                while (it.hasNext()) {
                    Node node = it.next();
                    NodeSerializer serializer = new NodeSerializer(node);
                    strings.add(serializer.getData());
                }
                String filepath = path + n.toString().toLowerCase() + ".txt";
                writeFile(filepath, strings);
            }
        }
    }

    private void exportEdges(String path) throws GraphException {
        for (NodeType n : NodeType.values()) {
            if (n != LABEL && n != TERM) {
                List<String> strings;
                String filepath = null;
                Container<Node>.ContainerIterator it = graph.getNodeIterator(n);
                while (it.hasNext()) {
                    EdgeSerializer serializer = null;
                    ArrayList<Node> relation;
                    Node node1 = it.next();
                    if (n == AUTHOR) {
                        strings = new ArrayList<>();
                        relation = graph.getEdges(3, node1);
                        for (int i = 0; i < relation.size(); ++i) {
                            Node node2 = relation.get(i);
                            serializer = new LabelSerializer(node1, node2);
                            strings.add(serializer.getData());
                        }
                        filepath = path + "author_label" + ".txt";
                        writeFile(filepath, strings);
                    } else if (n == CONF) {
                        strings = new ArrayList<>();
                        relation = graph.getEdges(5, node1);
                        for (int i = 0; i < relation.size(); ++i) {
                            Node node2 = relation.get(i);
                            serializer = new LabelSerializer(node1, node2);
                            strings.add(serializer.getData());
                        }
                        filepath = path + "conf_label" + ".txt";
                        writeFile(filepath, strings);
                    } else if (n == PAPER) {
                        strings = new ArrayList<>();
                        relation = graph.getEdges(0, node1);
                        for (int i = 0; i < relation.size(); ++i) {
                            Node node2 = relation.get(i);
                            serializer = new EdgeSerializer(node1, node2);
                            strings.add(serializer.getData());
                        }
                        filepath = path + "paper_author" + ".txt";
                        writeFile(filepath, strings);
                        strings = new ArrayList<>();
                        relation = graph.getEdges(1, node1);
                        for (int i = 0; i < relation.size(); ++i) {
                            Node node2 = relation.get(i);
                            serializer = new EdgeSerializer(node1, node2);
                            strings.add(serializer.getData());
                        }
                        filepath = path + "paper_conf" + ".txt";
                        writeFile(filepath, strings);
                        strings = new ArrayList<>();
                        relation = graph.getEdges(4, node1);
                        for (int i = 0; i < relation.size(); ++i) {
                            Node node2 = relation.get(i);
                            serializer = new LabelSerializer(node1, node2);
                            strings.add(serializer.getData());
                        }
                        filepath = path + "paper_label" + ".txt";
                        writeFile(filepath, strings);
                        strings = new ArrayList<>();
                        relation = graph.getEdges(2, node1);
                        for (int i = 0; i < relation.size(); ++i) {
                            Node node2 = relation.get(i);
                            serializer = new EdgeSerializer(node1, node2);
                            strings.add(serializer.getData());
                        }
                        filepath = path + "paper_term" + ".txt";
                        writeFile(filepath, strings);
                    }
                }
            }
        }
    }

    public PersistenceController(Graph graph) {
        this.graph = graph;
        try {
            graph.addNode(graph.createNode(LABEL, 0, "Database"));
            graph.addNode(graph.createNode(LABEL, 1, "Data Mining"));
            graph.addNode(graph.createNode(LABEL, 2, "AI"));
            graph.addNode(graph.createNode(LABEL, 3, "Information Retreival"));
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    public void importNodes(String path, NodeType type) {
        List<String> strings = readFile(path);
        for (String s : strings) {
            NodeSerializer serializer = null;
            Node node = null;
            switch (type) {
                case AUTHOR:
                    serializer = new AuthorSerializer(s);
                    node = (Author) graph.createNode(type, serializer.getId(), serializer.getName());
                    break;
                case CONF:
                    serializer = new ConferenceSerializer(s);
                    node = (Conference) graph.createNode(type, serializer.getId(), serializer.getName());
                    break;
                case PAPER:
                    serializer = new PaperSerializer(s);
                    node = (Paper) graph.createNode(type, serializer.getId(), serializer.getName());
                    break;
                case TERM:
                    serializer = new TermSerializer(s);
                    node = (Term) graph.createNode(type, serializer.getId(), serializer.getName());
                    break;
            }
            try {
                graph.addNode(node, serializer.getId());
            } catch (GraphException e) {
                e.printStackTrace();
            }
        }
    }

    public void importEdges(String path, NodeType type1, NodeType type2) {
        List<String> strings = readFile(path);
        for (String s : strings) {
            int relId = -1;
            EdgeSerializer serializer = null;
            if (type1.equals(AUTHOR) && type2.equals(NodeType.LABEL)) {
                relId = 3;
                serializer = new LabelSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.CONF) && type2.equals(NodeType.LABEL)) {
                relId = 5;
                serializer = new LabelSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.PAPER) && type2.equals(AUTHOR)) {
                relId = 0;
                serializer = new EdgeSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.CONF)) {
                relId = 1;
                serializer = new EdgeSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.LABEL)) {
                relId = 4;
                serializer = new LabelSerializer(graph, s, type1, type2);
            } else if (type1.equals(NodeType.PAPER) && type2.equals(NodeType.TERM)) {
                relId = 2;
                serializer = new EdgeSerializer(graph, s, type1, type2);
            }
            try {
                Node node1 = serializer.getNode1();
                Node node2 = serializer.getNode2();
                graph.addEdge(relId, node1, node2);
            } catch (GraphException e) {
                e.printStackTrace();
            }
        }
    }

    public void exportGraph(String path) throws GraphException {
        clearDir(path);
        exportNodes(path);
        exportEdges(path);
    }

}
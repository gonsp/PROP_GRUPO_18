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
            String filePath = new File(path).getAbsolutePath();
            FileReader fr = new FileReader(filePath);
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
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            for (String s : strings) {
                out.println(s);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PersistenceController(Graph graph) {
        this.graph = graph;
        try {
            graph.addNode(graph.createNode(NodeType.LABEL, 0, "Database"));
            graph.addNode(graph.createNode(NodeType.LABEL, 1, "Data Mining"));
            graph.addNode(graph.createNode(NodeType.LABEL, 2, "AI"));
            graph.addNode(graph.createNode(NodeType.LABEL, 3, "Information Retreival"));
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

    public void exportNodes(String path) {
        for (NodeType n : NodeType.values()) {
            if (n != NodeType.LABEL) {
                List<String> strings = new ArrayList<>();
                Container<Node>.ContainerIterator it = graph.getNodeIterator(n);
                while (it.hasNext()) {
                    Node node = it.next().getValue();
                    NodeSerializer serializer = new NodeSerializer(node);
                    strings.add(serializer.getData());
                }
                String filepath = path + n.toString().toLowerCase() + ".txt";
                writeFile(filepath, strings);
            }
        }
    }

    public void exportEdges(String path) throws GraphException {
        for (NodeType n : NodeType.values()) {
            if (n != NodeType.LABEL) {
                List<String> strings = new ArrayList<>();
                String filename = null;
                Container<Node>.ContainerIterator it = graph.getNodeIterator(n);
                while (it.hasNext()) {
                    EdgeSerializer serializer = null;
                    ArrayList<Node> relation;
                    int size;
                    if (n == AUTHOR) {
                        Node node1 = it.next().getValue();
                        relation = graph.getEdges(3, node1);
                        size = relation.size();
                        if (size != 0) {
                            for (int i = 0; i < size; ++i) {
                                Node node2 = relation.get(i);
                                serializer = new EdgeSerializer(node1, node2, "author_label");
                                String d = serializer.getData();
                                strings.add(d);
                            }
                        }
                        filename = "author_label";
                        String filepath = path + filename + ".txt";
                        writeFile(filepath, strings);
                    } else if (n == CONF) {
                        Node node1 = it.next().getValue();
                        relation = graph.getEdges(5, node1);
                        size = relation.size();
                        if (size != 0) {
                            for (int i = 0; i < size; ++i) {
                                Node node2 = relation.get(i);
                                serializer = new EdgeSerializer(node1, node2, "conference_label");
                                String d = serializer.getData();
                                strings.add(d);
                            }
                        }
                        filename = "conference_label";
                        String filepath = path + filename + ".txt";
                        writeFile(filepath, strings);
                    } else if(n == TERM){

                    } else if (n == PAPER) {
                        Node node1 = it.next().getValue();
                        relation = graph.getEdges(0, node1);
                        size = relation.size();
                        if (size != 0) {
                            for (int i = 0; i < size; ++i) {
                                Node node2 = relation.get(i);
                                serializer = new EdgeSerializer(node1, node2, "paper_author");
                                filename = "paper_author";
                                String d = serializer.getData();
                                String filepath = path + filename + ".txt";
                                strings.add(d);
                            }
                        }
                        relation = graph.getEdges(1, node1);
                        size = relation.size();
                        if (size != 0) {
                            for (int i = 0; i < size; ++i) {
                                Node node2 = relation.get(i);
                                serializer = new EdgeSerializer(node1, node2, "paper_conference");
                                filename = "paper_conference";
                                String d = serializer.getData();
                                String filepath = path + filename + ".txt";
                                strings.add(d);
                            }
                        }
                        relation = graph.getEdges(4, node1);
                        size = relation.size();
                        if (size != 0) {
                            for (int i = 0; i < size; ++i) {
                                Node node2 = relation.get(i);
                                serializer = new EdgeSerializer(node1, node2, "paper_label");
                                filename = "paper_label";
                                String d = serializer.getData();

                                strings.add(d);
                            }
                        }
                        relation = graph.getEdges(2, node1);
                        size = relation.size();
                        if (size != 0) {
                            for (int i = 0; i < size; ++i) {
                                Node node2 = relation.get(i);
                                serializer = new EdgeSerializer(node1, node2, "paper_term");
                                filename = "paper_term";
                                String d = serializer.getData();
                                String filepath = path + filename + ".txt";
                                strings.add(d);
                            }
                        }
                    }
                }
                String filepath = path + filename + ".txt";
            }
        }
    }

    public void exportGraph(String path) throws GraphException {
        exportNodes(path);
        //exportEdges(path);
    }

}
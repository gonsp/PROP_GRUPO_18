package common.persistence;

import java.util.*;

import common.domain.*;

import static common.domain.NodeType.*;
import static common.persistence.FileHandler.*;


public class PersistenceController {

    private String TXT_REGEX = "(.*)_(.*)";
    private String DOUBLE_TXT_REGEX = "(.*)_(.*)_(.*)";
    private Graph graph;

    public PersistenceController(Graph graph) {
        this.graph = graph;
        try {
            // Adding implicit Label nodes
            graph.addNode(graph.createNode(LABEL, "Database"), 0);
            graph.addNode(graph.createNode(LABEL, "Data Mining"), 1);
            graph.addNode(graph.createNode(LABEL, "AI"), 2);
            graph.addNode(graph.createNode(LABEL, "Information Retreival"), 3);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    public void exportNode(String path, NodeType n){
        List<String> strings = new ArrayList<>();
        Container<Node>.ContainerIterator it = graph.getNodeIterator(n);
        while (it.hasNext()) {
            NodeSerializer serializer = new NodeSerializer(it.next());
            strings.add(serializer.getData());
        }
        writeFile(path, strings);
    }

    public void exportNodes(String path) {
        for (NodeType n : NodeType.values()) {
            if (n != LABEL) {
                String filepath = path + n.toString().toLowerCase() + ".txt";
                exportNode(filepath, n);
            }
        }
    }

    public void exportEdges(String path) {
        Map<String, ArrayList<String>> strings = new HashMap<String, ArrayList<String>>();

        Iterator iter = graph.getRelationIterator();
        while (iter.hasNext()) {
            Relation r = (Relation) iter.next();
            if (r.getName().matches(TXT_REGEX)) {
                strings.put(r.getName(), new ArrayList<>());
            } else {
                String s = r.getNodeTypeA().toString().toLowerCase() + "_" + r.getNodeTypeB().toString().toLowerCase() + "_" + r.getName();
                strings.put(s, new ArrayList<>());
            }
        }

        for (NodeType n : NodeType.values()) {
            Container<Node>.ContainerIterator it = graph.getNodeIterator(n);
            ArrayList<Relation> rs = graph.getRelationsForType(n);
            while (it.hasNext()) {
                Node node1 = it.next();
                for (Relation rel : rs) {
                    try {
                        ArrayList<Node> node_list = graph.getEdges(rel.getId(), node1);
                        for (int i = 0; i < node_list.size(); ++i) {
                            Node node2 = node_list.get(i);
                            EdgeSerializer serializer;
                            if (rel.containsLabel()) {
                                serializer = new LabelSerializer(node1, node2);
                            } else {
                                serializer = new EdgeSerializer(node1, node2);
                            }
                            if (rel.getName().matches(TXT_REGEX)) {
                                strings.get(rel.getName()).add(serializer.getData());
                            } else {
                                String s = rel.getNodeTypeA().toString().toLowerCase() + "_" + rel.getNodeTypeB().toString().toLowerCase() + "_" + rel.getName();
                                strings.get(s).add(serializer.getData());
                            }
                        }
                    } catch (GraphException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Iterator it = strings.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            writeFile(path + pair.getKey() + ".txt", (List<String>) pair.getValue());
        }

    }

    public void importNodes(String path) {
        for (NodeType n : NodeType.values()) {
            if (n != LABEL) {
                String filepath = path + n.toString().toLowerCase() + ".txt";
                List<String> strings = readFile(filepath);
                for (String s : strings) {
                    NodeSerializer serializer = new NodeSerializer(s);
                    Node node = graph.createNode(n, serializer.getName());
                    try {
                        graph.addNode(node, serializer.getId());
                    } catch (GraphException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void importEdges(String path) {
        List<String> files = readDir(path);
        for (String f : files) {
            String relation_name = null;
            Relation r = null;
            NodeType typeA = null;
            NodeType typeB = null;
            if (f.matches(DOUBLE_TXT_REGEX)) {
                String[] parts = f.split("_");
                typeA = NodeType.valueOf(parts[0].toUpperCase());
                typeB = NodeType.valueOf(parts[1].toUpperCase());
                relation_name = parts[2].substring(0, parts[2].length() - 4);
            } else if (f.matches(TXT_REGEX)) {
                String[] parts = f.split("_");
                typeA = NodeType.valueOf(parts[0].toUpperCase());
                typeB = NodeType.valueOf(parts[1].substring(0, parts[1].length() - 4).toUpperCase());
                relation_name = f.substring(0, f.length() - 4).toLowerCase();
            } else {
                continue;
            }
            r = graph.getOrCreateRelation(typeA, typeB, relation_name);
            List<String> strings = readFile(path + f);
            for (String s : strings) {
                EdgeSerializer serializer;
                if (r.containsLabel()) {
                    serializer = new LabelSerializer(graph, s, typeA, typeB);
                } else {
                    serializer = new EdgeSerializer(graph, s, typeA, typeB);
                }
                try {
                    graph.addEdge(r.getId(), serializer.getNode1(), serializer.getNode2());
                } catch (GraphException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void importGraph(String path) {
        path = handlePath(path);
        importNodes(path);
        importEdges(path);
    }

    public void exportGraph(String path) {
        path = handlePath(path);
        clearDir(path);
        exportNodes(path);
        exportEdges(path);
    }

}
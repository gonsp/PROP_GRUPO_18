package common.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import common.domain.Graph;
import common.domain.GraphException;
import common.domain.Node;

public class PersistenceController {

    public void importNode(Graph g, String path, String ntype) throws GraphException {

        BufferedReader br = null;

        try {

            String sCurrentLine;

            String filePath = new File(path).getAbsolutePath();
            FileReader fr = new FileReader(filePath);
            br = new BufferedReader(fr);

            while ((sCurrentLine = br.readLine()) != null) {
                NodeSerializer serializer = null;
                switch (ntype){
                    case "Author":
                        serializer = new AuthorSerializer(sCurrentLine);
                        break;
                    case "Conference":
                        serializer = new ConferenceSerializer(sCurrentLine);
                        break;
                    case "Paper":
                        serializer = new PaperSerializer(sCurrentLine);
                        break;
                    case "Term":
                        serializer = new TermSerializer(sCurrentLine);
                        break;
                }
                Node node = serializer.getNode();
                g.addNode(node, serializer.getId());
                System.out.println(sCurrentLine);
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

    }

}
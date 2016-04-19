package common.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import common.domain.Graph;

public class PersistenceController {

    public void importNode(Graph g, String path, String type) {

        BufferedReader br = null;

        try {

            String sCurrentLine;

            String filePath = new File(path).getAbsolutePath();
            FileReader fr = new FileReader(filePath);
            br = new BufferedReader(fr);

            while ((sCurrentLine = br.readLine()) != null) {
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
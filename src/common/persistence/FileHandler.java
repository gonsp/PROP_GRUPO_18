package common.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public final class FileHandler {

    protected static List<String> readFile(String path) {
        List<String> toReturn = new ArrayList<>();
        try {
            String absolutePath = new File(path).getAbsolutePath();
            FileReader fr = new FileReader(absolutePath);
            BufferedReader br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                toReturn.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    protected static void writeFile(String path, List<String> strings) {
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

    protected static List<String> readDir(String path) {
        List<String> toReturn = new ArrayList<>();
        String absolutePath = new File(path).getAbsolutePath();
        File folder = new File(absolutePath);
        if (folder.exists()) {
            File flist[] = folder.listFiles();
            for (int i = 0; i < flist.length; i++) {
                toReturn.add(flist[i].getName());
            }
        }
        return toReturn;
    }

    protected static void clearDir(String path) {
        String absolutePath = new File(path).getAbsolutePath();
        File folder = new File(absolutePath);
        if (!folder.exists()) {
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

}

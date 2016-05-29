package common.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public final class FileHandler {

    public static List<String> readFile(String path) {
        List<String> toReturn = new ArrayList<>();
        try {
            String absolutePath = new File(path).getAbsolutePath();
            FileReader fr = new FileReader(absolutePath);
            BufferedReader br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
                toReturn.add(sCurrentLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public static void writeFile(String path, List<String> strings, boolean append) {
        try {
            String absolutePath = new File(path).getAbsolutePath();
            File file = new File(absolutePath);
            file.createNewFile();
            FileWriter fw = new FileWriter(file, append);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            for (String s : strings)
                out.println(s);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readDir(String path) {
        List<String> toReturn = new ArrayList<>();
        String absolutePath = new File(path).getAbsolutePath();
        File folder = new File(absolutePath);
        if (folder.exists()) {
            File flist[] = folder.listFiles();
            for (File aFlist : flist) toReturn.add(aFlist.getName());
        }
        return toReturn;
    }

    public static void clearDir(String path) {
        String absolutePath = new File(path).getAbsolutePath();
        File folder = new File(absolutePath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File flist[] = folder.listFiles();
        for (File aFlist : flist) {
            String pes = aFlist.getName();
            if (pes.endsWith(".txt"))
                aFlist.delete();
        }
    }

    public static String handlePath(String path){
        int last = path.length() - 1;
        if(last > -1){
            if(!path.substring(last).equals("/"))
                return path + "/";
        } else {
            // TODO: Raise exception
        }
        return path;
    }

}

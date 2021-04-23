package Controller.DataBaseControllers;

import Controller.Utils;
import Model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataBaseController {

    public static void makeResourceDirectory() {

        File theDir = new File(getUsersPath());
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        theDir = new File(getDecksPath());
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    protected static String getUsersPath(){
        return "Resource\\Users";
    }

    protected static String getDecksPath(){
        return "Resource\\decks";
    }


    protected static String makeObjectJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    protected static String readDataFromFile(File file) {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            scanner.useDelimiter("\\Z");
            return scanner.next();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static String readDataFromFile(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }

    protected static void writeDataInfile(String data, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(data);
        fileWriter.close();
    }

    protected static File[] getFilesOfOneFolder(String path) {
        File folder = new File(path);
        return folder.listFiles();
    }

    public static void rewriteUser(User user){
        String path = Utils.getUserFileNameByUsername(user.getUsername());
        File file = new File(path);
        try {
            file.createNewFile();
            writeDataInfile(makeObjectJson(user), path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static boolean isThisFileExist(String path) {
        return Files.exists(new File(path).toPath());
    }
}

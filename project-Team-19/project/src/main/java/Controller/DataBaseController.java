package Controller;

import Model.*;
import View.Printer;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Paths;
import java.util.Scanner;


public class DataBaseController {

    public static void makeResourceDirectory() {

        File theDir = new File(Utils.getUsersPath());
        if (!theDir.exists()){
            theDir.mkdirs();
        }
    }

    public static void createUser(User user){

        String path = Utils.getUsersPath() + "\\" + user.getUsername() + ".json";

        if(!checkCreatingUserErrors(user, path)){
            return;
        }

        File file = new File(path);
        try {
            file.createNewFile();
            Printer.printSuccessfulRegister();

            writeDataInfile(makeUserJson(user), path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkCreatingUserErrors(User user, String userPath){

        if(isThisFileExist(userPath)){
            Printer.printRepetitiousUsername(user.getUsername());
            return false;
        }
        if(isNickNameRepetitious(user.getNickname())){
            Printer.printRepetitousNickName(user.getNickname());
            return false;
        }
        return true;
    }

    private static String readDataFromFile(File file) {
        Scanner sc;
        try {
            sc = new Scanner(file);
            sc.useDelimiter("\\Z");
            return sc.next();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    private static String readDataFromFile(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private static boolean isNickNameRepetitious(String nickname) {

        String data;

        for(File userData : getFilesOfOneFolder(Utils.getUsersPath())){
            data = readDataFromFile(userData);
            JsonObject jsonObjectAlt = JsonParser.parseString(data).getAsJsonObject();
            JsonElement nicknameJson = jsonObjectAlt.get("nickname");
            if(nicknameJson.toString().equals("\"" + nickname + "\"")){
                return true;
            }
        }
        return false;

    }

    private static String makeUserJson(User user) {

        Gson gson = new Gson();
        return gson.toJson(user);
    }

    private static void writeDataInfile(String data, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(data);
        fileWriter.close();
    }

    private static File [] getFilesOfOneFolder(String path){
        File folder = new File(path);
        return folder.listFiles();
    }

    private static boolean isThisFileExist(String path) {
        return Files.exists(new File(path).toPath());
    }



}

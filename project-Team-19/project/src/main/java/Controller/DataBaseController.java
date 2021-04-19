package Controller;

import Model.*;

import Model.Card.Card;
import Model.Enums.MonsterEnums.MonsterNames;
import Model.Enums.SpellsAndTraps.SpellAndTrapNames;
import View.Printer.RegisterPrinter;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class DataBaseController {

    public static void makeResourceDirectory() {

        File theDir = new File(Utils.getUsersPath());
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        theDir = new File(Utils.getDecksPath());
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    public static void createUser(User user) {

        String path = Utils.getUserFileNameByUsername(user.getUsername());

        if (!checkCreatingUserErrors(user, path)) {
            return;
        }

        File file = new File(path);
        try {
            file.createNewFile();
            RegisterPrinter.printSuccessfulRegister();

            writeDataInfile(makeObjectJson(user), path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkCreatingUserErrors(User user, String userPath) {

        if (isThisFileExist(userPath)) {
            RegisterPrinter.printRepetitiousUsername(user.getUsername());
            return false;
        }
        if (isNickNameRepetitious(user.getNickname())) {
            RegisterPrinter.printRepetitousNickName(user.getNickname());
            return false;
        }
        return true;
    }


    public static void createDeck(Deck deck) {
    }

    private static String getDeckFilePath() {
        return null;
    }

    public static int getLastDeckIndex() {
        return 1;
    }

    public static Card getMonster(MonsterNames monsterName) {
        return null;
    }

    public static Card getSpellAndTrap(SpellAndTrapNames spellAndTrapName) {
        return null;
    }


    private static String readDataFromFile(File file) {
        Scanner sc;
        try {
            sc = new Scanner(file);
            sc.useDelimiter("\\Z");
            return sc.next();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readDataFromFile(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private static boolean isNickNameRepetitious(String nickname) {

        String data;

        for (File userData : getFilesOfOneFolder(Utils.getUsersPath())) {
            data = readDataFromFile(userData);
            JsonObject jsonObjectAlt = JsonParser.parseString(data).getAsJsonObject();
            JsonElement nicknameJson = jsonObjectAlt.get("nickname");
            if (nicknameJson.toString().equals("\"" + nickname + "\"")) {
                return true;
            }
        }
        return false;

    }

    private static String makeObjectJson(Object obj) {

        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    private static void writeDataInfile(String data, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(data);
        fileWriter.close();
    }

    private static File[] getFilesOfOneFolder(String path) {
        File folder = new File(path);
        return folder.listFiles();
    }

    private static boolean isThisFileExist(String path) {
        return Files.exists(new File(path).toPath());
    }

    public static User getUserByUsername(String username) {
        return null;
    }

    private static Card getCardByGson(String Gson) {
        return null;
    }

    public static boolean userExists(String username) {
        String path = Utils.getUserFileNameByUsername(username);
        return isThisFileExist(path);
    }

    public static boolean userPasswordIsCorrect(String username, String password) {
        String path = Utils.getUserFileNameByUsername(username);
        User user = User.getUserByGson(path);
        return user.isPasswordCorrect(password);
    }
}

package Controller.DataBaseControllers;

import Controller.Utils;
import Model.User;
import View.Printer.RegisterProfilePrinter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserDataBaseController extends DataBaseController {

    public static String getUserFileNameByUsername(String username) {
        return getUsersPath() + "\\" + username + ".json";
    }

    public static void createUser(User user) {

        String path = getUserFileNameByUsername(user.getUsername());

        if (!checkCreatingUserErrors(user, path)) {
            return;
        }

        if(createFileByPathAndData(path, makeObjectJson(user)))
            RegisterProfilePrinter.printSuccessfulRegister();
    }


    public static boolean checkCreatingUserErrors(User user, String userPath) {

        if (isThisFileExist(userPath)) {
            RegisterProfilePrinter.printRepetitiousUsername(user.getUsername());
            return false;
        }
        if (isNickNameRepetitious(user.getNickname())) {
            RegisterProfilePrinter.printRepetitousNickName(user.getNickname());
            return false;
        }
        return true;
    }

    public static boolean isNickNameRepetitious(String nickname) {

        String data;

        for (File userData : getFilesOfOneFolder(getUsersPath())) {
            data = readDataFromFile(userData);
            JsonObject jsonObjectAlt = JsonParser.parseString(data).getAsJsonObject();
            JsonElement nicknameJson = jsonObjectAlt.get("nickname");
            if (nicknameJson.toString().equals("\"" + nickname + "\"")) {
                return true;
            }
        }
        return false;

    }

    public static User getUserByUsername(String username) {

        String path = UserDataBaseController.getUserFileNameByUsername(username);
        return User.getUserByGson(path);
    }

    public static boolean doesUserExistWithThisUsername(String username) {

        String path = UserDataBaseController.getUserFileNameByUsername(username);
        return isThisFileExist(path);
    }

    public static boolean isUserPasswordCorrect(String username, String password) {
        String path = UserDataBaseController.getUserFileNameByUsername(username);
        User user = User.getUserByGson(path);
        return user.isPasswordCorrect(password);
    }

    public static void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        rewriteFileOfObjectGson(getUserFileNameByUsername(user.getUsername()), user);
    }

    public static void changeNickname(User user, String newNickname) {
        user.setNickname(newNickname);
        rewriteFileOfObjectGson(getUserFileNameByUsername(user.getUsername()), user);
    }

    public static void saveChanges(User user){
        rewriteFileOfObjectGson(getUserFileNameByUsername(user.getUsername()),user);
    }

    public static  ArrayList<User> allUsers(){
        ArrayList<User> allUsers=new ArrayList<>();
        for(File file:getFilesOfOneFolder("Resource\\Users")){
            allUsers.add((User)getObjectByGsonFile(file.getPath(),User.class));
        }
        return allUsers;
    }


}

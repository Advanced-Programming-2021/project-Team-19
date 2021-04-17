package Controller;

import Model.User;
import View.GetInput;
import View.Printer.Printer;
import View.Printer.RegisterPrinter;

import java.util.HashMap;
import java.util.regex.Matcher;

public class RegisterMenuController {

    static RegisterMenuController instance = null;

    private RegisterMenuController(){

    }

    public static RegisterMenuController getInstance(){
        if(instance == null){
            instance = new RegisterMenuController();
        }
        return instance;
    }

    public void run(){
        while (true){
            String command;
            command = GetInput.getString();
            if(command.matches("user create" +
                    "(:?(:? --username \\S+)|(:? --nickname \\S+)|(:? --password \\S+)){3}")) {

                manageCreatingAccount(Utils.getMatcher(command, "user create (.+)"));
            }
            else{
                Printer.printInvalidCommand();
            }
        }
    }

    private void manageCreatingAccount(Matcher matcher) {

        matcher.find();
        String username = getDataInCommandByKey(matcher.group(1), "--username");
        String password = getDataInCommandByKey(matcher.group(1), "--password");
        String nickname = getDataInCommandByKey(matcher.group(1), "--nickname");

        if(username == null | password == null | nickname == null){
            Printer.printInvalidCommand();
            return;
        }

        if(!checkFormatValidity(Utils.getHashMap("username", username,
                "password", password, "nickname", nickname))){
            return;
        }

        if(isPasswordWeak(password)){
            RegisterPrinter.printPasswordSafetyError();
            return;
        }

        DataBaseController.createUser(new User(username, nickname, password));
    }

    private boolean checkFormatValidity(HashMap<String, String> userData) {

        for(String dataKey : userData.keySet()){
            if(!isFormatValid(userData.get(dataKey))){
                RegisterPrinter.printFormatError(dataKey);
                return false;
            }
        }

        return true;
    }


    private boolean isPasswordWeak(String password) {

        if(password.length() < 5){
            return true;
        }
        if(!password.matches(".*?\\d.*")){
            return true;
        }
        if(!password.matches(".*?[a-z].*")){
            return true;
        }
        if(!password.matches(".*?[A-Z].*")){
            return true;
        }
        return false;
    }


    private String getDataInCommandByKey(String command, String key){
        Matcher matcher = Utils.getMatcher(command, key + " (\\S+)");
        if(matcher.find())
            return matcher.group(1);
        return null;
    }

    private boolean isFormatValid(String data){
        return data.matches("\\w+");
    }

    private void manageLogin(Matcher matcher){
        MainMenuController.getInstance().run();
    }

    private boolean isUserByThisUsernameExist(String username){return false;}

    private boolean isPasswordTrue(User user, String password){return false;}


}

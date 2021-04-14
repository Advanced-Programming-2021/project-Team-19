package Controller;

import Model.User;
import View.GetInput;
import View.Printer;

import java.util.regex.Matcher;

public class RegisterMenuController {

    static RegisterMenuController instance = null;

    private RegisterMenuController(){
        ;
    }

    public static RegisterMenuController getInstance(){
        if(instance == null){
            instance = new RegisterMenuController();
        }
        return instance;
    }

    public void run(){//user create --username mohammad --nickname hihi --password yiho
        while (true){
            String command;
            command = GetInput.getString();
            if(command.matches("user create(=? --username \\S+)" +
                    "(=? --nickname \\S+)(=? --password \\S+)")) {

                manageCreatingAccount(Utils.getMatcher(command, "user create (.+)"));
            }
        }
    }

    private void manageCreatingAccount(Matcher matcher) {

        matcher.find();
        String username = getDataInCommandByKey(matcher.group(1), "--username");
        String password = getDataInCommandByKey(matcher.group(1), "--password");
        String nickname = getDataInCommandByKey(matcher.group(1), "--nickname");

        if(!checkRequestValidity(username, nickname, password)){
            return;
        }

        new User(username, nickname, password);
    }

    private boolean checkRequestValidity(String username, String nickname, String password) {

        if(!isFormatValid(username)){
            Printer.printFormatError(username);
            return false;
        }
        if(!isFormatValid(nickname)){
            Printer.printFormatError(nickname);
            return false;
        }
        if(!isFormatValid(password)){
            Printer.printFormatError(password);
            return false;
        }
        if(isPasswordWeek(password)){
            Printer.printPasswordSafetyError();
            return false;
        }

        return true;
    }


    private boolean isPasswordWeek(String password) {

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
        matcher.find();
        return matcher.group(1);
    }


    private boolean isFormatValid(String data){
        return data.matches("\\w+");
    }
}

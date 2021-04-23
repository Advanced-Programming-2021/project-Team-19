package Controller;

import Controller.DataBaseControllers.UserDataBaseController;
import Model.User;
import View.GetInput;
import View.Printer.Printer;
import View.Printer.RegisterProfilePrinter;

import java.util.HashMap;
import java.util.regex.Matcher;

public class LoginMenuController extends MenuController {

    static LoginMenuController instance = null;

    private LoginMenuController() {
        super("Login Menu" ,0);
    }

    public static LoginMenuController getInstance() {
        if (instance == null) {
            instance = new LoginMenuController();
        }
        return instance;
    }

    public void run() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("user create" +
                    "(:?(:? --username \\S+)|(:? --nickname \\S+)|(:? --password \\S+)){3}")) {

                manageCreatingAccount(Utils.getMatcher(command, "user create (.+)"));
            } else if (command.matches("user login" +
                    "(:?(:? --username \\S+)|(:? --password \\S+)){2}")) {
                manageLogin(Utils.getMatcher(command, "user login (.+)"));
            }else if (command.matches("menu exit")) {
                break;
            }else if (command.startsWith("menu ")){
                menuOrders(command);
            }
            else {
                Printer.printInvalidCommand();
            }
        }
    }


    private boolean checkUserLoginErrors(String username, String password) {

        if (!UserDataBaseController.doesUserExistWithThisUsername(username)) {
            RegisterProfilePrinter.printInvalidLogin();
            return false;
        } else if (!isPasswordTrue(username, password)) {
            RegisterProfilePrinter.printInvalidLogin();
            return false;
        }
        return true;

    }

    private void manageCreatingAccount(Matcher matcher) {

        matcher.find();
        String username = Utils.getDataInCommandByKey(matcher.group(1), "--username");
        String password = Utils.getDataInCommandByKey(matcher.group(1), "--password");
        String nickname = Utils.getDataInCommandByKey(matcher.group(1), "--nickname");

        if (username == null | password == null | nickname == null) {
            Printer.printInvalidCommand();
            return;
        }

        if (!Utils.checkFormatValidity(Utils.getHashMap("username", username,
                "password", password, "nickname", nickname))) {
            return;
        }

        if (Utils.isPasswordWeak(password)) {
            RegisterProfilePrinter.printPasswordSafetyError();
            return;
        }

        UserDataBaseController.createUser(new User(username, nickname, password));
    }


    private void manageLogin(Matcher matcher) {
        matcher.find();
        String username = Utils.getDataInCommandByKey(matcher.group(1), "--username");
        String password = Utils.getDataInCommandByKey(matcher.group(1), "--password");

        if (checkUserLoginErrors(username, password)) {

            login(UserDataBaseController.getUserByUsername(username));
        }
    }

    private void login(User user) {

        RegisterProfilePrinter.printLoginSuccessful();
        MainMenuController.getInstance().run(user);
    }

    private boolean isPasswordTrue(String username, String password) {
        return UserDataBaseController.isUserPasswordCorrect(username, password);
    }


}

package Controller;

import Controller.DataBaseControllers.UserDataBaseController;
import Model.User;
import View.GetInput;
import View.Printer.Printer;
import View.Printer.RegisterProfilePrinter;

import java.util.regex.Matcher;

class ProfileMenuController extends MenuController {

    private User user;

    private static ProfileMenuController instance = null;

    private ProfileMenuController() {
        super("Profile Menu", 2);
    }

    public static ProfileMenuController getInstance() {

        if (instance == null) {
            instance = new ProfileMenuController();
        }
        return instance;

    }

    public void run(User user) {
        this.user = user;
        manageProfileMenu();
    }


    private void changeNickName(Matcher matcher) {
        matcher.find();
        String newNickname = matcher.group(1);

        if (UserDataBaseController.isNickNameRepetitious(newNickname)) {
            RegisterProfilePrinter.printRepetitousNickName(newNickname);
        } else {
            RegisterProfilePrinter.printNicknameChanged();
            UserDataBaseController.changeNickname(user, newNickname);
        }
    }

    private void changePassword(Matcher matcher) {
        matcher.find();
        String currentPassword = Utils.getDataInCommandByKey(matcher.group(1), "--current");
        String newPassword = Utils.getDataInCommandByKey(matcher.group(1), "--new");

        if (!isPasswordTrue(user.getUsername(), currentPassword)) {
            RegisterProfilePrinter.printCurrentPasswordInvalid();
        } else if (currentPassword.matches(newPassword)) {
            RegisterProfilePrinter.printEnterNewPassword();
        } else if (Utils.isPasswordWeak(newPassword)) {
            RegisterProfilePrinter.printPasswordSafetyError();
        } else if (!Utils.checkFormatValidity(Utils.getHashMap("password", newPassword))) {
            RegisterProfilePrinter.printFormatError("password");
        } else {
            RegisterProfilePrinter.printPasswordChanged();
            UserDataBaseController.changePassword(user, newPassword);
        }

    }

    private boolean isPasswordTrue(String username, String password) {
        return UserDataBaseController.isUserPasswordCorrect(username, password);
    }

    public void manageProfileMenu() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("profile change --nickname \\S+")) {
                changeNickName(Utils.getMatcher(command, "profile change (--nickname \\S+)"));
            } else if (command.matches("profile change" +
                    "(:?(:? --password)|(:? --current \\S+)|(:? --new \\S+)){3}")) {
                changePassword(Utils.getMatcher(command, "profile change (.+)"));
            }else if (command.matches("menu exit")) {
                break;
            }else if (command.startsWith("menu ")){
                menuOrders(command);
            } else {
                Printer.printInvalidCommand();
            }
        }
    }
}

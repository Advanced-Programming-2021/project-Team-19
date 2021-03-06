package controller.MenuControllers;

import controller.DataBaseControllers.UserDataBaseController;
import controller.Utils;
import model.Data.DataForClientFromServer;
import model.Enums.MessageType;
import model.User;
import view.Printer.RegisterProfilePrinter;

import java.util.regex.Matcher;

public class ProfileMenuController {

    private static ProfileMenuController instance = null;

    private ProfileMenuController() {

    }

    public static ProfileMenuController getInstance() {

        if (instance == null) {
            instance = new ProfileMenuController();
        }
        return instance;

    }

    public DataForClientFromServer run(User user, String command) {

        if (command.matches("profile change --nickname (\\w+)")) {
            return changeNickName(
                    user, Utils.getMatcher(command, "profile change --nickname (\\w+)"));

        } else if (command.matches("profile change --password" +
                "--current (\\w+) --new (\\w+)")) {

            return changePassword(
                    user,
                    Utils.getMatcher(command, "profile change --password" +
                            "--current (\\S+) --new (\\S+)"));
        }

        return Utils.getDataSendToClientForInvalidInput();

    }


    private DataForClientFromServer changeNickName(User user, Matcher matcher) {

        matcher.find();
        String newNickname = matcher.group(1);

        if (UserDataBaseController.isNickNameRepetitious(newNickname)) {

            return new DataForClientFromServer(
                    "user with nickname " + newNickname + " already exists", MessageType.ERROR);

        } else {
            RegisterProfilePrinter.printNicknameChanged();

            if (UserDataBaseController.changeNickname(user, newNickname))
                return new DataForClientFromServer("", MessageType.SUCCESSFUL);
            else
                return Utils.getDataSendToClientForOperationFailed();
        }
    }

    private DataForClientFromServer changePassword(User user, Matcher matcher) {

        matcher.find();
        String currentPassword = matcher.group(1);
        String newPassword = matcher.group(2);

        if (!isPasswordTrue(user.getUsername(), currentPassword)) {

            return new DataForClientFromServer("current password is invalid", MessageType.ERROR);

        } else if (currentPassword.matches(newPassword)) {
            return new DataForClientFromServer("please enter a new password", MessageType.ERROR);

        } else if (Utils.isPasswordWeak(newPassword)) {
            return new DataForClientFromServer("new password is weak", MessageType.ERROR);

        }

        if (UserDataBaseController.changePassword(user, newPassword))
            return new DataForClientFromServer("password changed successfully!",
                    MessageType.SUCCESSFUL);
        else
            return Utils.getDataSendToClientForOperationFailed();

    }

    private boolean isPasswordTrue(String username, String password) {
        return UserDataBaseController.isUserPasswordCorrect(username, password);
    }
}

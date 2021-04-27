package View.Menu;

import View.DataToSendToServer;
import View.GetInput;
import View.Printer.Printer;
import View.Printer.RegisterProfilePrinter;
import View.Utils;

import java.util.regex.Matcher;

public class LoginMenu extends Menu{

    public LoginMenu(String menuName) {
        super(menuName);
    }

    public void run() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("user create" +
                    "(:?(:? --username \\S+)|(:? --nickname \\S+)|(:? --password \\S+)){3}")) {
                manageCreatingAccount(command);;
            } else if (command.matches("user login" +
                    "(:?(:? --username \\S+)|(:? --password \\S+)){2}")) {
                manageLogin(command);
            } else if (command.matches("menu exit")) {
                break;
            } else if (command.startsWith("menu ")) {
                handleMenuOrders(command);
            }else if(command.matches("help")){
                help();
            }else {
                Printer.printInvalidCommand();
            }
        }
    }

    private void manageLogin(String command) {

        Matcher matcher = Controller.Utils.getMatcher(command, "user login (.+)");

        matcher.find();
        String username = Utils.getDataInCommandByKey(matcher.group(1), "--username");

        sendData(new DataToSendToServer(command, username, menuName));
    }

    private void manageCreatingAccount(String command) {

        Matcher matcher = Utils.getMatcher(command, "user create (.+)");

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

        sendData(new DataToSendToServer(command, menuName));
    }

    private void help() {
        System.out.print("""
                user create --username [username] --nickname [nickname] --password [password]
                user login --username <username> --password <password>
                 help
                menu show-current
                menu [menu name]
                menu exit               
                """);
    }

}

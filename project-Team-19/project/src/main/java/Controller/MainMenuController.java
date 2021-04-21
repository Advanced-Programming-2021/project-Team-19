package Controller;

import Model.User;
import View.GetInput;
import View.Printer.Printer;
import java.util.regex.Matcher;

public class MainMenuController extends MenuController {

    private static MainMenuController instance = null;


    private MainMenuController() {
        super("Main Menu", 1);
    }

    public static MainMenuController getInstance() {
        if (instance == null) {
            return new MainMenuController();
        }
        return instance;
    }

    public void run(User user) {

        setUser(user);

        String command;
        while (true) {
            command = GetInput.getString();
            if (command.matches("")) {

            }
            else if (command.matches("menu show-current")) {
                showCurrentMenu();
            }
            else if (command.matches("menu enter (.+)")) {
                enterOtherMenu(Utils.getFirstGroupInMatcher(Utils.getMatcher(command, "menu enter (.+)")));
            }
            else if (command.matches("menu exit")) {
                break;
            }
            else {
                Printer.printInvalidCommand();
            }
        }
    }

    private void logoutUser(Matcher matcher) {
    }

    private void showScoreBoard(Matcher matcher) {
    }

    private void exitMenu(Matcher matcher) {
    }

    private void showCurrentMenu(Matcher matcher) {
    }

}

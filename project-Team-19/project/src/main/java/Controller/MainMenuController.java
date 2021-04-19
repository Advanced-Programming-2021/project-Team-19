package Controller;

import Model.User;
import View.GetInput;
import View.Printer.Printer;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MainMenuController extends MenuController {

    private static String menuName;

    private static MainMenuController instance = null;

    private static final ArrayList<String> accessibleMenus;

    static {
        menuName = "MainMenu";

        accessibleMenus = new ArrayList<>();

        accessibleMenus.add("Duel Menu");
        accessibleMenus.add("Deck Menu");
        accessibleMenus.add("Scoreboard Menu");
        accessibleMenus.add("Profile Menu");
        accessibleMenus.add("Shop Menu");
        accessibleMenus.add("Import/Export Menu");

    }

    private MainMenuController() {
    }

    public static MainMenuController getInstance() {
        if (instance == null) {
            return new MainMenuController();
        }
        return instance;
    }


    public void run(User user) {

        String command;
        while (true) {
            command = GetInput.getString();
            if (command.matches("")) {

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

    @Override
    public boolean canEnterTheMenu(String menuName) {
        return accessibleMenus.contains(menuName);
    }

    @Override
    public void showCurrentMenu() {
        Printer.print(menuName);
    }
}

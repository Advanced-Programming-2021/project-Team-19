package Controller;

import Model.User;
import View.Printer.Printer;
import View.Printer.RegisterProfilePrinter;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class MenuController {

    protected static final HashMap<String, Integer> menuLevels;

    protected final String menuName;

    protected final int menuLevel;

    protected User user;

    static {

        menuLevels = new HashMap<>();

        menuLevels.put("Login Menu", 0);
        menuLevels.put("Main Menu", 1);
        menuLevels.put("Duel Menu", 2);
        menuLevels.put("Deck Menu", 2);
        menuLevels.put("Scoreboard Menu", 2);
        menuLevels.put("Profile Menu", 2);
        menuLevels.put("Shop Menu", 2);
        menuLevels.put("Import/Export Menu", 2);

    }

    public MenuController(String menuName, int menuLevel) {
        this.menuName = menuName;
        this.menuLevel = menuLevel;
    }

    protected void setUser(User user) {
        this.user = user;
    }

    protected void handleMenuOrders(String command) {

        if (command.matches("menu show-current")) {
            showCurrentMenu();
        } else if (command.matches("menu enter (.+)")) {
            enterOtherMenu(Utils.getFirstGroupInMatcher(Utils.getMatcher(command, "menu enter (.+)")));
        } else {
            Printer.printInvalidCommand();
        }

    }


    private void enterOtherMenu(String menuName) {

        if (!isMenuNameValid(menuName)) {
            Printer.print("invalid menu name");
            return;
        }
        if (!canEnterTheMenu(menuName)) {
            RegisterProfilePrinter.printCanNotNavigate();
            return;
        }

        if (menuName.matches("Profile Menu")) {
            ProfileMenuController.getInstance().run(user);
        } else if (menuName.matches("Deck Menu")) {
            DeckController.getInstance().run(user);
        } else if (menuName.matches("Scoreboard Menu")) {
            ScoreBoardMenuController.getInstance().run();
        } else if (menuName.matches("Shop Menu")) {
            ShopMenuController.getInstance().run(user);
        } else if (menuName.matches("Import/Export Menu")) {
            ImportAndExportMenuController.getInstance().run();
        } else if (menuName.matches("Main Menu")) {
            MainMenuController.getInstance().run(user);
        }

    }

    private boolean canEnterTheMenu(String menuName) {
        return menuLevels.get(menuName) - menuLevel == 1;
    }

    private boolean isMenuNameValid(String menuName) {
        return menuLevels.containsKey(menuName);
    }

    private void showCurrentMenu() {
        Printer.print(menuName);
    }

}

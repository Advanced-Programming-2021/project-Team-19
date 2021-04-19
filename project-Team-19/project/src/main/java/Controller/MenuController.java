package Controller;

import Model.Deck;
import Model.User;
import View.Printer.Printer;

import java.util.ArrayList;

public abstract class MenuController {

    private static final ArrayList<String> menuNames;

    protected User user;

    static {

        menuNames = new ArrayList<>();
        menuNames.add("Main Menu");
        menuNames.add("Duel Menu");
        menuNames.add("Deck Menu");
        menuNames.add("Scoreboard Menu");
        menuNames.add("Profile Menu");
        menuNames.add("Shop Menu");
        menuNames.add("Import/Export Menu");
    }


    public void enterOtherMenu(String menuName) {

        if (isMenuNameValid(menuName)) {
            Printer.print("invalid menu name");
        }
        if (!canEnterTheMenu(menuName)) {
            Printer.print("menu navigation is not possible");
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

    public abstract boolean canEnterTheMenu(String menuName);

    public boolean isMenuNameValid(String menuName) {
        return menuNames.contains(menuName);
    }

    protected abstract void showCurrentMenu();

}

package Controller;

import Model.User;
import View.Printer.Printer;
import View.Printer.RegisterPrinter;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class MenuController {

    private static final ArrayList<String> menuNames;

    protected static final HashMap<String , Integer> menuLevels;

    protected final String menuName;

    protected final int menuLevel;

    protected User user;

    static {

        menuLevels = new HashMap<>();

        menuLevels.put("Login Menu", 0);
        menuLevels.put("Main Menu", 1);
        menuLevels.put("Duel Menu" , 2);
        menuLevels.put("Deck Menu" , 2);
        menuLevels.put("Scoreboard Menu" , 2);
        menuLevels.put("Profile Menu" , 2);
        menuLevels.put("Shop Menu" , 2);
        menuLevels.put("Import/Export Menu" , 2);

        menuNames = new ArrayList<>();

        menuNames.add("Main Menu");
        menuNames.add("Duel Menu");
        menuNames.add("Deck Menu");
        menuNames.add("Scoreboard Menu");
        menuNames.add("Profile Menu");
        menuNames.add("Shop Menu");
        menuNames.add("Import/Export Menu");
    }

    public MenuController(String menuName, int menuLevel){
        this.menuName = menuName;
        this.menuLevel = menuLevel;
    }

    protected void setUser(User user){this.user = user;}

    public void enterOtherMenu(String menuName) {

        if (!isMenuNameValid(menuName)) {
            Printer.print("invalid menu name");
            return;
        }
        if (!canEnterTheMenu(menuName)) {
            RegisterPrinter.printCanNotNavigate();
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

    public boolean canEnterTheMenu(String menuName){
        return menuLevels.get(menuName) - menuLevel == 1;
    }

    public boolean isMenuNameValid(String menuName) {
        return menuNames.contains(menuName);
    }

    protected void showCurrentMenu(){
        Printer.print(menuName);
    }

}

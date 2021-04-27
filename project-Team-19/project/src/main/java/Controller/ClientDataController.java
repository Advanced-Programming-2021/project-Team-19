package Controller;

import Controller.DataBaseControllers.UserDataBaseController;
import Model.User;
import View.DataToSendToServer;
import View.Printer.Printer;
import View.Printer.RegisterProfilePrinter;
import jdk.jshell.execution.Util;

public class ClientDataController {

    public static String run(DataToSendToServer data){
        String menuName = data.menuName;
        User user = null;
        if ( data.username != null)
            user = UserDataBaseController.getUserByUsername(data.username);

        if (menuName.matches("Login Menu")) {
            LoginMenuController.getInstance().run();
        }else if (menuName.matches("Profile Menu")) {
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
        return "";
    }

}

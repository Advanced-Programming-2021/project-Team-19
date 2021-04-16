package Controller;

import java.util.regex.Matcher;

public class MainMenuController {

    private static MainMenuController instance= null;

    private MainMenuController(){}

    public static MainMenuController getInstance(){
        if(instance == null){
            return new MainMenuController();
        }
        return instance;
    }

    public void run(){}

    private void logoutUser(Matcher matcher){}

    private void showScoreBoard(Matcher matcher){}

    private void exitMenu(Matcher matcher){}

    private void showCurrentMenu(Matcher matcher){}

}

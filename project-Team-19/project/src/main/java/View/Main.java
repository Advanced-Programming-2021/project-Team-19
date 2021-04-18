package View;

import Controller.*;

public class Main {
    public static void main(String[] arg){

        DataBaseController.makeResourceDirectory();

        LoginMenuController.getInstance().run();
    }
}

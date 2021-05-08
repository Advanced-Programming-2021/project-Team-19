package view;

import controller.DataBaseControllers.DataBaseController;
import view.Menu.LoginMenu;



public class Main {

    public static void main(String[] arg){

        DataBaseController.makeResourceDirectory();

        LoginMenu.getInstance().run();
    }

}

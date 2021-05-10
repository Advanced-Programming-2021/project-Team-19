package view;

import controller.DataBaseControllers.DataBaseController;
import org.junit.jupiter.api.Test;
import view.Menu.LoginMenu;



public class Main {

    public static void main(String[] arg){

        DataBaseController.makeResourceDirectory();

        LoginMenu.getInstance().run();
    }

}

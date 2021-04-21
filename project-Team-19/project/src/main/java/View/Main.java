package View;

import Controller.*;
import Controller.DataBaseControllers.*;

public class Main {
    public static void main(String[] arg){

        DataBaseController.makeResourceDirectory();

        LoginMenuController.getInstance().run();
    }
}

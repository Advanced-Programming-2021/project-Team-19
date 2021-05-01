package View;

import Controller.*;
import Controller.DataBaseControllers.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {


        DataBaseController.makeResourceDirectory();
        LoginMenuController.getInstance().run();
    }

}

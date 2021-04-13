package View;

import Controller.*;

import java.io.File;

public class Main {
    public static void main(String[] arg){

        DataBaseController.makeResourceDirectory();

        RegisterMenuController.getInstance().run();
    }
}

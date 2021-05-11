package view;

import controller.DataBaseControllers.CardDataBaseController;
import controller.DataBaseControllers.DataBaseController;
import controller.Utils;
import model.Card.Card;
import model.Data.DataForClientFromServer;
import model.Enums.MessageType;
import org.junit.jupiter.api.Test;
import view.Menu.LoginMenu;



public class Main {

    public static void main(String[] arg){

        DataBaseController.makeResourceDirectory();

        LoginMenu.getInstance().run();
    }

}

package controller.MenuControllers;

import controller.DataBaseControllers.CardDataBaseController;
import controller.DataBaseControllers.DataBaseController;
import controller.Utils;
import model.Card.Card;
import model.Data.DataForClientFromServer;
import model.Enums.CardNames;
import model.Enums.MessageType;

import java.util.regex.Matcher;

public class ImportAndExportMenuController{

    private static ImportAndExportMenuController instance = null;

    private ImportAndExportMenuController() {
    }

    public static ImportAndExportMenuController getInstance() {
        if (instance == null) {
            instance = new ImportAndExportMenuController();
        }
        return instance;
    }

    private DataForClientFromServer importCard(Matcher matcher) {

        matcher.find();

        CardNames cardName = Utils.getCardEnumByName(matcher.group(1));

        Card card = CardDataBaseController.getCardObjectByCardName(cardName);

        if(card == null){
             return new DataForClientFromServer
                    ("invalid card name", MessageType.ERROR);
        }

         return  new DataForClientFromServer(card.toString(), MessageType.SUCCESSFUL);

    }


    private DataForClientFromServer exportCard(Matcher matcher) {

        matcher.find();

        CardNames cardName = Utils.getCardEnumByName(matcher.group(1));

        if (cardName == null) {
            return new DataForClientFromServer
                    ("invalid card name", MessageType.ERROR);

        }

        return new DataForClientFromServer(DataBaseController.readDataFromFile
                (CardDataBaseController.getCardFilePathByCardName(cardName)), MessageType.SUCCESSFUL);


//        System.out.println(DataBaseController.makeObjectJson
//                (CardDataBaseController.getCardObjectByCardName(cardName)));

    }


    public DataForClientFromServer run(String command) {

        if (command.matches("import card .+")) {
            return importCard(Utils.getMatcher(command, "import card (.+)"));
        } else if (command.matches("export card .+")) {
            return exportCard(Utils.getMatcher(command, "export card (.+)"));
        }
        //else
        return Utils.getDataSendToClientForInvalidInput();

    }

}

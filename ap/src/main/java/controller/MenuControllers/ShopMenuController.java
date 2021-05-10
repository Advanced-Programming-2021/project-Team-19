package controller.MenuControllers;

import controller.DataBaseControllers.CardDataBaseController;
import controller.DataBaseControllers.DataBaseController;
import controller.DataBaseControllers.UserDataBaseController;
import controller.Utils;
import model.Card.Card;
import model.Data.DataForClientFromServer;
import model.Enums.MessageType;
import model.User;

import java.util.regex.Matcher;

public class ShopMenuController {


    private ShopMenuController() {
    }

    private static ShopMenuController instance = null;

    public static ShopMenuController getInstance() {

        if (instance == null) {
            instance = new ShopMenuController();
        }
        return instance;
    }


    public DataForClientFromServer run(User user, String command) {

        if (command.matches("shop buy (.+)")) {
            return manageBuyCards(user, Utils.getMatcher(command, "shop buy (.+)"));
        }
        else if (command.matches("shop show --all")){
            return showAllCards();
        }
        return Utils.getDataSendToClientForInvalidInput();

    }

    private DataForClientFromServer manageBuyCards(User user, Matcher matcher) {

        matcher.find();
        String cardName = matcher.group(1);

        Card card = CardDataBaseController.getCardObjectByCardName(Utils.getCardEnumByName(cardName));

        if (card == null) {
            return new DataForClientFromServer("there is no card with this name",
                    MessageType.ERROR);
        }

        if(user.getCredit() < card.getPrice()){
            return new DataForClientFromServer("not enough money", MessageType.ERROR);
        }

        user.addCard(Utils.getCardEnumByName(cardName));
        user.setCredit(user.getCredit() - card.getPrice());
        DataBaseController.rewriteFileOfObjectGson(UserDataBaseController.
                getUserFilePathByUsername(user.getUsername()), user);
        return new DataForClientFromServer("you successfully bought the card",
                MessageType.SUCCESSFUL);

    }

    private DataForClientFromServer showAllCards() {

        return new DataForClientFromServer(CardDataBaseController.getCardNamesAndPrices(), MessageType.Card);
    }
}

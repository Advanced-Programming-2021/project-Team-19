package Controller.DataBaseControllers;

import Model.Card.Card;
import Model.Enums.CardNames;

public class CardDataBaseController extends DataBaseController {

    public static String getCardFilePathByCardName(CardNames cardName) {
        return DataBaseController.getCardsPath() + "\\" + cardName + ".json";
    }

    public static Card getCardObjectByCardName(CardNames cardName){
        return (Card) getObjectByGsonFile(getCardFilePathByCardName(cardName),
                getClassByClassName(cardName.getClassName()));
    }

}

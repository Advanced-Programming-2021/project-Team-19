package controller.DataBaseControllers;

import model.Card.Card;
import model.Enums.CardNames;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;

public class CardDataBaseController extends DataBaseController {

    public static String getCardFilePathByCardName(CardNames cardName) {
        return DataBaseController.getCardsPath() + "\\" + getCardNameStr(cardName) + ".json";
    }

    public static String getCardNameStr(CardNames cardName){

        String ans;
        ans = cardName.toString();

        ans = ans.replaceAll("___", ", ");
        ans = ans.replaceAll("__", "-");
        ans = ans.replaceAll("_", " ");

        return ans;
    }

    public static Card getCardObjectByCardName(CardNames cardName){

        if(cardName == null){
            return null;
        }

        return (Card) getObjectByGsonFile(getCardFilePathByCardName(cardName),
                getClassByClassName(cardName.getClassName()));
    }

    public static String getCardNamesAndPrices(){

        StringBuilder returnedData = new StringBuilder();

        File[] cardFiles = getFilesOfOneFolder(getCardsPath());
        String tempData;

        for(File file : cardFiles){

            tempData = readDataFromFile(file);

            JsonObject jsonObjectAlt = JsonParser.parseString(tempData).getAsJsonObject();
            JsonElement nameJson = jsonObjectAlt.get("Name");
            JsonElement priceJson = jsonObjectAlt.get("Price");
            returnedData.append(nameJson.toString() + ":" + priceJson.toString() + "\n");

        }

        returnedData.deleteCharAt(returnedData.length() - 1);
        return returnedData.toString();
    }


}

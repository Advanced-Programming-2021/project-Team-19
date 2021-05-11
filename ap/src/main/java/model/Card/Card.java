package model.Card;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import controller.DuelControllers.GameData;
import model.Enums.CardFamily;

public class Card {

    @SerializedName("Price")
    private int price;
    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String description;


    @Expose
    private CardFamily cardFamily;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CardFamily getCardFamily() {
        return cardFamily;
    }

    public void setCardFamily(CardFamily cardFamily) {
        this.cardFamily = cardFamily;
    }

    public String getName() {
        return name;
    }

    public void handleDestroy(GameData gameData){
        if (gameData.getCurrentGamer().getGameBoard().getZone(this) != null)
            gameData.moveCardFromOneZoneToAnother(this,
                    gameData.getCurrentGamer().getGameBoard().getZone(this),
                    gameData.getCurrentGamer().getGameBoard().getGraveYard());
        else
            gameData.moveCardFromOneZoneToAnother(this,
                    gameData.getSecondGamer().getGameBoard().getZone(this),
                    gameData.getSecondGamer().getGameBoard().getGraveYard());
    }
}

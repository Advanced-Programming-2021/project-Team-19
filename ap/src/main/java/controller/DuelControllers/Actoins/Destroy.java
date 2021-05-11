package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Card.Card;

public class Destroy extends Action {

    public Destroy(GameData gameData){
        super(gameData, "destroy");
    }

    public void run(Card card){
        card.handleDestroy(gameData);
    }

}

package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Card.Monster;

public abstract class Summon extends Action{

    protected Monster summoningMonster;

    private void setSummoningMonster(){
        summoningMonster = (Monster) gameData.getSelectedCard();
    }

    protected Summon(GameData gameData, String actionName){
        super(gameData, actionName);
        setSummoningMonster();
    }

}

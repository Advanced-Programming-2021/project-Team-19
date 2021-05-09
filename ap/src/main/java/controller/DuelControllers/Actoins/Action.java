package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;

public abstract class Action {

    protected GameData gameData;

    protected Action(GameData gameData){
        setGameData(gameData);
    }

    protected void setGameData(GameData gameData){
        this.gameData = gameData;
    }

    public GameData getGameData(){
        return gameData;
    }
}

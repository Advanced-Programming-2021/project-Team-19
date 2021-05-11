package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Card.SpellAndTraps;
import view.Printer.Printer;

public abstract class Action {

    protected GameData gameData;
    protected String actionName;

    protected Action(GameData gameData, String actionName){

        setGameData(gameData);
        setActionName(actionName);
    }

    protected void setGameData(GameData gameData){
        this.gameData = gameData;
    }

    public GameData getGameData(){
        return gameData;
    }

    protected void setActionName(String actionName){
        this.actionName = actionName;
    }

    public String getActionName(){
        return actionName;
    }

    protected boolean canOtherPlayerActivateAnyTrapOrSpeedSpell(){

        for(SpellAndTraps spellOrTrap :
                gameData.getSecondGamer().getGameBoard().getSpellAndTrapCardZone().getAllCards())
        {
            if(spellOrTrap.canActivateOnOtherPlayerTurn(gameData)){
                return true;
            }
        }

        return false;
    }

    protected void handleActivateTrapOrSpeedSpellOnOtherPlayerTurn(){

        changeTurn();
        gameData.showBoard();


        changeTurn();
    }

    private void changeTurn(){
        gameData.changeTurn();
        Printer.print("now it will be " + gameData.getCurrentGamer().getUsername() +"’s turn");
    }

}

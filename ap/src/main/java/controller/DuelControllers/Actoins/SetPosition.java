package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import controller.Utils;
import model.Card.Monster;
import model.Enums.CardMod;
import model.Phase;
import view.Printer.Printer;

import java.util.regex.Matcher;

public class SetPosition extends Action {

    public SetPosition(GameData gameData){
        super(gameData);
    }

    public void run(Matcher matcher){
        setPosition(matcher);
    }

    private void setPosition(Matcher matcher) {

        Monster selectedCard = (Monster) gameData.getSelectedCard();

        if (selectedCard == null) {
            Printer.print("no card is selected yet");
            return;
        }

        if (!gameData.getFirstGamer().getGameBoard().getMonsterCardZone().containsCard(selectedCard)) {
            Printer.print("you can’t change this card position");
            return;
        }

        if (!gameData.getCurrentPhase().equals(Phase.MAIN1) && !gameData.getCurrentPhase().equals(Phase.MAIN2)) {
            Printer.print("action not allowed in this phase");
            return;
        }

        String modeStr = Utils.getFirstGroupInMatcher(matcher);
        CardMod newCardMode;

        if(modeStr.equals("defense")){
            newCardMode = CardMod.DEFENSIVE_OCCUPIED;
            if(!selectedCard.getCardMod().equals(CardMod.OFFENSIVE_OCCUPIED)){
                Printer.print("you can’t change this card position");
                return;
            }
        }
        else{
            newCardMode = CardMod.OFFENSIVE_OCCUPIED;
            if(!selectedCard.getCardMod().equals(CardMod.DEFENSIVE_OCCUPIED)){
                Printer.print("you can’t change this card position");
                return;
            }
        }

        if(selectedCard.getLastTurnHasChangedPosition() == gameData.getTurn()){
            Printer.print("you already changed this card position in this turn");
            return;
        }

        selectedCard.handleChangePosition(gameData, newCardMode);
        Printer.print("monster card position changed successfully");
    }

}

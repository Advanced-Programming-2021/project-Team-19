package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Card.Card;
import model.Card.Monster;
import model.Phase;
import view.Printer.Printer;

import java.util.regex.Matcher;

public abstract class Attack extends Action{

    public Attack(GameData gameData){
        super(gameData);
    }

    public static boolean doesNotHaveMutualAttackErrors(Card selectedCard, GameData gameData) {

        if (selectedCard == null) {
            Printer.print("no card is selected yet");
            return false;
        } else if (!gameData.getFirstGamer().getGameBoard().getMonsterCardZone().containsCard(selectedCard)) {
            Printer.print("you can’t attack with this card");
            return false;
        } else if (!gameData.getCurrentPhase().equals(Phase.BATTLE)) {
            Printer.print("action not allowed in this phase");
            return false;
        } else if (gameData.getTurn() == ((Monster) selectedCard).getLastTurnAttacked()) {
            Printer.print("this card already attacked");
            return false;
        }
        return true;
    }

}

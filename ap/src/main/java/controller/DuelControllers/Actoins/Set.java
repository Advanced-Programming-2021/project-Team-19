package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Board.SpellAndTrapCardZone;
import model.Card.Card;
import model.Card.Monster;
import model.Card.SpellAndTraps;
import model.Enums.CardFamily;
import model.Enums.SpellCardMods;
import model.Phase;
import view.Printer.Printer;

public class Set extends Action {

    public Set(GameData gameData) {
        super(gameData, "set");
    }

    public void run() {
        manageSetCard();
    }

    private void manageSetCard() {

        Card selectedCard = gameData.getSelectedCard();

        if (selectedCard == null) {
            Printer.print("no card is selected yet");
        } else if (!gameData.getCurrentGamer().getGameBoard().getHand().getCardsInHand()
                .contains(selectedCard)) {
            Printer.print("you can’t set this card");
        } else if (!gameData.getCurrentPhase().equals(Phase.MAIN1) && !gameData.getCurrentPhase().equals(Phase.MAIN2)) {
            Printer.print("action not allowed in this phase");
        }

        if (selectedCard.getCardFamily().equals(CardFamily.MONSTER)) {
            setMonster(selectedCard);
        }
        if(selectedCard.getCardFamily().equals(CardFamily.TRAP)||
                selectedCard.getCardFamily().equals(CardFamily.MONSTER)){
            setSpellOrTrap(selectedCard);
        }

    }

    private void setSpellOrTrap(Card card){

        if (gameData.getCurrentGamer().getGameBoard().getSpellAndTrapCardZone().isZoneFull()) {
            Printer.print("spell card zone is full");
            return;
        }

        ((SpellAndTraps)card).setSpellCardMod(SpellCardMods.HIDDEN);

        gameData.moveCardFromOneZoneToAnother(card,
                gameData.getCurrentGamer().getGameBoard().getHand(),
                gameData.getCurrentGamer().getGameBoard().getSpellAndTrapCardZone());

        Printer.print("set successfully");
    }


    private void setMonster(Card card) {

        if (gameData.getCurrentGamer().getGameBoard().getMonsterCardZone().isZoneFull()) {
            Printer.print("monster card zone is full");
            return;
        } else if (gameData.getCurrentGamer().getLastTurnHasSummonedOrSet() == gameData.getTurn()) {
            Printer.print("you already summoned/set on this turn");
            return;
        }

        ((Monster) card).handleSet(gameData);

        gameData.moveCardFromOneZoneToAnother(card,
                gameData.getCurrentGamer().getGameBoard().getHand(),
                gameData.getCurrentGamer().getGameBoard().getMonsterCardZone());

        Printer.print("set successfully");
    }
}

package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Card.Card;
import model.Card.Monster;
import model.Enums.CardType;
import model.Phase;
import view.Printer.Printer;

public class Set extends Action {

    public Set(GameData gameData) {
        super(gameData);
    }

    public void run() {
        setCard();
    }

    private void setCard() {

        Card selectedCard = gameData.getSelectedCard();

        if (selectedCard == null) {
            Printer.print("no card is selected yet");
        } else if (!gameData.getFirstGamer().getGameBoard().getHand().getCardsInHand()
                .contains(selectedCard)) {
            Printer.print("you can’t set this card");
        } else if (!gameData.getCurrentPhase().equals(Phase.MAIN1) && !gameData.getCurrentPhase().equals(Phase.MAIN2)) {
            Printer.print("action not allowed in this phase");
        }

        if (selectedCard.getCardType().equals(CardType.MONSTER)) {
            setMonster(selectedCard);
        }

    }

    private void setMonster(Card card) {

        if (gameData.getFirstGamer().getGameBoard().getMonsterCardZone().isZoneFull()) {
            Printer.print("monster card zone is full");
            return;
        } else if (gameData.getFirstGamer().getLastTurnHasSummonedOrSet() == gameData.getTurn()) {
            Printer.print("you already summoned/set on this turn");
            return;
        }

        ((Monster) card).handleSet(gameData);

        gameData.moveCardFromOneZoneToAnother(card,
                gameData.getFirstGamer().getGameBoard().getHand(),
                gameData.getFirstGamer().getGameBoard().getMonsterCardZone());

        Printer.print("set successfully");
    }
}

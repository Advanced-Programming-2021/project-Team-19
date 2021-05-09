package controller.DuelControllers.Phases;

import controller.DuelControllers.GameData;
import model.Card.Card;
import model.Gamer;
import model.Phase;
import view.GetInput;
import view.Printer.Printer;

public class DrawPhase {

    public void run(GameData gameData) {

        addCardToPlayerHand(gameData);
    }

    private void addCardToPlayerHand(GameData gameData) {

        Gamer currentPlayer = gameData.getFirstGamer();
        Card cardToAddToHand = currentPlayer.getGameBoard().getDeckZone().removeCard(0);
        Printer.print("new card added to the hand :" + cardToAddToHand.getName());
        currentPlayer.getGameBoard().getHand().addCard(cardToAddToHand);
    }

}

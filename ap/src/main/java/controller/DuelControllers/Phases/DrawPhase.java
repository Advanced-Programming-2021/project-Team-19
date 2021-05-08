package controller.DuelControllers.Phases;

import controller.DuelControllers.GameData;
import model.Card.Card;
import model.Gamer;
import view.GetInput;
import view.Printer.Printer;

public class DrawPhase extends AllPhases{

    public DrawPhase(GameData gameData) {
        super.gameData = gameData;
        this.phaseName = "Draw Phase";
    }

    public String run() {
        while (true) {
            showPhaseName();
            String command;
            command = GetInput.getString();
            if (command.matches("next phase")) {
                break;
            } else if (command.matches("help")) {
//                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
        addCardToPlayerHand();
        return "";
    }

    private void addCardToPlayerHand() {
        Gamer currentPlayer = gameData.getFirstGamer();
        Card cardToAddToHand = currentPlayer.getGameBoard().getDeckZone().removeCard(0);
        Printer.print("new card added to the hand :" + cardToAddToHand.getName());
        currentPlayer.getGameBoard().getHand().addCard(cardToAddToHand);
    }

}

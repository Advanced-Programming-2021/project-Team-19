package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Card.Monster;
import model.Enums.CardMod;
import view.Printer.Printer;

public class FlipSummon extends Summon {

    public FlipSummon(GameData gameData){
        super(gameData);
    }

    public void run(){
        flip();
    }

    private void flip() {

        Monster selectedCard = (Monster) gameData.getSelectedCard();

        if (selectedCard == null) {
            Printer.print("no card is selected yet");
            return;
        }
        if (!gameData.getFirstGamer().getGameBoard().getMonsterCardZone()
                .containsCard(selectedCard)) {
            Printer.print("you can’t change this card position");
            return;
        }
        if (!selectedCard.getCardMod().equals(CardMod.DEFENSIVE_HIDDEN)) {
            Printer.print("you can’t flip summon this card");
            return;
        }
        if (selectedCard.getTurnWasPutInMonsterZone() == gameData.getTurn()) {
            Printer.print("you can’t flip summon this card");
            return;
        }
        if (selectedCard.handleFlip())
            Printer.print("flip summoned successfully");
    }


}

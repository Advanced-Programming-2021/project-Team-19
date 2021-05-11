package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Enums.CardMod;
import model.Phase;
import view.Printer.Printer;

public class FlipSummon extends Summon {

    public FlipSummon(GameData gameData){
        super(gameData, "flip summon");
    }

    public void run(){
        if(checkFlipSummonErrors())
            manageFlip();
    }

    private boolean checkFlipSummonErrors() {

        if (summoningMonster == null) {
            Printer.print("no card is selected yet");
            return false;
        }
        if (!gameData.getCurrentGamer().getGameBoard().getMonsterCardZone()
                .containsCard(summoningMonster)) {
            Printer.print("you can’t change this card position");
            return false;
        }
        if (!gameData.getCurrentPhase().equals(Phase.MAIN1) && !gameData.getCurrentPhase().equals(Phase.MAIN2)) {
            Printer.print("action not allowed in this phase");
            return false;
        }
        if (!summoningMonster.getCardMod().equals(CardMod.DEFENSIVE_HIDDEN)) {
            Printer.print("you can’t flip summon this card");
            return false;
        }
        if (summoningMonster.getTurnWasPutInMonsterZone() == gameData.getTurn()) {
            Printer.print("you can’t flip summon this card");
            return false;
        }
        return true;
    }

    private void manageFlip() {

        gameData.addActionToCurrentActions(this);

        if (summoningMonster.handleFlip())
            Printer.print("flip summoned successfully");

        if(canOtherPlayerActivateAnyTrapOrSpeedSpell()){
            handleActivateTrapOrSpeedSpellOnOtherPlayerTurn();
        }

        gameData.removeActionFromCurrentActions(this);
    }

}

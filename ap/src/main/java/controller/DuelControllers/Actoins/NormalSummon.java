package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Card.Card;
import model.Card.Monster;
import model.Enums.CardFamily;
import model.Enums.MonsterEnums.MonsterTypesForEffects;
import model.Phase;
import view.Printer.Printer;

public class NormalSummon extends Summon {



    public NormalSummon(GameData gameData) {
        super(gameData, "normal summon");
    }

    public void run() {
        if(checkSummonErrors())
            summonMonster();
    }

    private boolean checkSummonErrors(){

        if (summoningMonster == null) {
            Printer.print("no card is selected yet");
            return false;
        }
        if (!gameData.getCurrentGamer().getGameBoard().getHand().getCardsInHand()
                .contains(summoningMonster) ||
                !summoningMonster.getCardFamily().equals(CardFamily.MONSTER) ||
                (summoningMonster).getEffectType().equals(MonsterTypesForEffects.RITUAL)) {
            Printer.print("you can’t summon this card");
            return false;
        }
        if (!gameData.getCurrentPhase().equals(Phase.MAIN1) && !gameData.
                getCurrentPhase().equals(Phase.MAIN2)) {
            Printer.print("action not allowed in this phase");
            return false;
        }
        if (gameData.getCurrentGamer().getGameBoard().getMonsterCardZone().isZoneFull()) {
            Printer.print("monster card zone is full");
            return false;
        }
        if (gameData.getCurrentGamer().getLastTurnHasSummonedOrSet() == gameData.getTurn()) {
            Printer.print("you already summoned/set on this turn");
            return false;
        }
        return true;
    }

    private void summonMonster() {

        if(determineSummonType(summoningMonster)){
            gameData.addActionToCurrentActions(this);
            handleActivateTrapOrSpeedSpellOnOtherPlayerTurn();
            gameData.removeActionFromCurrentActions(this);
        }

    }

    private boolean determineSummonType(Monster monster) {

        boolean hasSummoned = false;
        int level = monster.getLevel();
        if (level <= 4) {
            if (monster.handleSummonType1(gameData))
                hasSummoned = true;
        }
        else if (level <= 6) {
            if (monster.handleSummonType2(gameData))

                hasSummoned = true;
        }
        else{
            if (monster.handleSummonType3(gameData)){

                hasSummoned = true;
            }
        }
        if(hasSummoned){
            Printer.print("summoned successfully");
            return true;
        }
        return false;
    }

}

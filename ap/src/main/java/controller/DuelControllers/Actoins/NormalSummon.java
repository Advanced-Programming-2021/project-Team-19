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
        super(gameData);
    }

    public void run() {
        summonMonster();
    }

    private void summonMonster() {

        Card selectedCard = gameData.getSelectedCard();
        if (selectedCard == null) {
            Printer.print("no card is selected yet");
        } else if (!gameData.getFirstGamer().getGameBoard().getHand().getCardsInHand()
                .contains(selectedCard) ||
                !selectedCard.getCardFamily().equals(CardFamily.MONSTER) ||
                ((Monster) selectedCard).getEffectType().equals(MonsterTypesForEffects.RITUAL)) {
            Printer.print("you can’t summon this card");
        } else if (!gameData.getCurrentPhase().equals(Phase.MAIN1) && !gameData.getCurrentPhase().equals(Phase.MAIN2)) {
            Printer.print("action not allowed in this phase");
        } else if (gameData.getFirstGamer().getGameBoard().getMonsterCardZone().isZoneFull()) {
            Printer.print("monster card zone is full");
        } else if (gameData.getFirstGamer().getLastTurnHasSummonedOrSet() == gameData.getTurn()) {
            Printer.print("you already summoned/set on this turn");
        } else {
            Monster monster = (Monster) selectedCard;
            determineSummonType(monster);
        }
    }

    private void determineSummonType(Monster monster) {
        int level = monster.getLevel();
        if (level <= 4) {
            if (monster.handleSummonType1(gameData))
                Printer.print("summoned successfully");
        } else if (level <= 6) {
            if (monster.handleSummonType2(gameData))
                Printer.print("summoned successfully");
        } else {
            if (monster.handleSummonType3(gameData))
                Printer.print("summoned successfully");
        }
    }
}

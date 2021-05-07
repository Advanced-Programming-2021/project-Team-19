package Model.Card;

import Controller.DuelControllers.GameData;
import Model.Enums.CardMod;
import Model.Enums.MonsterEnums.Attribute;
import View.Printer.Printer;

public class Monster extends Card {
    int Attack;
    int Defence;
    int level;
    boolean canBattle;
    boolean hasBattledThisTurn = false;
    Attribute attribute;
    State state;

    public void handleFlip() {

    }

    public void handleAttack(GameData gameData, int enemyId) {
        Monster defendingMonster = null;
//        Monster defendingMonster = (Monster) gameData.getSecondGamer().getGameBoard().monsterCardZone.getCardById(enemyId, false);

        if (!hasBattledThisTurn)
            Printer.print("this card already attacked");
        else if (defendingMonster == null)
            Printer.print("there is no card to attack here");
        else {
            if (defendingMonster.getCardMode().equals(CardMod.OFFENSIVE_OCCUPIED))
                attackOffensiveMonster(this, defendingMonster);
        }


    }

    private void attackOffensiveMonster(Monster attackingMonster, Monster defendingMonster) {

    }

    public void handleDefend() {

    }

    public void handleDestroy() {

    }

    public void handleSummon() {

    }

}

package Model.Card;

import Controller.DuelControllers.GameData;
import Model.Enums.CardMod;
import Model.Enums.MonsterEnums.Attribute;
import View.Printer.Printer;

public class Monster extends Card {
    private int Attack;
    private int Defence;
    private int level;
    private boolean canBattle;
    private boolean hasBattledThisTurn = false;
    private Attribute attribute;
    private State state;
    private CardMod cardMod;

    public int getAttack() {
        return Attack;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }

    public int getDefence() {
        return Defence;
    }

    public void setDefence(int defence) {
        Defence = defence;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isCanBattle() {
        return canBattle;
    }

    public void setCanBattle(boolean canBattle) {
        this.canBattle = canBattle;
    }

    public boolean isHasBattledThisTurn() {
        return hasBattledThisTurn;
    }

    public void setHasBattledThisTurn(boolean hasBattledThisTurn) {
        this.hasBattledThisTurn = hasBattledThisTurn;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public CardMod getCardMod() {
        return cardMod;
    }

    public void setCardMod(CardMod cardMod) {
        this.cardMod = cardMod;
    }


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

package model.Card;

import controller.DuelControllers.GameData;
import model.Board.MonsterCardZone;
import model.Enums.CardMod;
import model.Enums.MonsterEnums.Attribute;
import model.Enums.MonsterEnums.MonsterTypesForEffects;
import model.Gamer;
import view.GetInput;
import view.Printer.Printer;

import java.util.ArrayList;

public class Monster extends Card {
    private int attack;
    private int defence;
    private int level;
    private boolean canBattle;
    private int lastTurnAttacked = 0;
    private int lastTurnHasChangedPosition = 0;
    private int turnWasPutInMonsterZone = 0;
    private Attribute attribute;
    private State state;
    private CardMod cardMod;
    private MonsterTypesForEffects effectType;

    public int getAttack() {
        return attack;
    }

    public MonsterTypesForEffects getEffectType() {
        return effectType;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
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

    public int getLastTurnAttacked() {
        return lastTurnAttacked;
    }

    public int getTurnWasPutInMonsterZone(){
        return turnWasPutInMonsterZone;
    }

    public int getLastTurnHasChangedPosition() {
        return lastTurnHasChangedPosition;
    }

    public void setLastTurnHasChangedPosition(int lastTurnHasChangedPosition) {
        this.lastTurnHasChangedPosition = lastTurnHasChangedPosition;
    }

    public void setTurnWasPutInMonsterZone(int turnWasPutInMonsterZone){
        this.turnWasPutInMonsterZone = turnWasPutInMonsterZone;
    }

    public boolean handleFlip() {
        setCardMod(CardMod.OFFENSIVE_OCCUPIED);
        return true;
    }

    public void handleAttack(GameData gameData, int enemyId) {
        Monster defendingMonster = (Monster) gameData.getSecondGamer().getGameBoard().getMonsterCardZone().getCardById(enemyId);

        defendingMonster.handleDefend();
        lastTurnAttacked = gameData.getTurn();
        switch (defendingMonster.getCardMod()) {
            case OFFENSIVE_OCCUPIED:
                attackOffensiveMonster(defendingMonster, gameData);
                break;
            case DEFENSIVE_OCCUPIED:
                attackDefensiveMonster(defendingMonster, gameData);
                break;
            case DEFENSIVE_HIDDEN:
                attackDefensiveHiddenMonster(defendingMonster, gameData);
                break;
        }


    }

    private void attackDefensiveHiddenMonster(Monster defendingMonster, GameData gameData) {
        System.out.print("opponent’s monster card was " + defendingMonster.getName() + " and ");
        attackDefensiveMonster(defendingMonster, gameData);
    }

    private void attackDefensiveMonster(Monster defendingMonster, GameData gameData) {
        int damage;
        if (attack > defendingMonster.getDefence()) {
            defendingMonster.handleDestroy(gameData, false);
            Printer.print("the defense position monster is destroyed");
        } else if (attack < defendingMonster.getDefence()) {
            handleDestroy(gameData, true);
            damage = defendingMonster.getDefence() - attack;
            gameData.getFirstGamer().decreaseLifePoint(damage);
            Printer.print("no card is destroyed and you received " + damage + " battle damage");
        } else {
            Printer.print("no card is destroyed");
        }
    }

    private void attackOffensiveMonster(Monster defendingMonster, GameData gameData) {
        int damage;
        if (attack > defendingMonster.getAttack()) {
            defendingMonster.handleDestroy(gameData, false);
            damage = attack - defendingMonster.getAttack();
            gameData.getSecondGamer().decreaseLifePoint(damage);
            Printer.print("your opponent’s monster is destroyed and your opponent receives " + damage + " battle damage");
        } else if (attack < defendingMonster.getAttack()) {
            handleDestroy(gameData, true);
            damage = defendingMonster.getAttack() - attack;
            gameData.getFirstGamer().decreaseLifePoint(damage);
            Printer.print("Your monster card is destroyed and you received " + damage + " battle damage");
        } else {
            handleDestroy(gameData, true);
            defendingMonster.handleDestroy(gameData, false);
            Printer.print("both you and your opponent monster cards are destroyed and no one receives damage");
        }
    }

    public void handleSet(GameData gameData){
        setCardMod(CardMod.DEFENSIVE_HIDDEN);
        setTurnWasPutInMonsterZone(gameData.getTurn());
    }

    public void handleChangePosition(GameData gameData, CardMod newCardMod){
        setCardMod(newCardMod);
        setLastTurnHasChangedPosition(gameData.getTurn());
    }

    public void handleDefend() {
    }

    public void handleDestroy(GameData gamedata, boolean cardOfAttackingUser) {
        Gamer gamer = gamedata.getSecondGamer();
        if (cardOfAttackingUser)
            gamer = gamedata.getFirstGamer();
        gamedata.moveCardFromOneZoneToAnother(this,
                gamer.getGameBoard().getMonsterCardZone(),
                gamer.getGameBoard().getGraveYard());
    }

    public void handleDirectAttack(GameData gameData) {
        lastTurnAttacked = gameData.getTurn();
        gameData.getSecondGamer().decreaseLifePoint(attack);
        Printer.print("your opponent receives " + attack + " battle damage");
    }

    public boolean handleSummonType1(GameData gameData) {
        Gamer gamer = gameData.getFirstGamer();

        gameData.moveCardFromOneZoneToAnother(this,
                gamer.getGameBoard().getHand(),
                gamer.getGameBoard().getMonsterCardZone());
        return true;
    }

    public boolean handleSummonType2(GameData gameData) {
        Gamer gamer = gameData.getFirstGamer();
        MonsterCardZone monsterCardZone = gamer.getGameBoard().getMonsterCardZone();
        if (monsterCardZone.getNumberOfCards() < 1) {
            Printer.print("there are not enough cards for tribute");
            return false;
        } else {
            String command;
            while (true) {
                Printer.print("enter the Id of the monster you want to sacrifice:");
                command = GetInput.getString();
                if (command.matches("([1-5])")) {
                    if (monsterCardZone.getCardById(Integer.parseInt(command)) == null) {
                        Printer.print("there are no monsters on this address");
                    } else {
                        ((Monster) monsterCardZone.getCardById(Integer.parseInt(command))).sacrifice(gameData, gamer);
                        gameData.moveCardFromOneZoneToAnother(this,
                                gamer.getGameBoard().getHand(),
                                gamer.getGameBoard().getMonsterCardZone());
                        return true;
                    }
                } else if (command.matches("cancel")) {
                    return false;
                } else {
                    Printer.printInvalidCommand();
                }
            }
        }
    }

    public boolean handleSummonType3(GameData gameData) {
        Gamer gamer = gameData.getFirstGamer();
        MonsterCardZone monsterCardZone = gamer.getGameBoard().getMonsterCardZone();
        if (monsterCardZone.getNumberOfCards() < 2) {
            Printer.print("there are not enough cards for tribute");
            return false;
        } else {
            String command;
            while (true) {
                Printer.print("enter the Ids of the monsters you want to sacrifice:");
                command = GetInput.getString();
                if (command.matches("([1-5]) ([1-5])")) {
                    String[] ids = command.split(" ");
                    if (monsterCardZone.getCardById(Integer.parseInt(ids[0])) == null ||
                        monsterCardZone.getCardById(Integer.parseInt(ids[1])) == null) {
                        Printer.print("there is no monster on one of these addresses");
                    } else {
                        ((Monster) monsterCardZone.getCardById(Integer.parseInt(ids[0]))).sacrifice(gameData, gamer);
                        ((Monster) monsterCardZone.getCardById(Integer.parseInt(ids[1]))).sacrifice(gameData, gamer);
                        gameData.moveCardFromOneZoneToAnother(this,
                                gamer.getGameBoard().getHand(),
                                gamer.getGameBoard().getMonsterCardZone());
                        return true;
                    }
                } else if (command.matches("cancel")) {
                    return false;
                } else {
                    Printer.printInvalidCommand();
                }
            }
        }
    }

    public void sacrifice(GameData gameData, Gamer gamer){
        gameData.moveCardFromOneZoneToAnother(this,
                gamer.getGameBoard().getMonsterCardZone(),
                gamer.getGameBoard().getGraveYard());
    }
}

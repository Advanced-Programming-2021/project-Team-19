package controller.DuelControllers.Phases;

import controller.DuelControllers.GameData;
import controller.Utils;
import model.Card.Card;
import model.Card.Monster;
import model.Enums.CardMod;
import model.Enums.CardType;
import model.Enums.MonsterEnums.MonsterTypesForEffects;
import view.GetInput;
import view.Printer.Printer;

import java.util.regex.Matcher;

public class MainPhase extends AllPhases {
    private int mainPhaseNumber;

    public MainPhase(GameData gameData, int number) {
        super.gameData = gameData;
        this.mainPhaseNumber = number;
    }

    public void run() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("summon")) {
                summonMonster();
            } else if (command.matches("set")) {
                setCard();
            } else if (command.matches("set --position (attack|defence)")) {
                setPosition(Utils.getMatcher(command, "set --position (.*)"));
            } else if (command.startsWith("select")) {
                select(command);
            } else if (command.matches("flip-summon")) {
                flip();
            } else if (command.matches("next phase")) {
                break;
            } else if (command.matches("activate effect") && mainPhaseNumber == 1) {
                activate();
            } else if (command.matches("help")) {
//                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
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

    private void activate() {

    }

    private void setPosition(Matcher matcher) {

    }

    private void setCard() {

        Card selectedCard = gameData.getSelectedCard();

        if (selectedCard == null) {
            Printer.print("no card is selected yet");
            return;
        } else if (!gameData.getFirstGamer().getGameBoard().getHand().getCardsInHand()
                .contains(selectedCard)) {
            Printer.print("you can’t set this card");
            return;
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

        ((Monster) card).handleSet();

        gameData.moveCardFromOneZoneToAnother(card,
                gameData.getFirstGamer().getGameBoard().getHand(),
                gameData.getFirstGamer().getGameBoard().getMonsterCardZone());

        Printer.print("set successfully");
    }

    private void summonMonster() {

        Card selectedCard = gameData.getSelectedCard();
        if (selectedCard == null) {
            Printer.print("no card is selected yet");
        } else if (!gameData.getFirstGamer().getGameBoard().getHand().getCardsInHand()
                .contains(selectedCard) ||
                !selectedCard.getCardType().equals(CardType.MONSTER) ||
                ((Monster) selectedCard).getEffectType().equals(MonsterTypesForEffects.RITUAL)) {
            Printer.print("you can’t summon this card");
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

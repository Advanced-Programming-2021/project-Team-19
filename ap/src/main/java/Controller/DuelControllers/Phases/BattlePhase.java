package Controller.DuelControllers.Phases;

import Controller.DuelControllers.GameData;
import Controller.Utils;
import Model.Card.Card;
import Model.Card.Monster;
import View.GetInput;
import View.Printer.Printer;

import java.util.regex.Matcher;

public class BattlePhase extends AllPhases {

    public BattlePhase(GameData gameData) {
        super.gameData = gameData;
    }

    public void run() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("attack (\\d+)")) {
                attackMonster(Utils.getMatcher(command, "attack ([1-5])"));
            } else if (command.matches("attack direct")) {
                directAttack();
            } else if (command.startsWith("select")) {
                select(command);
            } else if (command.matches("next phase")) {
                break;
            } else if (command.matches("help")) {
//                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
    }


    private void attackMonster(Matcher matcher) {
        Card selectedCard = gameData.getSelectedCard();
        matcher.find();
        int enemyId = Integer.parseInt(matcher.group(1));


        if (doesNotHaveMutualAttackErrors(selectedCard, gameData)) {
            if (gameData.getSecondGamer().getGameBoard().getMonsterCardZone().getCardById(enemyId) == null)
                Printer.print("there is no card to attack here");
            else {
                Monster attackingMonster = (Monster) selectedCard;
                attackingMonster.handleAttack(gameData, enemyId);
            }
        }
    }

    private void directAttack() {
        Card selectedCard = gameData.getSelectedCard();

        if (doesNotHaveMutualAttackErrors(selectedCard, gameData)) {
            if (currentPlayerCannotDirectAttack(gameData)) {
                Printer.print("you can’t attack the opponent directly");
            } else {
                Monster attackingMonster = (Monster) selectedCard;
                attackingMonster.handleDirectAttack(gameData);
            }
        }
    }

    private boolean currentPlayerCannotDirectAttack(GameData gameData) {
        for (int i = 0; i < 5; i++) {
            if (gameData.getSecondGamer().getGameBoard().getMonsterCardZone().getCardById(i) != null)
                return true;
        }
        return false;
    }

    private boolean doesNotHaveMutualAttackErrors(Card selectedCard, GameData gameData) {
        if (selectedCard == null) {
            Printer.print("no card is selected yet");
            return false;
        } else if (!gameData.getFirstGamer().getGameBoard().getMonsterCardZone().containsCard(selectedCard)) {
            Printer.print("you can’t attack with this card");
            return false;
        } else if (gameData.getTurn() == ((Monster) selectedCard).getLastTurnAttacked()) {
            Printer.print("this card already attacked");
            return false;
        }
        return true;
    }


}

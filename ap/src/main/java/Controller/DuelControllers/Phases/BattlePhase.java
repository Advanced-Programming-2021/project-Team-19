package Controller.DuelControllers.Phases;

import Controller.DuelControllers.GameData;
import Controller.Utils;
import Model.Card.Card;
import Model.Card.Monster;
import View.GetInput;
import View.Printer.Printer;

import java.util.regex.Matcher;

public class BattlePhase extends AllPhases {
    private GameData gamedata;

    public BattlePhase(GameData gamedata) {
        this.gamedata = gamedata;
    }

    public void run() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("attack (\\d+)")) {
                attackMonster(Utils.getMatcher(command, "attack ([1-5])"));
            } else if (command.matches("attack direct")) {
                directAttack();
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
        Card selectedCard = gamedata.getSelectedCard();
        matcher.find();
        int enemyId = Integer.parseInt(matcher.group(1));

        if (selectedCard == null)
            Printer.print("no card is selected yet");
        else if (!gamedata.getFirstGamer().getGameBoard().monsterCardZone.containsCard(selectedCard))
            Printer.print("you can’t attack with this card");
        else{
            Monster attackingMonster = (Monster) selectedCard;
            attackingMonster.handleAttack(gamedata, enemyId);
        }
    }

    private void directAttack() {
    }
}
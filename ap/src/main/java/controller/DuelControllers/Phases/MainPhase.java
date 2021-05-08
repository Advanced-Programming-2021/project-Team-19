package controller.DuelControllers.Phases;

import controller.DuelControllers.GameData;
import controller.Utils;
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
    }

    private void activate() {
    }

    private void setPosition(Matcher matcher) {
    }

    private void setCard() {
    }

    private void summonMonster() {
    }


}

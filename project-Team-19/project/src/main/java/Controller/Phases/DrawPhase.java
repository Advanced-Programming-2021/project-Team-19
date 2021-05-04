package Controller.Phases;

import Controller.GameData;
import Controller.Utils;
import View.GetInput;
import View.Printer.Printer;

public class DrawPhase extends AllPhases{
    private GameData gamedata;

    public DrawPhase(GameData gamedata) {
        this.gamedata = gamedata;
    }

    public String run() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("next phase")) {
                break;
            } else if (command.matches("help")) {
//                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
        addCardToPlayerHand();
        return "";
    }

    private void addCardToPlayerHand() {

    }

}

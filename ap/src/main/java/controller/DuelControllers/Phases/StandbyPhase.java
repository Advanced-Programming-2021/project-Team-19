package controller.DuelControllers.Phases;

import controller.DuelControllers.GameData;
import view.GetInput;
import view.Printer.Printer;

public class StandbyPhase extends AllPhases{

    public StandbyPhase(GameData gamedata) {
        super.gameData = gamedata;
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
        return "";
    }
}

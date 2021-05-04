package Controller.Phases;

import Controller.GameData;
import Controller.Utils;
import View.GetInput;
import View.Printer.Printer;

public class EndPhase {
    private GameData gamedata;

    public EndPhase(GameData gamedata) {
        this.gamedata = gamedata;
    }

    public String EndPhase(GameData gamedata) {
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

    public String run(){
        return "";
    }
}

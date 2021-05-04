package Controller.Phases;

import Controller.GameData;
import Controller.Utils;
import View.GetInput;
import View.Printer.Printer;

public class DrawPhase {
    private GameData gamedata;

    public DrawPhase(GameData gamedata) {
        this.gamedata = gamedata;
    }

    public String run(){
        addCardToPlayerHand();
        return "";
    }

    private void addCardToPlayerHand() {

    }

    public String drawCard(){
        return "";
    }
}

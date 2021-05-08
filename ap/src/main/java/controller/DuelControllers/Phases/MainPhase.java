package controller.DuelControllers.Phases;

import controller.DuelControllers.GameData;
import controller.Utils;
import model.Card.Card;
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

        Card selectedCard = gameData.getSelectedCard();

        if(selectedCard == null){
            Printer.print("no card is selected yet");
            return;
        }
        else if(!gameData.getFirstGamer().getGameBoard().getHand().getCardsInHand().contains(selectedCard)){
            Printer.print("you can’t set this card");
            return;
        }
        else if(gameData.getFirstGamer().getGameBoard().getMonsterCardZone().isZoneFull()){
            Printer.print("monster card zone is full");
            return;
        }
        else if(gameData.getFirstGamer().getLastTurnHasSummonedOrSet() == gameData.getTurn()){
            Printer.print("you already summoned/set on this turn");
            return;
        }
        
    }

    private void summonMonster() {
    }


}

package controller.DuelControllers;

import controller.DuelControllers.Actoins.*;
import controller.DuelControllers.Phases.DrawPhase;
import controller.DuelControllers.Phases.StandbyPhase;
import controller.Utils;
import model.Gamer;
import model.Phase;
import view.GetInput;
import view.Printer.Printer;

import java.util.Locale;

public class Game {

    public Gamer run(GameData gameData) {

        String command;

        while (true) {

            if (gameData.isGameOver()) {
                return finishGame(gameData);
            }
            if (gameData.getCurrentPhase().equals(Phase.DRAW)) {
                new DrawPhase().run(gameData);
                goToNextPhase(gameData);
                continue;
            }
            if (gameData.getCurrentPhase().equals(Phase.STANDBY)) {
                new StandbyPhase().run(gameData);
                goToNextPhase(gameData);
                continue;
            }
            if (gameData.getCurrentPhase().equals(Phase.END)) {
                gameData.turnFinished();
                goToNextPhase(gameData);
                continue;
            }

            command = GetInput.getString();

            if(gameData.getTurn() == 1){
                if(command.equals("next phase")){
                    gameData.goToEndPhase();
                    Printer.print(gameData.getCurrentPhase().getPhaseName());
                    continue;
                }
            }

            if (command.matches("surrender")) {
                if (askForSurrender())
                    return handleSurrender(gameData);
            } else if (command.matches("attack ([1-5])")) {
                new AttackMonster(gameData).run(Utils.getMatcher(command, "attack ([1-5])"));
            } else if (command.matches("attack direct")) {
                new DirectAttack(gameData).run();
            } else if (command.startsWith("select")) {
                new Select(gameData).select(command);
            } else if (command.matches("summon")) {
                new NormalSummon(gameData).run();
            } else if (command.matches("set")) {
                new Set(gameData).run();
            } else if (command.matches("set --position (attack|defence)")) {
                new SetPosition(gameData).run(Utils.getMatcher(command, "set --position (.*)"));
            } else if (command.matches("flip-summon")) {
                new FlipSummon(gameData).run();
            } else if (command.matches("next phase")) {
                goToNextPhase(gameData);
            } else if (command.matches("help")) {
                help(gameData);
            } else if (command.equals("show board")){
                gameData.showBoard();
            }
            else {
                Printer.printInvalidCommand();
            }

        }
    }

    private void help(GameData gameData) {
        GameHelp.run(gameData.getCurrentPhase());
    }

    private boolean askForSurrender() {
        while (true) {
            Printer.print("do you want to surrender?");
            String command = GetInput.getString().toLowerCase(Locale.ROOT);
            if (command.matches("yes"))
                return true;
            else if (command.matches("no"))
                return false;
            else
                Printer.printInvalidCommand();
        }
    }

    private Gamer handleSurrender(GameData gameData) {
        return gameData.getSecondGamer();
    }

    private Gamer finishGame(GameData gameData) {
        Gamer winner = gameData.getCurrentGamer();
        if (winner.getLifePoint() == 0)
            winner = gameData.getSecondGamer();
        gameData.finishGame();
        winner.wonGame();
        Printer.print(winner.getUsername() + "won the game and the score is: " +
                gameData.getGameStarter().getCurrentScoreInDuel() + " - " +
                gameData.getInvitedGamer().getCurrentScoreInDuel());
        return winner;
    }

    private void goToNextPhase(GameData gameData) {

        gameData.goToNextPhase();
        Printer.print(gameData.getCurrentPhase().getPhaseName());
    }

}

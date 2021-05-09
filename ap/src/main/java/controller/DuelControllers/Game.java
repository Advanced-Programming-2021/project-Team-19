package controller.DuelControllers;

import controller.DuelControllers.Actoins.*;
import controller.DuelControllers.Phases.DrawPhase;
import controller.DuelControllers.Phases.StandbyPhase;
import controller.Utils;
import model.Gamer;
import model.Phase;
import view.GetInput;
import view.Printer.Printer;

public class Game {


    public Game() {

    }

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

            if (command.matches("attack (\\d+)")) {
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
//                help();
            } else {
                Printer.printInvalidCommand();
            }

        }
    }

    private Gamer finishGame(GameData gameData) {
        return null;
    }

    private void goToNextPhase(GameData gameData) {

        gameData.goToNextPhase();
        Printer.print(gameData.getCurrentPhase().getPhaseName());
    }

}

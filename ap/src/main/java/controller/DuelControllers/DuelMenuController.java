package controller.DuelControllers;

import controller.DataBaseControllers.DeckDataBaseController;
import controller.DataBaseControllers.UserDataBaseController;
import controller.DuelControllers.Actoins.*;
import controller.DuelControllers.Actoins.Select;
import controller.DuelControllers.Phases.DrawPhase;
import controller.DuelControllers.Phases.StandbyPhase;
import controller.Utils;
import model.Gamer;
import model.Phase;
import model.User;
import view.GetInput;
import view.Menu.Menu;
import view.Printer.Printer;

import java.util.regex.Matcher;

public class DuelMenuController extends Menu {

    private User user;

    public DuelMenuController(String menuName) {
        super(menuName);
    }

    public void run(String username) {

        this.user = UserDataBaseController.getUserByUsername(username);
        String command;

        while (true) {

            command = GetInput.getString();
            if (command.startsWith("Menu")) {
                handleMenuOrders(command);
            } else if (command.equals("Menu exit")) {
                break;
            } else if (command.startsWith("duel --new")) {
                startDuel(command);
            } else {
                Printer.printInvalidCommand();
            }
        }

    }

    public void startDuel(String command) {

        if (command.contains("--ai")) {
            startDuelWithAi(Utils.getMatcher(command, "duel --new --ai --rounds \\d"));
        } else {
            startDuelWithTowPlayer(Utils.getMatcher(command, "duel --new --second-player (\\S) --rounds (\\d)"));
        }
    }

    private void startDuelWithAi(Matcher matcher) {


        int turn = Integer.parseInt(matcher.group(1));

        if (user.getActiveDeckName() == null) {
            Printer.print(user.getUsername() + "has no active deck");
        } else if (!DeckDataBaseController.getDeckByName(user.getUsername() + "_" + user.getActiveDeckName()).isDeckValid()) {
            Printer.print(user.getUsername() + "’s deck is invalid");
        } else if (turn != 1 && turn != 3) {
            Printer.print("number of rounds is not supported");
        } else {

        }

    }

    private void startDuelWithTowPlayer(Matcher matcher) {

        matcher.find();

        User rival = UserDataBaseController.getUserByUsername(matcher.group(1));
        int turn = Integer.parseInt(matcher.group(2));
        if (rival == null) {
            Printer.print("there is no player with this username");
        } else if (user.getActiveDeckName() == null) {
            Printer.print(user.getUsername() + "has no active deck");
        } else if (rival.getActiveDeckName() == null) {
            Printer.print(rival.getUsername() + "has no active deck");
        } else if (!DeckDataBaseController.getDeckByName(user.getUsername() + "_" + user.getActiveDeckName()).isDeckValid()) {
            Printer.print(user.getUsername() + "’s deck is invalid");
        } else if (!DeckDataBaseController.getDeckByName(rival.getUsername() + "_" + rival.getActiveDeckName()).isDeckValid()) {
            Printer.print(rival.getUsername() + "’s deck is invalid");
        } else if (turn != 1 && turn != 3) {
            Printer.print("number of rounds is not supported");
        } else {
            for (int i = 0; i < turn; i++) {
                Gamer firstGamer = new Gamer(user);
                Gamer secondGamer = new Gamer(rival);
                GameData gameData = new GameData(firstGamer, secondGamer);
                handleDuel(gameData);
            }
        }

    }


    public void handleDuel(GameData gameData) {

        String command;

        while (true) {

            if (gameData.isGameOver()) {
                finishDuel(gameData);
                break;
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

    private void finishDuel(GameData gameData) {

    }

    private void goToNextPhase(GameData gameData) {

        gameData.goToNextPhase();
        Printer.print(gameData.getCurrentPhase().getPhaseName());
    }

}

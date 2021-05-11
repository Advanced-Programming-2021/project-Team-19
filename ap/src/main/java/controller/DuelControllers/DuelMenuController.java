package controller.DuelControllers;

import controller.DataBaseControllers.DeckDataBaseController;
import controller.DataBaseControllers.UserDataBaseController;
import controller.Utils;
import model.Gamer;
import model.User;
import view.GetInput;
import view.Menu.Menu;
import view.Printer.Printer;

import java.util.regex.Matcher;

public class DuelMenuController extends Menu {

    private User user;
    private Gamer gameStarter;
    private Gamer rivalGamer;

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
            } else if (command.matches("help")) {
                help();
            } else {
                Printer.printInvalidCommand();
            }
        }

    }

    private void help() {
        System.out.print("""
                duel --new --ai --rounds <1|3>
                duel --new --second-player <second player username> --rounds <1|3>
                help
                menu show-current
                menu enter [menu name]
                menu exit               
                """);
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
        int rounds = Integer.parseInt(matcher.group(2));
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
        } else if (rounds != 1 && rounds != 3) {
            Printer.print("number of rounds is not supported");
        } else {
            gameStarter = new Gamer(user);
            rivalGamer = new Gamer(rival);
            GameData gameData = new GameData(gameStarter, rivalGamer);
            handleDuel(gameData, rounds);
        }

    }


    private void handleDuel(GameData gameData, int rounds) {
        if (rounds == 1) {
            finishDuel(new Game().run(gameData), gameData, 1);
        } else {
            int userWins = 0;
            int rivalWins = 0;
            while (userWins != 2 && rivalWins != 2) {
                if ((new Game().run(gameData)).equals(gameStarter))
                    userWins++;
                else
                    rivalWins++;
            }
            if (userWins == 2) {
                finishDuel(gameStarter, gameData, 3);
            } else {
                finishDuel(rivalGamer, gameData, 3);
            }
        }
    }

    private void finishDuel(Gamer winner, GameData gameData, int rounds) {
        Gamer loser = gameData.getCurrentGamer();
        if (loser.equals(winner))
            loser = gameData.getSecondGamer();

        Printer.print(winner.getUsername() + "won the whole match with score: " +
                gameData.getGameStarter().getCurrentScoreInDuel() + " - " +
                gameData.getInvitedGamer().getCurrentScoreInDuel());
        increaseCreditAndScoreAfterGame(winner, loser, rounds);
    }

    private void increaseCreditAndScoreAfterGame(Gamer winner, Gamer loser, int rounds) {
        winner.increaseCredit(1000 * rounds + winner.getMaxLifePointsInDuel());
        loser.increaseCredit(1000 * rounds);
        winner.increaseUserScore(1000 * rounds);
    }

}

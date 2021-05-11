package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import controller.Utils;
import view.Printer.Printer;

import java.util.regex.Matcher;

public class Select extends Action{

    private boolean commandIsDone;

    private GameData gameData;

    public Select(GameData gameData){
        super(gameData, "select");
    }

    public void select(String command) {

        commandIsDone = false;
        selectMonster(Utils.getMatcher(command, "select --monster (\\d)"));
        selectOpponentMonster(Utils.getMatcher(command, "select (?=.*?--monster)(?=.*?--opponent)--\\S+ --\\S+ (\\d)"));
        selectSpell(Utils.getMatcher(command, "select --spell (\\d)"));
        selectOpponentSpell(Utils.getMatcher(command, "select (?=.*?--spell)(?=.*?--opponent)--\\S+ --\\S+ (\\d)"));
        selectField(Utils.getMatcher(command, "select --field"));
        selectOpponentField(Utils.getMatcher(command, "select (?=.*?--field)(?=.*?--opponent)--\\S+ --\\S+"));
        selectHand(Utils.getMatcher(command, "select --hand (\\d)"));
        removeSelect(Utils.getMatcher(command, "select -d"));
        if (!commandIsDone) {
            Printer.print("invalid selection");
        }

    }

    private void selectMonster(Matcher matcher) {
        if (matcher.matches()) {
            int selectIndex = Integer.parseInt(matcher.group(1));
            if (selectIndex <= 5) {
                commandIsDone = true;
                gameData.setSelectedCard(gameData.getCurrentGamer().getGameBoard().getMonsterCardZone().getCardById(selectIndex));
            }
        }
    }

    private void selectOpponentMonster(Matcher matcher) {
        if (matcher.matches()) {
            int selectIndex = Integer.parseInt(matcher.group(1));
            if (selectIndex <= 5) {
                commandIsDone = true;
                gameData.setSelectedCard(gameData.getSecondGamer().getGameBoard().getMonsterCardZone().getCardById(selectIndex));
            }
        }
    }

    private void selectSpell(Matcher matcher) {
        if (matcher.matches()) {
            int selectIndex = Integer.parseInt(matcher.group(1));
            if (selectIndex <= 5) {
                commandIsDone = true;
                gameData.setSelectedCard(gameData.getCurrentGamer().getGameBoard().
                        getSpellAndTrapCardZone().getCard(selectIndex));
            }
        }
    }

    private void selectOpponentSpell(Matcher matcher) {
        if (matcher.matches()) {
            int selectIndex = Integer.parseInt(matcher.group(1));
            if (selectIndex <= 5) {
                commandIsDone = true;
                gameData.setSelectedCard(gameData.getSecondGamer().getGameBoard().
                        getSpellAndTrapCardZone().getCard(selectIndex));
            }
        }
    }

    private void selectField(Matcher matcher) {
        if (matcher.matches()) {
            commandIsDone = true;
            gameData.setSelectedCard(gameData.getCurrentGamer().getGameBoard().getFieldZone().getCard(0));
        }
    }

    private void selectOpponentField(Matcher matcher) {
        if (matcher.matches()) {
            commandIsDone = true;
            gameData.setSelectedCard(gameData.getSecondGamer().getGameBoard().getFieldZone().getCard(0));
        }
    }

    private void selectHand(Matcher matcher) {
        if (matcher.matches()) {
            commandIsDone = true;
            int selectIndex = Integer.parseInt(matcher.group(1));
            gameData.setSelectedCard(gameData.getCurrentGamer().getGameBoard().getHand().getCard(0));
        }
    }

    private void removeSelect(Matcher matcher) {
        if (matcher.matches()) {
            commandIsDone = true;
            gameData.setSelectedCard(null);
        }
    }



}

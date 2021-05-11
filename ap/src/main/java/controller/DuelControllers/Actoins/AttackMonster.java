package controller.DuelControllers.Actoins;

import controller.DuelControllers.GameData;
import model.Card.Card;
import model.Card.Monster;
import view.Printer.Printer;

import java.util.regex.Matcher;

public class AttackMonster extends Attack{

    public AttackMonster(GameData gameData){
        super(gameData, "attackMonster");
    }


    public void run(Matcher matcher){
        attackMonster(matcher);
    }

    public void attackMonster(Matcher matcher) {

        Card selectedCard = gameData.getSelectedCard();
        matcher.find();
        int enemyId = Integer.parseInt(matcher.group(1));


        if (doesNotHaveMutualAttackErrors(selectedCard, gameData)) {
            if (gameData.getSecondGamer().getGameBoard().getMonsterCardZone().getCardById(enemyId) == null)
                Printer.print("there is no card to attack here");
            else {
                Monster attackingMonster = (Monster) selectedCard;
                attackingMonster.handleAttack(gameData, enemyId);
            }
        }
    }

}

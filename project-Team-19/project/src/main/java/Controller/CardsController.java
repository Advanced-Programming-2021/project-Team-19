package Controller;

import Model.Card.Card;
import Model.User;

import java.util.regex.Matcher;

public class CardsController {
    private Card card;
    private User user;
    private static CardsController instance=null;
    public static CardsController getInstance(){
        if(instance==null){
            instance=new CardsController();
        }
        return instance;
    }

    public void manageCardController(){

    }

    public void destroyCard(Card card){

    }



    private void summonMonster(Matcher matcher){


    }
    private void setMonster(Matcher matcher){

    }
    private void changeMode(Matcher matcher){


    }
    private void flipSummon(Matcher matcher){

    }
    private void attackMonster(Matcher matcher){

    }
    private void attackDirect(Matcher matcher){

    }
    private void activateEffect(Matcher matcher){

    }
    private void setSpell(Matcher matcher){

    }
    private void setTrap(Matcher matcher){

    }
    private void activateSpellOrTrap(Matcher matcher){

    }
    private void ritualSummon(Matcher matcher){

    }
    private void specialSummon(Matcher matcher){

    }
    private void showGraveYard(Matcher matcher){

    }
    private void showAllCards(Matcher matcher){

    }
    private void handleScores(Matcher matcher){

    }
}

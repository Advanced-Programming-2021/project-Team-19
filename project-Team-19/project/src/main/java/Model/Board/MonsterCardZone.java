package Model.Board;

import Model.Card.Card;

import java.util.ArrayList;

public class MonsterCardZone {
    private ArrayList<Card> cardsInMonsterZone=new ArrayList<>();
    private ArrayList<>
    private int  hashNumber(int i){


        return 1;
    }
    private int hashRivalNumber(int i){


        return 1;
    }
    public Card getCardById(int id,boolean selfRequest){
        if(selfRequest){
            return cardsInMonsterZone.get(hashNumber(id));
        }
        else {
            return cardsInMonsterZone.get(hashRivalNumber(id));
        }
    }
    public Card removeCardById(int id){

        return null;
    }
    public void addCard(Card card){


    }


}

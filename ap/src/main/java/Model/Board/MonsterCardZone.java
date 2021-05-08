package Model.Board;

import Model.Card.Card;
import Model.Card.Monster;
import Model.Enums.CardMod;

import java.util.ArrayList;

public class MonsterCardZone extends Zones {
    private final ArrayList<Monster> cardsInMonsterZone = new ArrayList<>();

    {
        for (int i = 0; i < 5; i++) {
            cardsInMonsterZone.add(null);
        }
    }


    public Card getCardById(int id) {
        return cardsInMonsterZone.get(hashNumber(id));
    }

    public Card removeCard(int id) {

        return null;
    }

    public void addCard(Card card) {


    }

    public boolean containsCard(Card card){
        return cardsInMonsterZone.contains(card);
    }

    public void changeModById(int id, CardMod cardMod) {


    }
    @Override
    public String toString(){
        StringBuilder temp= new StringBuilder("\t");
        for(int i=0;i<5;i++){
            if(cardsInMonsterZone.get(i).getCardMod().equals(CardMod.EMPTY)){
                temp.append("E\t");
            }
            else if(cardsInMonsterZone.get(i).getCardMod().equals(CardMod.DEFENSIVE_HIDDEN)){
                temp.append("DH\t");
            }
            else if(cardsInMonsterZone.get(i).getCardMod().equals(CardMod.DEFENSIVE_OCCUPIED)){
                temp.append("DO\t");
            }
            else{
                temp.append("OO\t");
            }
        }
        return temp.toString();
    }


}

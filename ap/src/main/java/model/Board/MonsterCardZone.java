package model.Board;

import model.Card.Card;
import model.Card.Monster;
import model.Enums.CardMod;

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
        Card temp=cardsInMonsterZone.get(hashNumber(id));
        cardsInMonsterZone.set(hashNumber(id),null);
        return temp;
    }

    public void addCard(Card card) {
        for(int i=0;i<5;i++){
            if(cardsInMonsterZone.get(i)==null){
                cardsInMonsterZone.set(i,(Monster)card);
            }
        }
    }

    public boolean containsCard(Card card){
        return cardsInMonsterZone.contains(card);
    }


    public int getNumberOfCards(){
        int toReturn = 0;

        for (Monster monster : cardsInMonsterZone) {
            if (monster != null)
                toReturn++;
        }

        return toReturn;
    }

    public boolean isZoneFull(){
        return getNumberOfCards() == 5;
    }

    public int getId(Card card){
        if(cardsInMonsterZone.contains((Monster)card)){
            return cardsInMonsterZone.indexOf((Monster)card);
        }
        else{
            return -1;
        }
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

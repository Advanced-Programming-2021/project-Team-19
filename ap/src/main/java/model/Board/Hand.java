package model.Board;

import model.Card.Card;

import java.util.ArrayList;

public class Hand extends Zones {
    ArrayList<Card> cardsInHand = new ArrayList<>();

    public ArrayList<Card> getCardsInHand(){
        return cardsInHand;
    }

    public Card getCard(int id) {
        return cardsInHand.get(id);
    }

    public Card removeCard(int id) {
        Card temp=getCard(id);
        cardsInHand.remove(id);
        return temp;
    }

    public void addCard(Card card) {
        cardsInHand.add(card);
    }

    public int getId(Card card){
        if(cardsInHand.contains(card)){
            return cardsInHand.indexOf(card);
        }
        else{
            return -1;
        }
    }

    public String selfToString(){
        return "c\t".repeat(cardsInHand.size());
    }

    public String rivalToString(){
        return "\tc".repeat(cardsInHand.size());
    }

}

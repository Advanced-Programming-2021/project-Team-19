package model.Board;

import model.Card.Card;

import java.util.ArrayList;

public class Hand extends Zones {
    ArrayList<Card> cardsInHand = new ArrayList<>();

    public ArrayList<Card> getCardsInHand(){
        return cardsInHand;
    }

    public Card getCard() {

        return null;
    }

    public Card removeCard(int id) {
        Card temp=cardsInHand.get(id);
        cardsInHand.set(id,null);
        return temp;
    }

    public void addCard(Card card) {

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

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

        return null;
    }

    public void addCard(Card card) {


    }

    public String selfToString(){
        return "c\t".repeat(cardsInHand.size());
    }

    public String rivalToString(){
        return "\tc".repeat(cardsInHand.size());
    }

}

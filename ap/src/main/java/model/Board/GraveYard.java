package model.Board;

import model.Card.Card;

import java.util.ArrayList;

public class GraveYard extends Zones {
    private ArrayList<Card> cardsInGraveYard = new ArrayList<>();

    public Card getCard() {

        return null;
    }

    public Card removeCard(int id) {

        return null;
    }

    public int getId(Card card){
        if(cardsInGraveYard.contains(card)){
            return cardsInGraveYard.indexOf(card);
        }
        else{
            return -1;
        }
    }

    public void addCard(Card card) {


    }

    public int getSize() {
        return cardsInGraveYard.size();
    }

}

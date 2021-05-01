package Model.Board;

import Model.Card.Card;
import View.Printer.Printer;

import java.util.ArrayList;

public class Hand extends Zones {
    ArrayList<Card> cardsInHand = new ArrayList<>();

    public Card getCard() {

        return null;
    }

    public Card removeCard(int id) {

        return null;
    }

    public void addCard(Card card) {


    }

    public String toString(){
        return "c    ".repeat(cardsInHand.size());
    }
}

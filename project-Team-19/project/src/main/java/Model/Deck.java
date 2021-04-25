package Model;


import Model.Card.Card;

import java.util.ArrayList;

public class Deck {

    private int id;
    private ArrayList<Card> mainDeckCards;
    private ArrayList<Card> sideDeckCards;

    public Deck(int id) {
    }

    public static Deck gsonToDeck(String gson) {
        return null;
    }

    ;

    private void setID() {
    }

    public void addCadToMainDeck(Card card) {
    }

    public void addCardToSideDeck(Card card) {
    }

    public boolean isDeckValid() {
        return true;
    }

    public void removeCardFromSideDeck(Card card) {

    }

    public void removeCardFromMainDeck(Card card) {

    }

    private boolean canAddThisCard(Card card) {
        return true;
    }

}

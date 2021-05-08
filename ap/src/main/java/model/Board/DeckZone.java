package model.Board;

import controller.DataBaseControllers.CardDataBaseController;
import model.Card.Card;
import model.Deck;
import model.Enums.CardNames;

import java.util.ArrayList;

public class DeckZone extends Zones {

    ArrayList<Card> mainDeckCards=new ArrayList<>();
    ArrayList<Card> sideDeckCards=new ArrayList<>();

    public DeckZone(Deck deck){
        for(CardNames cardName:deck.getMainDeckCards()){
            mainDeckCards.add(CardDataBaseController.getCardObjectByCardName(cardName));
        }
        for(CardNames cardName:deck.getSideDeckCards()){
            sideDeckCards.add(CardDataBaseController.getCardObjectByCardName(cardName));
        }
    }


    public Card getCard() {

        return null;
    }

    public Card removeCard(int id) {

        return null;
    }

    public void addCard(Card card) {


    }

    public int getSize() {
        return mainDeckCards.size();
    }
}

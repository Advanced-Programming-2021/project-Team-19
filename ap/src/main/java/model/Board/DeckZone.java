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


    public Card getCard(int id) {
        return mainDeckCards.get(id);
    }


    public Card removeCard(int id) {
        Card temp=mainDeckCards.get(id);
        mainDeckCards.remove(temp);
        return temp;
    }

    public void addCard(Card card) {
        mainDeckCards.add(card);
    }

    public int getId(Card card){
        if(mainDeckCards.contains(card)){
            return mainDeckCards.indexOf(card);
        }
        else{
            return -1;
        }
    }

    public int getSize() {
        return mainDeckCards.size();
    }
}

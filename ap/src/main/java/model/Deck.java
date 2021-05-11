package model;


import controller.DataBaseControllers.CardDataBaseController;
import model.Card.Card;
import model.Card.Monster;
import model.Card.SpellAndTraps;
import model.Enums.CardNames;

import java.util.ArrayList;
import java.util.TreeSet;

public class Deck {
    private final String name;
    private final  ArrayList<CardNames> mainDeckCards=new ArrayList<>();
    private final ArrayList<CardNames> sideDeckCards=new ArrayList<>();

    public Deck(String name) {
        this.name=name;
    }

    public static Deck gsonToDeck(String gson) {
        return null;
    }



    private void setID() {
    }

    public void addCardToMainDeck(CardNames cardName) {
        mainDeckCards.add(cardName);
    }

    public void addCardToSideDeck(CardNames cardName) {
        sideDeckCards.add(cardName);
    }

    public ArrayList<CardNames> getMainDeckCards(){
        return mainDeckCards;
    }
    public ArrayList<CardNames> getSideDeckCards(){
        return sideDeckCards;
    }

    public boolean isDeckValid() {
        return true;
    }

    public String getName(){
        return name;
    }

    public void removeCardFromSideDeck(CardNames cardName) {
        sideDeckCards.remove(cardName);
    }

    public void removeCardFromMainDeck(CardNames cardName) {
        mainDeckCards.remove(cardName);
    }


    public boolean isSideDeckFull(){
        return sideDeckCards.size()>=15;
    }
    public boolean isMainDeckFull(){
        return mainDeckCards.size()>=60;
    }

    public ArrayList<CardNames> getAllCard(){
        ArrayList<CardNames> allCardNames=new ArrayList<>();
        allCardNames.addAll(mainDeckCards);
        allCardNames.addAll(sideDeckCards);
        return allCardNames;
    }

    public TreeSet<Card> getAllCardsSorted(){
        TreeSet<Card> allCards=new TreeSet<>(new Card.CardComp());
        for(CardNames cardName:mainDeckCards){
            allCards.add(CardDataBaseController.getCardObjectByCardName(cardName));
        }
        for(CardNames cardName:sideDeckCards){
            allCards.add(CardDataBaseController.getCardObjectByCardName(cardName));
        }
        return allCards;
    }

    public TreeSet<Card> getAllMainCardsSorted(){
        TreeSet<Card> allCards=new TreeSet<>(new Card.CardComp());
        for(CardNames cardName:mainDeckCards){
            allCards.add(CardDataBaseController.getCardObjectByCardName(cardName));
        }
        return allCards;
    }

    public TreeSet<Card> getAllSideCardsSorted(){
        TreeSet<Card> allCards=new TreeSet<>(new Card.CardComp());
        for(CardNames cardName:sideDeckCards){
            allCards.add(CardDataBaseController.getCardObjectByCardName(cardName));
        }
        return allCards;
    }

    private boolean canAddThisCard(Card card) {
        return true;
    }

    public boolean isThereThreeCardsInDeck(CardNames CardName){
        int cnt=0;
        for(CardNames cardName:getAllCard()){
            if(cardName.equals(CardName)){
                cnt++;
            }
        }
        return cnt>=3;
    }
    @Override
    public String toString(){
        String temp= name+": main deck "+mainDeckCards.size()+", side deck " +
                sideDeckCards.size()+", ";
        if(isDeckValid()){
            return temp+"valid";
        }
        else{
            return temp+"invalid";
        }
    }

    public String detailedToStringMain(){
        StringBuilder detailedToString=new StringBuilder();
        detailedToString.append("Deck: ").append(name).append("\n");
        detailedToString.append("Main deck:\n");
        detailedToString.append("Monsters:\n");
        for(Card card:getAllMainCardsSorted()){
            if(card instanceof Monster){
                detailedToString.append(card.toString()).append("\n");
            }
        }
        detailedToString.append("Spell And Traps:\n");
        for(Card card:getAllMainCardsSorted()){
            if(card instanceof SpellAndTraps){
                detailedToString.append(card.toString()).append("\n");
            }
        }
        return detailedToString.toString();
    }

    public String detailedToStringSide(){
        StringBuilder detailedToString=new StringBuilder();
        detailedToString.append("Deck: ").append(name).append("\n");
        detailedToString.append("Side deck:\n");
        detailedToString.append("Monsters:\n");
        for(Card card:getAllSideCardsSorted()){
            if(card instanceof Monster){
                detailedToString.append(card.toString()).append("\n");
            }
        }
        detailedToString.append("Spell And Traps:\n");
        for(Card card:getAllSideCardsSorted()){
            if(card instanceof SpellAndTraps){
                detailedToString.append(card.toString()).append("\n");
            }
        }
        return detailedToString.toString();
    }

}

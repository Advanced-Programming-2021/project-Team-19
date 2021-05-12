package model.Board;

import model.Card.Card;
import model.Card.SpellAndTraps;
import model.Enums.CardMod;
import model.Enums.SpellCardMods;

import java.util.ArrayList;
import java.util.Collections;

public class SpellAndTrapCardZone extends Zones {

    private ArrayList<SpellAndTraps> allCards=new ArrayList<>();

    {
        for(int i=0;i<5;i++){
            allCards.add(null);
        }
    }

    public ArrayList<SpellAndTraps> getAllCards(){
        return allCards;
    }

    public Card getCard(int id) {
        return allCards.get(hashNumber(id));
    }

    public Card removeCard(int id) {
        Card temp=allCards.get(hashNumber(id));
        allCards.set(hashNumber(id),null);
        return temp;
    }

    public void addCard(Card card) {
        for(int i=1;i<=5;i++){
            if(allCards.get(hashNumber(i))!=null){
                allCards.set(hashNumber(i),(SpellAndTraps)card);
                break;
            }
        }
    }

    public int getId(Card card){
        if(allCards.contains(card)){
            return allCards.indexOf(card);
        }
        else{
            return -1;
        }
    }

    public String getStringForSelf(){

        StringBuilder answer= new StringBuilder();

        for(String appendingStr : getPrintingStringsForToStringMethod()){
            answer.append(appendingStr);
        }
        return answer.toString();
    }

    public String getStringForRival(){

        StringBuilder answer= new StringBuilder();

        ArrayList<String> printingStrings = getPrintingStringsForToStringMethod();
        Collections.reverse(printingStrings);

        for(String appendingStr : printingStrings){
            answer.append(appendingStr);
        }
        return answer.toString();
    }

    private ArrayList<String> getPrintingStringsForToStringMethod() {

        ArrayList<String> returnedArrayList = new ArrayList<>();

        returnedArrayList.add("\t");

        for (int i = 0; i < 5; i++) {

            if(allCards.get(i) == null){
                returnedArrayList.add("E ");
            }
            else if(allCards.get(i).getSpellCardMod().equals(SpellCardMods.HIDDEN)){
                returnedArrayList.add("H ");
            }
            else{
                returnedArrayList.add("O ");
            }

            returnedArrayList.add("\t");

        }

        return returnedArrayList;
    }

    public boolean isZoneFull(){
        for(Card card : allCards){
            if(card == null){
                return false;
            }
        }
        return true;
    }
}

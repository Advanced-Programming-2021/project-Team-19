package model.Board;

import model.Card.Card;
import model.Card.Spell;
import model.Enums.SpellCardMods;

import java.util.ArrayList;

public class SpellAndTrapCardZone extends Zones {

    ArrayList<Spell> allCards=new ArrayList<>();


    {
        for(int i=0;i<5;i++){
            allCards.add(null);
        }
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
        for(int i=0;i<5;i++){
            if(allCards.get(i)!=null){
                allCards.set(i,(Spell)card);
            }
        }
    }

    public int getId(Card card){
        if(allCards.contains((Spell)card)){
            return allCards.indexOf((Spell)card);
        }
        else{
            return -1;
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder("\t");
        for(int i=0;i<5;i++){
            if(allCards.get(i).getSpellCardMod().equals(SpellCardMods.EMPTY)){
                stringBuilder.append("E\t");
            }
            else if(allCards.get(i).getSpellCardMod().equals(SpellCardMods.HIDDEN)){
                stringBuilder.append("H\t");
            }
            else{
                stringBuilder.append("O\t");
            }

        }
        return stringBuilder.toString();
    }
}
